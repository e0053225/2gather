package entity;

import entity.Event;
import entity.User;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-10T18:30:54")
@StaticMetamodel(Poll.class)
public class Poll_ { 

    public static volatile SingularAttribute<Poll, Date> endDate;
    public static volatile ListAttribute<Poll, User> userIds;
    public static volatile SingularAttribute<Poll, String> name;
    public static volatile SingularAttribute<Poll, List> options;
    public static volatile SingularAttribute<Poll, List> votes;
    public static volatile SingularAttribute<Poll, Long> id;
    public static volatile SingularAttribute<Poll, Event> event;
    public static volatile SingularAttribute<Poll, HashMap> vote;
    public static volatile ListAttribute<Poll, User> participants;

}