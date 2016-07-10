/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.dao.DeliveryDaoLocal;
import dvdShop.jpa.DVD;
import dvdShop.jpa.Delivery;
import dvdShop.jpa.DeliveryState;
import dvdShop.jpa.Subdelivery;
import dvdShop.metier.entities.DeliveryStateException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author doyenm
 */
@Singleton
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DeliveryMetier implements Serializable, IDeliveryMetierLocal, IDeliveryMetierRemote {

    // couche dao
    @EJB
    private DeliveryDaoLocal dao;

    @EJB
    private ISubdeliveryMetierRemote subdeliveryMetier;

    @Override
    public List<Delivery> getAll() {
        return dao.getAll();
    }

    @Override
    public void ready(int id) throws DeliveryStateException {
        Delivery deliv = dao.get(id);
        if (deliv.getDeliveryState() == DeliveryState.WAITING) {
            dao.ready(deliv);
        } else {
            System.out.println("Dans l'exception");
            throw new DeliveryStateException("Only a WAITING delivery can become READY");
        }
    }

    // The initial method, which uses the EJB properties.
    // The list of subdeliveries is directly obtained from the delivery
//    @Override
//    public void gone(int id) throws DeliveryStateException {
//        Delivery deliv = dao.get(id);
//        List<Subdelivery> subList = deliv.getSubdeliveries();
//        Iterator it = subList.iterator();
//        Subdelivery next;
//        if (deliv.getDeliveryState() == DeliveryState.READY) {
//            while (it.hasNext()) {
//                next = (Subdelivery) it.next();
//                if (deliv.getDeliveryState() == DeliveryState.READY) {
//                    subdeliveryMetier.gone(next.getIdSubdelivery());
//                } else {
//                    throw new DeliveryStateException("Only a READY subdelivery can become GONE");
//                }
//            }
//            dao.gone(deliv);
//        } else {
//            throw new DeliveryStateException("Only a READY delivery can become GONE");
//        }
//    }
    public void gone(int id) throws DeliveryStateException {
        Delivery deliv = dao.get(id);
        List<Subdelivery> subList = subdeliveryMetier.getByDelivery(deliv);
        Iterator it = subList.iterator();
        Subdelivery next;
        if (deliv.getDeliveryState() == DeliveryState.READY) {
            while (it.hasNext()) {
                next = (Subdelivery) it.next();
                if (deliv.getDeliveryState() == DeliveryState.READY) {
                    subdeliveryMetier.gone(next.getIdSubdelivery());
                } else {
                    throw new DeliveryStateException("Only a READY subdelivery can become GONE");
                }
            }
            dao.gone(deliv);
        } else {
            throw new DeliveryStateException("Only a READY delivery can become GONE");
        }
    }

    @Override
    public void checkDeliveryState(Delivery delivery) throws DeliveryStateException {
        List<Subdelivery> subList = delivery.getSubdeliveries();

        System.out.println("L'id de la delivery est : " + delivery.getIdDelivery());
        System.out.println("Sa liste de subdeliveries est de taille : " + delivery.getSubdeliveries().size());
        System.out.println("Or, le nombre de subdeliveries pointant "
                + "vers cette delivery est  : " + subdeliveryMetier.getByDelivery(delivery).size());

        Iterator it = subList.iterator();
        boolean ready = true;
        Subdelivery next;
        // We check if all of the subdelivery are ready
        while (it.hasNext()) {
            next = (Subdelivery) it.next();
            if (!next.getSubdeliveryState().equals(DeliveryState.READY)) {
                ready = false;
            }
        }
        // If yes, the whole delivery goes to READY
        if (ready) {
            System.out.println(delivery.getDeliveryState());
            this.ready(delivery.getIdDelivery());
        }
    }

    @Override
    public void checkDeliveryState(int idDelivery) throws DeliveryStateException {
        List<Subdelivery> subList = subdeliveryMetier.getByDelivery(dao.get(idDelivery));
        Iterator it = subList.iterator();
        boolean ready = true;
        Subdelivery next;
        // We check if all of the subdelivery are ready
        while (it.hasNext()) {
            next = (Subdelivery) it.next();
            if (!next.getSubdeliveryState().equals(DeliveryState.READY)) {
                ready = false;
            }
        }
        // If yes, the whole delivery goes to READY
        if (ready) {
            this.ready(idDelivery);
        }
    }

    @Override
    public void add(List<DVD> products) throws DeliveryStateException {
        Delivery delTmp = new Delivery(DeliveryState.WAITING);
        Delivery del = dao.add(delTmp);
        System.out.println("DELTMP : " + delTmp.getSubdeliveries());
        Iterator it = products.iterator();
        DVD next;
        Subdelivery sub = null;
        while (it.hasNext()) {
            next = (DVD) it.next();
            sub = new Subdelivery(del, DeliveryState.WAITING, next);
            subdeliveryMetier.add(sub);
        }
           this.checkDeliveryState(del.getIdDelivery());
        //this.checkDeliveryState(del);
        // We are sure that sub is not null : we cannot have an empty delivery

    }
}
