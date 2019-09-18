/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author valen
 */
@Entity
public class Poll implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private List<User> participants;
    private List<User> userIds;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    private List<Integer> votes;
    private List<String> options;
    private HashMap<Integer, String> vote;
    
    @OneToOne
    private Event event;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poll)) {
            return false;
        }
        Poll other = (Poll) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entity.poll[ id=" + getId() + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the participants
     */
    public List<User> getParticipants() {
        return participants;
    }

    /**
     * @param participants the participants to set
     */
    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    /**
     * @return the userIds
     */
    public List<User> getUserIds() {
        return userIds;
    }

    /**
     * @param userIds the userIds to set
     */
    public void setUserIds(List<User> userIds) {
        this.userIds = userIds;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the votes
     */
    public List<Integer> getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(List<Integer> votes) {
        this.votes = votes;
    }

    /**
     * @return the options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * @return the vote
     */
    public HashMap<Integer, String> getVote() {
        return vote;
    }

    /**
     * @param vote the vote to set
     */
    public void setVote(HashMap<Integer, String> vote) {
        this.vote = vote;
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

    private static class date {

        public date() {
        }
    }

    private static class vote {

        public vote() {
        }
    }

    }
    
