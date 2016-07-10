package dvdShop.jpa;

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
@Table(name = "Intervention")
@NamedQueries({
    @NamedQuery(name = "Intervention.getAll", query = "SELECT interv FROM Intervention interv")
})
public class Intervention implements java.io.Serializable {
     @Id
    @Column(name="idIndividual")
    private int idIndividual;
    
    @Id
    @Column(name="idDVD")
    private int idDVD;
    
    

    public int getIdIndividual() {
        return idIndividual;
    }

    public void setIdIndividual(int individual) {
        this.idIndividual = individual;
    }

    public int getIdDVD() {
        return idDVD;
    }

    public void setIdDVD(int dvd) {
        this.idDVD = dvd;
    }
  
    
    public Intervention(){
        
    }

    public Intervention(String title, int year, String firstName, String lastName){
        
    }
    
    public Intervention(int indiv,int  dvd){
        this.idDVD = dvd;
        this.idIndividual = indiv;
    }
}
