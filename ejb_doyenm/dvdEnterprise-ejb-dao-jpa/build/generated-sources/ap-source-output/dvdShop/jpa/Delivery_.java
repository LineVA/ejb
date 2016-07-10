package dvdShop.jpa;

import dvdShop.jpa.DeliveryState;
import dvdShop.jpa.Subdelivery;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-10T22:40:36")
@StaticMetamodel(Delivery.class)
public class Delivery_ { 

    public static volatile ListAttribute<Delivery, Subdelivery> subdeliveries;
    public static volatile SingularAttribute<Delivery, Integer> idDelivery;
    public static volatile SingularAttribute<Delivery, DeliveryState> deliveryState;

}