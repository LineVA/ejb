/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.jpa.Intervention;
import dvdShop.metier.entities.ExtendsIntervention;
import java.util.ArrayList;

/**
 *
 * @author doyenm
 */
public interface IParticipationMetier {

    public ArrayList<ExtendsIntervention> getAllParticipations();

    public boolean addParticipation(String title, int year, String firstName,
            String lastName);


    public ArrayList<ExtendsIntervention> searchByIdentity(ExtendsIntervention intervention);

    public ArrayList<ExtendsIntervention> searchByDVD(ExtendsIntervention intervention);

}
