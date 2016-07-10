/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author doyenm
 */
@Entity
@Table(name = "Supplier")
@NamedQueries({
})
public class Supplier implements Serializable {
    @Id
    @Column(name = "supplier")
    private String supplier;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.PERSIST)
    private List<DVD> dvds;

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<DVD> getDvds() {
        return dvds;
    }
 
    public void setDvds(List<DVD> dvds) {
        this.dvds = dvds;
    }
    
    public Supplier(){
        
    }
    
    public Supplier(String sup){
        this.supplier = sup;
    }
}