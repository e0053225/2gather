/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jiaying
 */
@Entity
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    @ManyToOne
    private User notificationReceiver;
    
    @OneToOne
    private Event event;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    private String message;
    
    private boolean dateAlert;
    private boolean pollAlert;
    private boolean eventAlert;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Notification[ id=" + id + " ]";
    }

    /**
     * @return the notificationReceiver
     */
    public User getNotificationReceiver() {
        return notificationReceiver;
    }

    /**
     * @param notificationReceiver the notificationReceiver to set
     */
    public void setNotificationReceiver(User notificationReceiver) {
        this.notificationReceiver = notificationReceiver;
    }

    /**
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the dateAlert
     */
    public boolean isDateAlert() {
        return dateAlert;
    }

    /**
     * @param dateAlert the dateAlert to set
     */
    public void setDateAlert(boolean dateAlert) {
        this.dateAlert = dateAlert;
    }

    /**
     * @return the pollAlert
     */
    public boolean isPollAlert() {
        return pollAlert;
    }

    /**
     * @param pollAlert the pollAlert to set
     */
    public void setPollAlert(boolean pollAlert) {
        this.pollAlert = pollAlert;
    }

    /**
     * @return the eventAlert
     */
    public boolean isEventAlert() {
        return eventAlert;
    }

    /**
     * @param eventAlert the eventAlert to set
     */
    public void setEventAlert(boolean eventAlert) {
        this.eventAlert = eventAlert;
    }
    
}
