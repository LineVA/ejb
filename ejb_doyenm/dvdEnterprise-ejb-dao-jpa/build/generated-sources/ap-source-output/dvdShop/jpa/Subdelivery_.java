package dvdShop.jpa;

import dvdShop.jpa.DVD;
import dvdShop.jpa.Delivery;
import dvdShop.jpa.DeliveryState;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-10T22:40:36")
@StaticMetamodel(Subdelivery.class)
public class Subdelivery_ { 

    public static volatile SingularAttribute<Subdelivery, Delivery> delivery;
    public static volatile SingularAttribute<Subdelivery, DeliveryState> subdeliveryState;
    public static volatile SingularAttribute<Subdelivery, DVD> dvd;
    public static volatile SingularAttribute<Subdelivery, Integer> idSubdelivery;

}