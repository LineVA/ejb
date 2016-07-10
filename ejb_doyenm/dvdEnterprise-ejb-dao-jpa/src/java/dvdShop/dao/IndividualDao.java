/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.Individual;
import java.util.List;

/**
 *
 * @author doyenm
 */
public interface IndividualDao {

    public Individual get(int idIndividual);

    public List<Individual> getAllIndividuals();

    public List<Individual> getIndividualByIdentity(String firstName, String lastName);

    public List<Individual> getIndividualByFirstName(String firstName);

    public List<Individual> getIndividualByLastName(String lastName);

    public boolean addIndividual(Individual individual);

    public boolean deleteIndividual(Individual individual);
}
