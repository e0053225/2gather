/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import session.stateful.SessionController;

/**
 *
 * @author
 */
@ManagedBean(name = "Session")
@SessionScoped
public class Session implements Serializable {

    @EJB
    private SessionController sessionCtr;

    //User specific Informations
    private String username;
    private String password;

    private Long uId;

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean loggedIn() {
        return sessionCtr.isLoggedIn();
    }

    public String login() {
//		log.info(username + password);
        System.out.println("###username : " + username);
        System.out.println("###password : " + password);

        if (sessionCtr.login(username, password) == true) {
            setuId(sessionCtr.getUser().getId());

            return "schedule.xhtml?faces-redirect=true";
        }

        return "error.xhtml?faces-redirect=true";
    }

    public String logout() {
        sessionCtr.logout();
        setuId(null);
        setUsername(null);
        setPassword(null);

        return "index.xhtml?faces-redirect=true";
    }
}
