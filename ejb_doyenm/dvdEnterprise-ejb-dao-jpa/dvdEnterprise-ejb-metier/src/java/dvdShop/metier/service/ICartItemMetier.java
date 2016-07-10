/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.jpa.DVD;
import dvdShop.metier.entities.DeliveryStateException;
import java.util.List;

/**
 *
 * @author doyenm
 */
public interface ICartItemMetier {

	  void addDVDToCart(DVD dvd);

          List<DVD> getCart();
	  void checkOut();
          
          int deliverCart() throws DeliveryStateException;

}
