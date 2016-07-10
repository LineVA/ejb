/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.jpa.Delivery;
import dvdShop.jpa.Subdelivery;
import dvdShop.metier.entities.DeliveryStateException;
import java.util.List;

/**
 *
 * @author doyenm
 */
public interface ISubdeliveryMetier {

    void add(Subdelivery subdelivery);

    List<Subdelivery> getByDelivery(Delivery delivery);

    void ready(int idSubdelivery) throws DeliveryStateException;

    void gone(int idSubdelivery) throws DeliveryStateException;

}
