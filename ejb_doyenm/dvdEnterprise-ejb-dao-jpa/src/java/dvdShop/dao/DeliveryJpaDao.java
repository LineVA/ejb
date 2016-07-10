/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.Delivery;
import dvdShop.jpa.DeliveryState;
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
@Singleton(mappedName = "delivery.dao")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DeliveryJpaDao implements DeliveryDaoLocal, DeliveryDaoRemote, Serializable {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Delivery add(Delivery delivery) {
        // Creation of a new delivery
        em.persist(delivery);
        em.flush();
        return delivery;
    }

    @Override
    public List<Delivery> getAll() {
        return em.createNamedQuery("Delivery.getAll").getResultList();
    }

    @Override
    public Delivery get(int id) {
        return em.find(Delivery.class, id);
    }

    @Override
    public void ready(Delivery delivery) {
        Delivery newDel = new Delivery(delivery.getIdDelivery(), DeliveryState.READY);
        em.merge(newDel);
    }
    
     @Override
    public void gone(Delivery delivery) {
        Delivery newDel = new Delivery(delivery.getIdDelivery(), DeliveryState.GONE);
        em.merge(newDel);
    }
}
