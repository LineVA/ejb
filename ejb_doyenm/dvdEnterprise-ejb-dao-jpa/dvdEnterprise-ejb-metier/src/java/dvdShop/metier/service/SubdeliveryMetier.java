/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.dao.SubdeliveryDaoLocal;
import dvdShop.jpa.DVD;
import dvdShop.jpa.Delivery;
import dvdShop.jpa.DeliveryState;
import dvdShop.jpa.Subdelivery;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import dvdShop.metier.entities.DeliveryStateException;
import java.util.Iterator;

/**
 *
 * @author doyenm
 */
@Singleton
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SubdeliveryMetier implements Serializable, ISubdeliveryMetierLocal, ISubdeliveryMetierRemote {

    @EJB
    private SubdeliveryDaoLocal dao;

    @EJB
    private IDeliveryMetierRemote deliveryMetier;

    @EJB
    private IDVDMetierRemote dvdMetier;

    @Override
    public void add(Subdelivery subdelivery) {
        // We check if we have enough stock for this DVD
        // if yes, we change the DeliveryState of the subdelivery to READY
        // if no, it stays to WAITING
        if (subdelivery.getDvd().getStock() > 0) {
            subdelivery.setSubdeliveryState(DeliveryState.READY);
            DVD dvd = subdelivery.getDvd();
            dvdMetier.stockDecrement(dvd, 1);
        } else {
            subdelivery.setSubdeliveryState(DeliveryState.WAITING);
        }
        dao.add(subdelivery);
    }

    public void checkAndReady(Subdelivery sub) throws DeliveryStateException {
        if (sub.getSubdeliveryState() == DeliveryState.WAITING) {
            System.out.println("Dans le 3 if");
            dao.ready(sub);
            // And we check if we need to change the status of the corresponding
            // delivery
            deliveryMetier.checkDeliveryState(sub.getDelivery().getIdDelivery());
        }
    }

//    @Override
//    public void ready(int idSubdelivery) throws DeliveryStateException {
//        Delivery del = dao.get(idSubdelivery).getDelivery();
//        List<Subdelivery> subs = del.getSubdeliveries();
//        System.out.println("Re : " + subs.size());
//    }
    @Override
    public void ready(int idSubdelivery) throws DeliveryStateException {
        Subdelivery sub = dao.get(idSubdelivery);
        String supplierStr = sub.getDvd().getSupplier().getSupplier();
        // If the subdelivery is WAITING, it becomes READY
        // If we do this operation, it is because we receive some DVDs from 
        // the supplier of the DVD , which is in this subdelivery.
        // So we also received the others DVds in this deliver, which come 
        // from the same supplier.
        if (sub.getSubdeliveryState() == DeliveryState.WAITING) {
            List<Subdelivery> subdeliveries = dao.getByDelivery(sub.getDelivery());
            Iterator it = subdeliveries.iterator();
            Subdelivery next;
            while (it.hasNext()) {
                next = (Subdelivery) it.next();
                if (next.getDvd().getSupplier().getSupplier().equals(supplierStr)) {
                    checkAndReady(next);
                }
            }
        } else {
            throw new DeliveryStateException("Only a WAITING subdelivery "
                    + "can become READY.");
        }
    }

    @Override
    public void gone(int idSubdelivery) throws DeliveryStateException {
        Subdelivery sub = dao.get(idSubdelivery);
        if (sub.getSubdeliveryState() == DeliveryState.READY) {
            dao.gone(sub);
        } else {
            throw new DeliveryStateException("Only a READY subdelivery can become GONE");
        }
    }

    @Override
    public List<Subdelivery> getByDelivery(Delivery delivery) {
        return dao.getByDelivery(delivery);
    }

}
