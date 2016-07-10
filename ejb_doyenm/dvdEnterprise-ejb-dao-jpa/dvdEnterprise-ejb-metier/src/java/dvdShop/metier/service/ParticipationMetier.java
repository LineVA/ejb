/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.dao.DVDDaoLocal;
import dvdShop.dao.IndividualDaoLocal;
import dvdShop.dao.ParticipationDaoLocal;
import dvdShop.jpa.DVD;
import dvdShop.jpa.Individual;
import dvdShop.jpa.Intervention;
import dvdShop.metier.entities.ExtendsIntervention;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ParticipationMetier implements IParticipationMetierLocal, IParticipationMetierRemote, Serializable {

    // couche dao
    @EJB
    private ParticipationDaoLocal dao;

    @EJB
    private IndividualDaoLocal daoIndiv;

    @EJB
    private DVDDaoLocal daoDVD;

    @EJB
    private IDVDMetierRemote dvdMetier;

    @EJB
    private IIndividualMetierRemote individualMetier;

    public ParticipationMetier() {

    }

    public ArrayList<ExtendsIntervention> extendsIntervention(List<Intervention> intervList) {
        Iterator it = intervList.iterator();
        ArrayList<ExtendsIntervention> extendsIntervList = new ArrayList<>();
        Intervention next;
        Individual individual;
        DVD dvd;
        while (it.hasNext()) {
            next = (Intervention) it.next();
            individual = individualMetier.get(next.getIdIndividual());
            dvd = dvdMetier.getDVDById(next.getIdDVD());
            extendsIntervList.add(new ExtendsIntervention(dvd.getTitle(),
                    dvd.getYear(), individual.getFirstName(),
                    individual.getLastName()));
        }
        return extendsIntervList;
    }

    @Override
    public ArrayList<ExtendsIntervention> getAllParticipations() {
        List<Intervention> intervList = dao.getAllParticipations();
        /* Because I do not know how obtain the DVD's and Individual's information
         from Participation, I create a new list by myself with 
         the information I want. The problem is, I need to do several BD's requests.
         But it is better than nothing.
         */
        return extendsIntervention(intervList);
    }

    @Override
    public boolean addParticipation(String title, int year, String firstName, String lastName) {
        List<Individual> listIndiv = daoIndiv.getIndividualByIdentity(firstName, lastName);
        List<DVD> listDVD = daoDVD.getDVDByTitleReleaseDate(title, year);
        if (listDVD.size() == 1 && listIndiv.size() == 1) {
            Individual nextIndiv;
            Iterator<Individual> itIndiv = listIndiv.iterator();
            // because the sizes of the list are equals to 1, we know they 
            // have a next element
            nextIndiv = itIndiv.next();
            DVD nextDVD;
            Iterator<DVD> itDVD = listDVD.iterator();
            nextDVD = itDVD.next();
            Intervention particip = new Intervention(nextIndiv.getIdIndividual(), nextDVD.getIdDVD());
            dao.addParticipation(particip);
        }
        return true;
    }

   

    @Override
    public ArrayList<ExtendsIntervention> searchByIdentity(ExtendsIntervention intervention) {
        List<Individual> indiv = individualMetier.getIndividualByIdentity(intervention.getFirstName(), intervention.getLastName());
        Iterator it = indiv.iterator();
        Individual next;
        if (it.hasNext()) {
            next = (Individual) it.next();
            return extendsIntervention(dao.searchIndividual(next.getIdIndividual()));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ExtendsIntervention> searchByDVD(ExtendsIntervention intervention) {
        List<DVD> dvd = dvdMetier.getDVD(intervention.getTitle(), intervention.getYear());
        Iterator it = dvd.iterator();
        DVD next;
        if (it.hasNext()) {
            next = (DVD) it.next();
            return extendsIntervention(dao.searchDVD(next.getIdDVD()));
        } else {
            return null;
        }
    }

}
