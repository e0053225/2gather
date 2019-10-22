/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.singleton;

import entity.Event;
import error.NoResultException;
import java.time.LocalDate;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.EventControllerLocal;

/**
 *
 * @author Admin
 */
@Singleton
@LocalBean
public class DataInitialization {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private EventControllerLocal eventcontroller;

    @PersistenceContext
    private EntityManager em;

    public DataInitialization() {

    }

    @PostConstruct
    public void postConstruct() {
        try {
            eventcontroller.retrieveAllEvents();
        } catch (NoResultException ex) {
            initializeData();
        }

    }
    public void initializeData(){
        eventcontroller.createEvent(new Event("Birthday"));
    }

}
