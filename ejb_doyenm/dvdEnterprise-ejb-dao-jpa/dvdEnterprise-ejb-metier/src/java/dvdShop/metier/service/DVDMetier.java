/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.dao.DVDDaoLocal;
import dvdShop.jpa.DVD;
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
public class DVDMetier implements IDVDMetierLocal, IDVDMetierRemote, Serializable {

    // couche dao
    @EJB
    private DVDDaoLocal dao;

    public DVDMetier() {
    }

    @Override
    public List<DVD> getAllDVDs() {
        return dao.getAllDVDs();
    }

    @Override
    public void addDVD(DVD dvd) {
        // We check if a DVD with the same title and release date already exists
        // If yes, we consider that it is the same DVD and we do not add the one in the DB
        // If no, we add the new DVD
        List<DVD> listDVD = getDVD(dvd.getTitle(), dvd.getYear());
        if (listDVD.isEmpty()) {
            dao.addDVD(dvd);
        }
    }

    @Override
    public List<DVD> getDVD(String title) {
        return dao.getDVDByTitle(title);
    }

    @Override
    public List<DVD> getDVD(int releaseYear) {
        return dao.getDVDByReleaseYear(releaseYear);
    }

    @Override
    public List<DVD> getDVD(String title, int releaseYear) {
        return dao.getDVDByTitleReleaseDate(title, releaseYear);
    }

    @Override
    public List<DVD> getDVD(String title, String genre, int year) {
        // Only one parameter : title
        if (genre.equals("") && year == 0) {
            return getDVD(title);
            // Only one parameter : date
        } else if (genre.equals("") && title.equals("")) {
            return getDVD(year);
            // Only one parameter : genre
        } else if (title.equals("") && year == 0) {
            return dao.getDVDByGenre(genre);
            // Only two parameters : title and year
        } else if (genre.equals("")) {
            return getDVD(title, year);
            // Only two parameters : title and genre
        } else if (year == 0) {
            return dao.getDVDByTitleGenre(title, genre);
            // Only two parameters : genre and year
        } else if (title.equals("")) {
            return dao.getDVDByGenreReleaseYear(genre, year);
            // Only three parameters : title, genre and year
        } else {
            return dao.getDVDByTitleGenreYear(title, genre, year);
        }
    }

    @Override
    public boolean deleteDVD(DVD dvd) {
        return dao.deleteDVD(dvd);
    }

    @Override
    public boolean editDVD(DVD dvd) {
        return dao.editDVD(dvd);
    }

    @Override
    public DVD getDVDById(int id) {
        return dao.getDVDById(id);
    }

    @Override
    public boolean stockDecrement(DVD dvd, int decrement){
        if(dvd.getStock() >= decrement){
            dao.stockDecrement(dvd, decrement);
            return true;
        } else {
            return false;
        }
    }

}
