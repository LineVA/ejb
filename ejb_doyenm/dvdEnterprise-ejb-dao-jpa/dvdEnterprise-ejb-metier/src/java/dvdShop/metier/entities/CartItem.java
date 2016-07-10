/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.entities;

import dvdShop.jpa.DVD;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class CartItem {
    private List<DVD> products;

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }

    public CartItem(List products) {
        this.products = products;
    }
    
    public CartItem(){
        
    }
}
