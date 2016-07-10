/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.Intervention;
import java.util.List;

/**
 *
 * @author doyenm
 */
public interface PartiticipationDao {

    public List<Intervention> getAllParticipations();

    public boolean addParticipation(Intervention participation);

    public boolean deleteParticipation(Intervention participation);

    public List<Intervention> searchIndividual(int idIndividual);

    public List<Intervention> searchDVD(int idDVD);

}
