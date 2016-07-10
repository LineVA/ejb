/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.dao;

import dvdShop.jpa.Delivery;
import dvdShop.jpa.DeliveryState;
import dvdShop.jpa.Subdelivery;
import java.util.List;

/**
 *
 * @author doyenm
 */
public interface SubdeliveryDao {

    void add(Subdelivery subdelivery);

    List<Subdelivery> getByDelivery(Delivery delivery);

    Subdelivery get(int idSubdelivery);

    void ready(Subdelivery subdelivery);

    void gone(Subdelivery subdelivery);

}
