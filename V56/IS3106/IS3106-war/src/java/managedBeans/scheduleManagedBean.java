package managedBeans;

import entity.Event;
import entity.Notification;
import entity.User;
import error.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import session.EventControllerLocal;
import session.NotificationControllerLocal;
import session.UserControllerLocal;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class scheduleManagedBean implements Serializable {

    private ScheduleModel eventModel;

    @EJB
    private EventControllerLocal eventControllerLocal;
    @EJB
    private UserControllerLocal userControllerLocal;
    @EJB
    private NotificationControllerLocal notificationControllerLocal;
    @ManagedProperty(value = "#{Session}")
    private Session session;

    private List<User> selectedUsers;

    private Long eventId;
    private String title;
    private Date startDate;
    private Date endDate;
    private String eventName;
    private String invitee;

    private ScheduleEvent event = new DefaultScheduleEvent();

    @PostConstruct
    public void init() {
        try {
            System.out.println("##in init");
            eventModel = new DefaultScheduleModel();
            eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
            eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
            eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
            eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
            eventModel.addEvent(new DefaultScheduleEvent("suppp", nextDay9Am(), nextDay11Am()));
            viewAllEvents();
            //   eventModel.addEvent(new DefaultScheduleEvent(event.getTitle(),
            //  event.getStartDate(), event.getEndDate()));
//why does this line affects the display of the events
        } catch (NoResultException ex) {
            Logger.getLogger(scheduleManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public scheduleManagedBean() {
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);
        System.out.println(t.getTime());
        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        System.out.println(t.getTime());
        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        System.out.println(t.getTime());
        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);
        System.out.println(t.getTime());
        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    /*  public void addEvent2(Long id) throws NoResultException{
     if(eventM.findEventById(id) == null){
     eventM.createEvent((Event) event);
     }else{
     eventM.updateEvent((Event) event);
     }
     } */

    public List<User> completeUser(String query) {
        List<User> allUsers = userControllerLocal.retrieveAllUsers();
        List<User> filteredUsers = new ArrayList<User>();

        for (int i = 0; i < allUsers.size(); i++) {
            User u = allUsers.get(i);
            if (u.getName().toLowerCase().contains(query)) {
                filteredUsers.add(u);
            }
        }

        return filteredUsers;
    }

    public void addEvent() throws NoResultException {
        Event newEvent = new Event();
        Notification notif = new Notification();
        //String notifName;
        if (event.getId() == null) {
            System.out.println("####session.getuId() : " + session.getuId());
            User selectedUser = userControllerLocal.getUser(session.getuId());

            newEvent.setTitle(event.getTitle());
            newEvent.setStartDate(event.getStartDate());
            newEvent.setEndDate(event.getEndDate());
            newEvent.setUser(selectedUser);
            if (selectedUsers.size() > 0 && selectedUsers != null) {
                newEvent.setInvitees(selectedUsers);
            }
            eventControllerLocal.createEvent(newEvent);
            //sending notifications to invitees
            for (User u : selectedUsers) {
                notif.setNotificationReceiver(u);
                notif.setEventAlert(true);
                notif.setDate(startDate);
                notif.setEvent(newEvent);
                notificationControllerLocal.createNotification(notif);
            }

            DefaultScheduleEvent e1 = new DefaultScheduleEvent(event.getTitle(), event.getStartDate(), event.getEndDate());

            eventModel.addEvent(e1);
            e1.setId("" + newEvent.getId());

        } else {
            System.out.println("###id : " + event.getId());
            //this is what i did. 
            //isn't it schedule Event display the event
            Event e = eventControllerLocal.findEventById(Long.parseLong(event.getId()));

            e.setTitle(event.getTitle());
            e.setStartDate(event.getStartDate());
            e.setEndDate(event.getEndDate());
            if (selectedUsers.size() > 0 && selectedUsers != null) {
                e.setInvitees(selectedUsers);
                for (User u : selectedUsers) {
                    notif.setNotificationReceiver(u);
                    notif.setEventAlert(true);
                    notif.setDate(startDate);
                    notif.setEvent(newEvent);
                    notificationControllerLocal.createNotification(notif);
                }
            }

            //TODO: add the other fields
            eventControllerLocal.updateEvent(e);
        }
        //  event = new DefaultScheduleEvent();   
    }

    public void viewAllEvents() throws NoResultException {
        User selectedUser = userControllerLocal.getUser(session.getuId());
        System.out.println("selectedUser" + selectedUser);
        for (Event e : eventControllerLocal.retrieveAllAvailableEventForUser(selectedUser)) {

            DefaultScheduleEvent e1 = new DefaultScheduleEvent(e.getTitle(), e.getStartDate(), e.getEndDate());

            eventModel.addEvent(e1);
        }

    }

    /*  public void addEvent() {
     if (event.getId() == null) {
     eventModel.addEvent(event);
     } else {
     eventModel.updateEvent(event);
     }

     event = new DefaultScheduleEvent();
     } */
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * @return the invitee
     */
    public String getInvitee() {
        return invitee;
    }

    /**
     * @param invitee the invitee to set
     */
    public void setInvitee(String invitee) {
        this.invitee = invitee;
    }

    /**
     * @return the selectedUsers
     */
    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    /**
     * @param selectedUsers the selectedUsers to set
     */
    public void setSelectedUsers(List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

}
