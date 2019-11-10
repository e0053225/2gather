package entity;

import entity.Event;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-10T18:30:54")
@StaticMetamodel(Notification.class)
public class Notification_ { 

    public static volatile SingularAttribute<Notification, User> notificationReceiver;
    public static volatile SingularAttribute<Notification, Date> date;
    public static volatile SingularAttribute<Notification, Boolean> eventAlert;
    public static volatile SingularAttribute<Notification, Long> id;
    public static volatile SingularAttribute<Notification, Boolean> pollAlert;
    public static volatile SingularAttribute<Notification, Event> event;
    public static volatile SingularAttribute<Notification, String> message;
    public static volatile SingularAttribute<Notification, Boolean> dateAlert;

}