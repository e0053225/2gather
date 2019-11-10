/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.UserControllerLocal;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class RegisterBean {

    @EJB
    private UserControllerLocal userCtr;
    
    public String username;
    public String password;
    public String password2;
    public String email;
    
    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

  
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    
    public String register(){
        try{
           
            userCtr.createUser(username,password,email);
            
        } catch(Exception e){
            System.err.println("User not created");
        }
        return "index";
    }
}
