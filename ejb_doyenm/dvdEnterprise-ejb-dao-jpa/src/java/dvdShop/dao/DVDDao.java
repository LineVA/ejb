/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.DVD;
import java.util.List;

/**
 *
 * @author doyenm
 */
public interface DVDDao {

    public List<DVD> getAllDVDs();

    public void addDVD(DVD dvd);

    public List<DVD> getDVDByTitle(String title);

    public List<DVD> getDVDByTitleReleaseDate(String title, int releaseYear);

    public List<DVD> getDVDByReleaseYear(int releaseYear);

    public List<DVD> getDVDByGenre(String genre);

    public List<DVD> getDVDByGenreReleaseYear(String genre, int year);

    public List<DVD> getDVDByTitleGenre(String title, String genre);

    public List<DVD> getDVDByTitleGenreYear(String title, String genre, int year);

    public boolean deleteDVD(DVD dvd);

    public boolean editDVD(DVD dvd);

    public DVD getDVDById(int id);
    
    public void stockDecrement(DVD dvd, int decrement);
}
