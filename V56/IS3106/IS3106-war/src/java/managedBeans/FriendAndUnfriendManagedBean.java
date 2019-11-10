/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.User;
import error.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import session.UserController;
import session.UserControllerLocal;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class FriendAndUnfriendManagedBean {

    @EJB
    private UserControllerLocal userCtr;
    @ManagedProperty(value="#{Session}")
    private Session session;
    
    private User selectedUser;
    /**
     * Creates a new instance of FriendAndUnfriendManagedBean
     */
    public FriendAndUnfriendManagedBean() {
    }
    
    public void friend(User friend){
        try {
            
            userCtr.addFriend(selectedUser,friend);
            init();
        } catch (NoResultException ex) {
            Logger.getLogger(FriendAndUnfriendManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void unFriend(User friend){
        try {
            userCtr.removeFriend(selectedUser,friend);
            init();
        } catch (NoResultException ex) {
            Logger.getLogger(FriendAndUnfriendManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PostConstruct
    public void init(){
        try {
            this.selectedUser = userCtr.getUser(session.getuId());
        } catch (NoResultException ex) {
            Logger.getLogger(FriendAndUnfriendManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserControllerLocal getUserCtr() {
        return userCtr;
    }

    public void setUserCtr(UserControllerLocal userCtr) {
        this.userCtr = userCtr;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    
    
}
