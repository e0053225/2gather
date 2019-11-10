/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Notes;
import entity.Notification;
import entity.User;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jiaying
 */
@Stateless
public class NotificationController implements NotificationControllerLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Notification createNotification(Notification newNotification) {
        em.persist(newNotification);
        em.flush();
        System.out.println("Create new Notification > " + newNotification.getId());
        return newNotification;
    }
    
    
    @Override
    public Notification updateNotification(Notification currNotification) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteNotification(Long notificationId) throws NoResultException {
       try {
            Query query = em.createQuery("SELECT n FROM notes e WHERE n.Id = :id");
            query.setParameter("id", notificationId);

           Notification delnot = (Notification) query.getSingleResult();

            em.remove(delnot);
        } catch (Exception ex) {
            throw new NoResultException("Notification " + notificationId + "does not exist!");
        }
    }

    @Override
    public Notification getNotification(Long notificationId) throws NoResultException {
        Notification n = em.find(Notification.class, notificationId);
        return n;
    }

    @Override
    public List<Notification> getNotifications(Long userId) throws NoResultException {
        User u = em.find(User.class, userId);
        return u.getNotifications();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
