package dvdShop.jpa;

import dvdShop.jpa.DVD;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-10T22:40:36")
@StaticMetamodel(Individual.class)
public class Individual_ { 

    public static volatile SingularAttribute<Individual, String> firstName;
    public static volatile SingularAttribute<Individual, String> lastName;
    public static volatile SingularAttribute<Individual, Integer> idIndividual;
    public static volatile ListAttribute<Individual, DVD> dvds;

}