/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.DVD;
import dvdShop.jpa.Delivery;
import java.util.List;

/**
 *
 * @author doyenm
 */

public interface DeliveryDao {
    Delivery add(Delivery delivery);
    
    List<Delivery> getAll();
    
    Delivery get(int id);
    
    void ready(Delivery delivery);

    void gone(Delivery delivery);

}
