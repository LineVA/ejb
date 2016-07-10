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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author doyenm
 */
@Entity
@Table(name = "Individual")
@NamedQueries({
    @NamedQuery(name = "Individual.getAll", query = "SELECT indiv FROM Individual indiv"),
    @NamedQuery(name = "Individual.getByIdentity", query = "SELECT indiv FROM Individual indiv WHERE indiv.firstName=:firstName and indiv.lastName=:lastName")
})
public class Individual implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIndividual")
    private int idIndividual;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    public int getIdIndividual() {
        return idIndividual;
    }

    public void setIdIndividual(int idIndividual) {
        this.idIndividual = idIndividual;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Individual() {

    }
    
   @ManyToMany
     @JoinTable(
      name="Intervention",
      joinColumns={@JoinColumn(name="idIndividual", referencedColumnName="idIndividual")},
      inverseJoinColumns={@JoinColumn(name="idDVD", referencedColumnName="idDVD")})
    public List<DVD> dvds;
    
    public List<DVD> getDVDs() {
        return this.dvds;
    }
    
     public void setDVDs(List<DVD> list) {
        this.dvds = list;
    }

    public Individual(int id, String first, String last) {
        this.idIndividual = id;
        this.firstName = first;
        this.lastName = last;
    }
    
     public Individual(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

}
