/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.dao.IndividualDaoLocal;
import dvdShop.jpa.Individual;
import java.io.Serializable;
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
public class IndividualMetier implements IIndividualMetierLocal, IIndividualMetierRemote, Serializable {

    @EJB
    private IndividualDaoLocal dao;

    public IndividualMetier() {
    }

    
    
    @Override
    public List<Individual> getAllIndividuals() {
        return dao.getAllIndividuals();
    }

    @Override
    public List<Individual> getIndividualByIdentity(String firstName, String lastName) {
        if (!firstName.equals("") && !lastName.equals("")) {
            return dao.getIndividualByIdentity(firstName, lastName);
        } else if (!firstName.equals("")) {
            return dao.getIndividualByFirstName(firstName);
        } else if (!lastName.equals("")) {
            return dao.getIndividualByLastName(lastName);
        } else {
            return null;
        }
    }

    @Override
    public boolean addIndividual(Individual individual) {
        List<Individual> identityList = dao.getIndividualByIdentity(
                individual.getFirstName(), individual.getLastName());
        if (identityList.isEmpty()) {
            return dao.addIndividual(individual);
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteIndividual(Individual individual) {
        return dao.deleteIndividual(individual);
    }

    @Override
    public Individual get(int idIndividual) {
        return dao.get(idIndividual);
    }

}
