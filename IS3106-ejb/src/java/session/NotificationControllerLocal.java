/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Notification;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jiaying
 */
@Local
public interface NotificationControllerLocal {
    public Notification createNotification(Notification newNotification);
    
    public Notification updateNotification(Notification currNotification) throws NoResultException;
    
    public void deleteNotification(Long notificationId) throws NoResultException;
    
    public Notification getNotification(Long notificationId)throws NoResultException;
    
    public List<Notification> getNotifications(Long userId)throws NoResultException;
    
    
    
    
}
