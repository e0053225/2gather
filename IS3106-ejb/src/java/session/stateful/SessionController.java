/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateful;

import entity.User;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@Stateful
@LocalBean
public class SessionController {

    @PersistenceContext
    private EntityManager em;

    /*
     * returns the current session, if the session is null, it creates a new one and returns it
     */
    public HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session == null) {
            session = (HttpSession) context.getExternalContext().getSession(true);
        }
        return session;
    }

    /**
     * Checks if User is Logged in
     *
     * @return true or false
     */
    public boolean isLoggedIn() {
        if (getSession().getAttribute("loggedIn") == null) {
            return false;
        }
        return (Boolean) getSession().getAttribute("loggedIn");
    }

    /**
     * returns the Userobject from the session
     *
     * @return User user
     */
    public User getUser() {
        return (User) getSession().getAttribute("user");
    }

    /*
     * login the User with the correct username + password 
     */
    public boolean login(String username, String password) {

		//log.info("Search for user in DB");
        try {

            Query q = em.createNamedQuery("Userlogin");

            q.setParameter("name", username);
            q.setParameter("password", password);
            User user = (User) q.getSingleResult();
            
            System.out.println("##user : " + user);
            HttpSession session = getSession();

            if (user != null) {

                session.setAttribute("user", user);
                session.setAttribute("loggedIn", Boolean.TRUE);

            } else {
                session.setAttribute("loggedIn", Boolean.FALSE);
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
     * logouts the user and invalidates the session
     */
    public void logout() {
        this.getSession().invalidate();
    }
}
