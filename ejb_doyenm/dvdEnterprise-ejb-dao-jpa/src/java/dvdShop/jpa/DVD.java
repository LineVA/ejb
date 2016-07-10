/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Entity
@Table(name = "DVD")
@NamedQueries({
    @NamedQuery(name = "DVD.getAll", query = "SELECT dvd FROM DVD dvd"),
    @NamedQuery(name = "DVD.findByTitle", query = "SELECT dvd FROM DVD dvd WHERE dvd.title=:title"),
    @NamedQuery(name = "DVD.findByTitleReleaseDate", query = "SELECT dvd FROM DVD dvd WHERE dvd.title=:title and dvd.year=:year"),
    @NamedQuery(name = "DVD.findByReleaseDate", query = "SELECT dvd FROM DVD dvd WHERE dvd.year=:year")

})
public class DVD implements Serializable {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDVD")
    private int idDVD;

    @Getter @Setter
    @Column(name = "title")
    private String title;

    @Getter @Setter
    @Column(name = "genre")
    private String genre;

    @Getter @Setter
    @Column(name = "stock")
    private int stock;

    @Getter @Setter
    @Column(name = "dateDVD")
    private int year;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "Intervention",
            joinColumns = {
                @JoinColumn(name = "idDVD", referencedColumnName = "idDVD")},
            inverseJoinColumns = {
                @JoinColumn(name = "idIndividual", referencedColumnName = "idIndividual")})
    private List<Individual> individuals;

    @Getter @Setter 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier")
    private Supplier supplier;

    @Getter @Setter
    @OneToMany(mappedBy = "dvd")
    private List<Subdelivery> subdeliveries;

    public DVD() {
    }

    public DVD(int id, String title, String genre, int year, int stock,
            Supplier supplier) {
        this.idDVD = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.stock = stock;
        this.supplier = supplier;
    }

    public DVD(String title, String genre, int year, int stock,
            Supplier supplier) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.stock = stock;
        this.supplier = supplier;
    }

    public DVD(int id) {
        this.idDVD = id;
    }

}
