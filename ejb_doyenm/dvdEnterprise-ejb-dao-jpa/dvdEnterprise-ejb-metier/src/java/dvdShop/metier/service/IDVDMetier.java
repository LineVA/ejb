/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.jpa.DVD;
import java.util.List;


/**
 *
 * @author doyenm
 */
public interface IDVDMetier {

    public List<DVD> getAllDVDs();

    public void addDVD(DVD dvd);
    
    public boolean deleteDVD(DVD dvd);
    
    public boolean editDVD(DVD dvd);

    public List<DVD> getDVD(String title);

    public List<DVD> getDVD(int releaseYear);

    public List<DVD> getDVD(String title, int releaseYear);

    public List<DVD> getDVD(String title, String genre, int year);
    
    public DVD getDVDById(int id);

    public boolean stockDecrement(DVD dvd, int decrement);
}
