/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.jpa;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author doyenm
 */
public class DVDSupplierPK implements Serializable{
    private int idDVD;

  private String supplier;

    public DVDSupplierPK(int idDVD, String supplier) {
        this.idDVD = idDVD;
        this.supplier = supplier;
    }

    public int getIdDVD() {
        return idDVD;
    }

    public void setIdDVD(int idDVD) {
        this.idDVD = idDVD;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.idDVD;
        hash = 13 * hash + Objects.hashCode(this.supplier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVDSupplierPK other = (DVDSupplierPK) obj;
        if (this.idDVD != other.idDVD) {
            return false;
        }
        if (!Objects.equals(this.supplier, other.supplier)) {
            return false;
        }
        return true;
    }

  
}
