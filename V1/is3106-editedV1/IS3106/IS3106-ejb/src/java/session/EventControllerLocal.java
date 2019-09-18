/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Event;
import entity.User;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface EventControllerLocal {
 
     public Event createEvent(Event newEvent);
    
    public Event updateEvent(Event currEvent) throws NoResultException;
    
    public List<Event> retrieveAllEvents() throws NoResultException;
    
    public List<Event> retrieveAllAvailableEventForUser(User currUser);
    
    public void deleteEvent(Long eventId) throws NoResultException;
    
    public Event findEventById(Long id);
}
