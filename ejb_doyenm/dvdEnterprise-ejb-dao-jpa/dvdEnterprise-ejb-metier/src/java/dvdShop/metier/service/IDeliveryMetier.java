/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.jpa.DVD;
import dvdShop.jpa.Delivery;
import dvdShop.metier.entities.DeliveryStateException;
import java.util.List;

/**
 *
 * @author doyenm
 */
public interface IDeliveryMetier {

    public List<Delivery> getAll();

    public void ready(int id) throws DeliveryStateException;

    public void gone(int id) throws DeliveryStateException;
    
    public void add(List<DVD> products)  throws DeliveryStateException ;
    
    public void checkDeliveryState(Delivery delivery)  throws DeliveryStateException ;
   
    public void checkDeliveryState(int idDelivery)  throws DeliveryStateException ;

}
