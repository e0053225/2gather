/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.User;
import error.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import session.UserController;
import session.UserControllerLocal;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class FriendsManagedBean {

    @EJB
    private UserControllerLocal userCtr;
    
    @ManagedProperty(value="#{Session}")
    private Session session;
    

    
    private User selectedUser;
    
    private List<User> friends;
    private List<User> users;
    
    private List<User> selectedUsers;
    
    /**
     * Creates a new instance of FriendsManagedBean
     */
    public FriendsManagedBean() {
    }
    
   
    @PostConstruct
    public void init(){
        try {
            this.selectedUser = userCtr.getUser(session.getuId());
            friends = userCtr.findAllFriends(selectedUser.getName());
        } catch (NoResultException ex) {
            Logger.getLogger(FriendsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserControllerLocal getUserCtr() {
        return userCtr;
    }
    
    public List<User> completeUser(String query) {
        List<User> allUsers = userCtr.retrieveAllUsers();
        List<User> filteredUsers = new ArrayList<User>();
         
        for (int i = 0; i < allUsers.size(); i++) {
            User u = allUsers.get(i);
            if(u.getName().toLowerCase().contains(query)) {
                filteredUsers.add(u);
            }
        }
         
        return filteredUsers;
    }
     
     
    public void getAllUsers(){
       
       users = userCtr.retrieveAllUsers();
    }

    public void setUserCtr(UserController userCtr) {
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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
