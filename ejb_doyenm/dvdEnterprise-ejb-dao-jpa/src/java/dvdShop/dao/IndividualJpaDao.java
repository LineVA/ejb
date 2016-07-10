/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.Individual;
import java.io.Serializable;
import java.util.Iterator;
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
@Singleton(mappedName = "individual.dao")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IndividualJpaDao implements Serializable, IndividualDaoRemote, IndividualDaoLocal{

    @PersistenceContext
    private EntityManager em;
    
    public IndividualJpaDao(){
        
    }

    @Override
    public List<Individual> getAllIndividuals() {
        return em.createNamedQuery("Individual.getAll").getResultList();
    }

    @Override
    public boolean addIndividual(Individual individual) {
         em.persist(individual);
         return true;
    }

    @Override
    public boolean deleteIndividual(Individual individual) {
         List<Individual> indivSearch = getIndividualByIdentity(
                 individual.getFirstName(), individual.getLastName());
        if (indivSearch.size() == 1) {
            Individual next;
            Iterator<Individual> it = indivSearch.iterator();
            while (it.hasNext()) {
                next = it.next();
                em.remove(next);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Individual> getIndividualByIdentity(String firstName, String lastName) {
        return em.createNamedQuery("Individual.getByIdentity").setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
    }

    @Override
    public List<Individual> getIndividualByFirstName(String firstName) {
        return em.createQuery("select indiv from Individual indiv where indiv.firstName=:firstName")
                .setParameter("firstName", firstName).getResultList();
    }

    @Override
    public List<Individual> getIndividualByLastName(String lastName) {
         return em.createQuery("select indiv from Individual indiv where indiv.lastName=:lastName")
                .setParameter("lastName", lastName).getResultList();
    }

    @Override
    public Individual get(int idIndividual) {
        return em.find(Individual.class, idIndividual);
    }

    

}
