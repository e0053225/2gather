/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.User;
import java.util.List;
import javafx.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.UserControllerLocal;

/**
 *
 * @author
 */
@ManagedBean
@RequestScoped

public class CustomerManagedBean {

    @EJB
    private UserControllerLocal userCtr;

    @ManagedProperty(value = "#{Session}")
    private Session session;
    public String firstname;
    public String lastname;
    public String username;
    public String password;
    public String password2;
    public String email;
    public int age;

    private List<User> users;

    private User selectedUser;
    private String searchType = "NAME";
    private String searchString;

    public CustomerManagedBean() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public UserControllerLocal getUserCtr() {
        return userCtr;
    }

    public void setUserCtr(UserControllerLocal userCtr) {
        this.userCtr = userCtr;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

   
    @PostConstruct //when the container is ready to let the bean work, after injecting refereneces
    public void init() {
       
       
    }

    public void searchTypeHandler() {

    }

    /*public void loadSelectedUser() {
     FacesContext context = FacesContext.getCurrentInstance();

     try {
     this.selectedUser = userCtr.getUser(session.getuId());

     } catch (Exception e) {
     context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to load user"));
     }
     }*/
    public void handleSearch() {
        init();
    }

    public void updateUser() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            userCtr.editUser(selectedUser);
        } catch (Exception e) {
            //show with an error icon
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to update user"));
            return;
        }

        //need to make sure reinitialize the customers collection
        init();
        context.addMessage(null, new FacesMessage("Success", "Successfully updated user"));
    }

   
}
