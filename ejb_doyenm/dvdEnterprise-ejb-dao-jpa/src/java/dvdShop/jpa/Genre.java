/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.jpa;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author doyenm
 */
@Entity
@Table(name = "Genre")
@NamedQueries({
@NamedQuery(name = "Genre.getAll", query = "SELECT genre FROM Genre genre")})
public class Genre implements Serializable {

    @Id
    @Column(name = "genre")
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Genre(String genre) {
        this.genre = genre;
    }

    public Genre() {
    }
}
