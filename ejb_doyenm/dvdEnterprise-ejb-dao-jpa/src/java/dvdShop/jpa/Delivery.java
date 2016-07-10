/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author doyenm
 */
@Entity
@Table(name = "Delivery")
@NamedQueries({
    @NamedQuery(name = "Delivery.getAll", query = "SELECT del FROM Delivery del")
})
public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDelivery")
    private int idDelivery;
    @Column(name = "stateDelivery")
    private DeliveryState deliveryState;
    @OneToMany(mappedBy = "delivery")
    private List<Subdelivery> subdeliveries;

    public int getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(int idDelivery) {
        this.idDelivery = idDelivery;
    }

    public DeliveryState getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(DeliveryState deliveryState) {
        this.deliveryState = deliveryState;
    }

    public List<Subdelivery> getSubdeliveries() {
        return subdeliveries;
    }

    public void setSubdeliveries(List<Subdelivery> subDeliveries) {
        this.subdeliveries = subDeliveries;
    }

    public Delivery(int id, DeliveryState state) {
        this.idDelivery = id;
        this.deliveryState = state;
    }
    
    public Delivery(DeliveryState state){
        this.deliveryState = state;
    }

    public Delivery() {

    }
}
