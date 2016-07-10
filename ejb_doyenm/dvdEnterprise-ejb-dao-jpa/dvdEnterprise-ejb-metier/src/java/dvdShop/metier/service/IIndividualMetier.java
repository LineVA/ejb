/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.jpa.Individual;
import java.util.List;

/**
 *
 * @author doyenm
 */
public interface IIndividualMetier {

    public Individual get(int idIndividual);

    public List<Individual> getAllIndividuals();

    public List<Individual> getIndividualByIdentity(String firstName, String lastName);

    public boolean addIndividual(Individual individual);

    public boolean deleteIndividual(Individual individual);
}
