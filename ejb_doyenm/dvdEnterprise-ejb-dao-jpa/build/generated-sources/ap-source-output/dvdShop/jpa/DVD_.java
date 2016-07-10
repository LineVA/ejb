package dvdShop.jpa;

import dvdShop.jpa.Individual;
import dvdShop.jpa.Subdelivery;
import dvdShop.jpa.Supplier;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-10T22:40:36")
@StaticMetamodel(DVD.class)
public class DVD_ { 

    public static volatile SingularAttribute<DVD, Integer> idDVD;
    public static volatile ListAttribute<DVD, Subdelivery> subdeliveries;
    public static volatile SingularAttribute<DVD, Integer> year;
    public static volatile SingularAttribute<DVD, Supplier> supplier;
    public static volatile SingularAttribute<DVD, String> genre;
    public static volatile SingularAttribute<DVD, String> title;
    public static volatile SingularAttribute<DVD, Integer> stock;
    public static volatile ListAttribute<DVD, Individual> individuals;

}