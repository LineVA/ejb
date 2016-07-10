/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdShop.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author doyenm
 */
@Entity
@Table(name = "Subdelivery")
public class Subdelivery implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubdelivery")
    private int idSubdelivery;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDelivery")
    private Delivery delivery;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDVD")
    private DVD dvd;
    @Column(name = "stateSubdelivery")
    private DeliveryState subdeliveryState;

    public int getIdSubdelivery() {
        return idSubdelivery;
    }

    public void setIdSubdelivery(int idSubdelivery) {
        this.idSubdelivery = idSubdelivery;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public DVD getDvd() {
        return this.dvd;
    }

    public void setDvd(DVD dvd) {
        this.dvd = dvd;
    }

    public DeliveryState getSubdeliveryState() {
        return subdeliveryState;
    }

    public void setSubdeliveryState(DeliveryState subdeliveryState) {
        this.subdeliveryState = subdeliveryState;
    }

    public Subdelivery(int idSubdelivery, Delivery delivery, DeliveryState subdeliveryState, DVD dvd) {
        this.idSubdelivery = idSubdelivery;
        this.delivery = delivery;
        this.subdeliveryState = subdeliveryState;
        this.dvd = dvd;
    }
    
    public Subdelivery(Delivery delivery, DeliveryState state, DVD dvd){
        this.delivery = delivery;
        this.subdeliveryState = state;
        this.dvd = dvd;
    }

    public Subdelivery(int idSubdelivery, DeliveryState state){
        this.idSubdelivery = idSubdelivery;
        this.subdeliveryState = state;
    }
    
    public Subdelivery() {
    }
}
