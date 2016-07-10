/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.metier.service;

import dvdShop.dao.DVDDaoLocal;
import dvdShop.dao.DeliveryDaoLocal;
import dvdShop.jpa.DVD;
import dvdShop.metier.entities.CartItem;
import dvdShop.metier.entities.DeliveryStateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author doyenm
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 5)
public class CartItemMetier implements ICartItemMetierLocal, ICartItemMetierRemote {

    @PersistenceContext//(unitName = "pu", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @EJB
    private DVDDaoLocal daoDVD;

    @EJB
    private DeliveryDaoLocal deliveryDao;

    @EJB
    IDeliveryMetierRemote del;

    CartItem cart;

    @PostConstruct
    private void initializeBean() {
        this.cart = new CartItem();
        cart.setProducts(new ArrayList<>());
    }

    @Override
    public void addDVDToCart(DVD dvd) {
        DVD fullDVD = daoDVD.getDVDById(dvd.getIdDVD());
        cart.getProducts().add(fullDVD);
    }

    @Override
    public List<DVD> getCart() {
        return this.cart.getProducts();
    }

    @Override
    public int deliverCart() throws DeliveryStateException {
        if (!cart.getProducts().isEmpty()) {
            del.add(cart.getProducts());
            this.checkOut();
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void checkOut() {
        List<DVD> dvdList = cart.getProducts();
        Iterator it = dvdList.iterator();
        while (it.hasNext()) {
            DVD dvd = (DVD) it.next();
            it.remove();
        }
    }
}
