/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.DVD;
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
@Singleton(mappedName = "dvd.dao")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DVDJpaDao implements DVDDaoLocal, DVDDaoRemote, Serializable {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DVD> getAllDVDs() {
        return em.createNamedQuery("DVD.getAll").getResultList();
    }

    @Override
    public void addDVD(DVD dvd) {
        em.persist(dvd);
    }

    @Override
    public boolean deleteDVD(DVD dvd) {
        List<DVD> dvdSearch = getDVDByTitleReleaseDate(dvd.getTitle(), dvd.getYear());
        if (dvdSearch.size() == 1) {
            DVD next;
            Iterator<DVD> it = dvdSearch.iterator();
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
    public boolean editDVD(DVD dvd) {
        List<DVD> dvdSearch = getDVDByTitleReleaseDate(dvd.getTitle(), dvd.getYear());
        if (dvdSearch.size() == 1) {
            DVD next;
            Iterator<DVD> it = dvdSearch.iterator();
            while (it.hasNext()) {
                next = it.next();
                DVD newDVD = new DVD(next.getIdDVD(), dvd.getTitle(),
                        dvd.getGenre(), dvd.getYear(), dvd.getStock(), dvd.getSupplier());
                em.merge(newDVD);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<DVD> getDVDByTitle(String title) {
        return em.createNamedQuery("DVD.findByTitle", DVD.class).
                setParameter("title", title).getResultList();
    }

    @Override
    public List<DVD> getDVDByTitleReleaseDate(String title, int releaseYear) {
        List<DVD> list = em.createNamedQuery("DVD.findByTitleReleaseDate", DVD.class
        ).setParameter("title", title).setParameter("year", releaseYear).getResultList();
        return list;
    }

    @Override
    public List<DVD> getDVDByReleaseYear(int releaseYear) {
        return em.createNamedQuery("DVD.findByReleaseDate", DVD.class
        ).setParameter("year", releaseYear).getResultList();
    }

    @Override
    public List<DVD> getDVDByGenre(String genre) {
        return em.createQuery("select dvd from DVD dvd where dvd.genre=:genre").setParameter("genre", genre).getResultList();
    }

    @Override
    public List<DVD> getDVDByGenreReleaseYear(String genre, int year) {
        return em.createQuery("select dvd from DVD dvd where dvd.genre=:genre "
                + "and dvd.year=:year").setParameter("genre", genre)
                .setParameter("year", year).getResultList();
    }

    @Override
    public List<DVD> getDVDByTitleGenre(String title, String genre) {
        return em.createQuery("select dvd from DVD dvd where dvd.genre=:genre "
                + "and dvd.title=:title").setParameter("genre", genre)
                .setParameter("title", title).getResultList();
    }

    @Override
    public List<DVD> getDVDByTitleGenreYear(String title, String genre, int year) {
        return em.createQuery("select dvd from DVD dvd where dvd.genre=:genre "
                + "and dvd.title=:title and dvd.year=:year")
                .setParameter("genre", genre).setParameter("title", title)
                .setParameter("year", year).getResultList();
    }

    public DVD getDVDById(int id) {
        return em.find(DVD.class, id);
    }

    @Override
    public void stockDecrement(DVD dvd, int decrement) {
        DVD newDVD = new DVD(dvd.getIdDVD(), dvd.getTitle(), dvd.getGenre(),
                dvd.getYear(), dvd.getStock() - decrement, dvd.getSupplier());
        em.merge(newDVD);
    }

}
