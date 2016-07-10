/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.jpa;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public class DVDSupplier implements Serializable {

    @Getter
    private int idDVD;
    @Getter
    @Setter
    private String supplierStr;
    @Getter
    @Setter
    private int stock;
    @Getter
    private DVD dvd;
    @Getter
    @Setter
    private Supplier supplier;

    public DVDSupplier() {
    }

    public DVDSupplier(DVDSupplierPK pk, int stock) {
        this.idDVD = pk.getIdDVD();
        this.supplierStr = pk.getSupplier();
        this.stock = stock;
    }

    public DVDSupplier(Supplier sup, DVD dvd, int stock) {
        this.dvd = dvd;
        this.stock = stock;
        this.supplier = sup;
    }
}
