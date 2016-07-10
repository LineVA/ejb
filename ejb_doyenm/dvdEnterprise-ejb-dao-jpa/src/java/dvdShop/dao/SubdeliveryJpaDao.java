/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.Delivery;
import dvdShop.jpa.DeliveryState;
import dvdShop.jpa.Subdelivery;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author doyenm
 */
@Singleton(mappedName = "subdelivery.dao")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SubdeliveryJpaDao implements Serializable, SubdeliveryDaoLocal, SubdeliveryDaoRemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Subdelivery subdelivery) {
        em.persist(subdelivery);
    }

    @Override
    public List<Subdelivery> getByDelivery(Delivery delivery) {
        return em.createQuery("select sub from Subdelivery sub "
                + "where sub.delivery=:id ").setParameter("id", delivery)
                .getResultList();
    }

    @Override
    public Subdelivery get(int idSubdelivery) {
        return em.find(Subdelivery.class, idSubdelivery);
    }

    @Override
    public void ready(Subdelivery subdelivery) {
        Subdelivery newSub = new Subdelivery(subdelivery.getIdSubdelivery(),
                subdelivery.getDelivery(), DeliveryState.READY,
                subdelivery.getDvd());
        em.merge(newSub);
    }

    @Override
    public void gone(Subdelivery subdelivery) {
        Subdelivery sub = new Subdelivery(subdelivery.getIdSubdelivery(),
                subdelivery.getDelivery(), DeliveryState.GONE,
                subdelivery.getDvd());
        em.merge(sub);
    }

}
