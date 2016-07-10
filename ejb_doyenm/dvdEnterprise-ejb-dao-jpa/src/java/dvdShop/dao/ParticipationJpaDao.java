/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.Intervention;
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
@Singleton(mappedName = "intervention.dao")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ParticipationJpaDao implements ParticipationDaoLocal, ParticipationDaoRemote, Serializable{
     @PersistenceContext
    private EntityManager em;

     public ParticipationJpaDao(){
         
     }
     
     @Override
     public List<Intervention> getAllParticipations(){
         return em.createNamedQuery("Intervention.getAll").getResultList();
     }

    @Override
    public boolean addParticipation(Intervention participation) {
        em.persist(participation);
        return true;
    }

    @Override
    public boolean deleteParticipation(Intervention participation) {
        //em.remove(participation);
        return true;
    }

    @Override
    public List<Intervention> searchIndividual(int idIndividual) {
           return em.createQuery("select interv from Intervention interv where interv.idIndividual=:id")
                   .setParameter("id", idIndividual).getResultList();
    }

    @Override
    public List<Intervention> searchDVD(int idDVD) {
    return em.createQuery("select interv from Intervention interv where interv.idDVD=:id")
                   .setParameter("id", idDVD).getResultList();    }
}
