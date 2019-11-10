package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-10T18:30:54")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, Date> date;
    public static volatile SingularAttribute<Event, Date> endDate;
    public static volatile ListAttribute<Event, User> invitees;
    public static volatile SingularAttribute<Event, Long> id;
    public static volatile SingularAttribute<Event, String> title;
    public static volatile SingularAttribute<Event, User> user;
    public static volatile SingularAttribute<Event, Date> startDate;

}