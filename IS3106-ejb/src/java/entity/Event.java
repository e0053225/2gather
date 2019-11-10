package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 
 */
@Entity
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    private User user;

    
    private List<User> invitees;
    
    

    public Event() {
    }

    public Event(String title) {
        this.title = title;
    }
    

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
        
    }

    public Event(String title, Date startDate, Date endDate, User user) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }
    
     public Event(String title, Date startDate, Date endDate, User user, List<User> invitees) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.invitees = invitees;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

    

    
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Event[ id=" + id + " ]";
    }

    /**
     * @return the invitees
     */
    public List<User> getInvitees() {
        return invitees;
    }

    /**
     * @param invitees the invitees to set
     */
    public void setInvitees(List<User> invitees) {
        this.invitees = invitees;
    }
    
}