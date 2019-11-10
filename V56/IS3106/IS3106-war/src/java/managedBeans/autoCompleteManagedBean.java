package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Note;
import entity.User;
import error.NoResultException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import session.NotesControllerLocal;
import session.UserControllerLocal;


/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class autoCompleteManagedBean {
     
    @EJB
    private NotesControllerLocal notesControllerLocal;
    @EJB
    private UserControllerLocal userControllerLocal;
    @ManagedProperty(value="#{Session}")
    private Session session;
    
    private Long noteId;

    
    private String title;
    private String message;
    
     
   
     
   
    
    public void addNote() throws NoResultException{
        Note newNote = new Note();
        User selectedUser = userControllerLocal.getUser(session.getuId());
        newNote.setUser(selectedUser);
        newNote.setTitle(title);
        newNote.setMessage(message);
        notesControllerLocal.createNote(newNote);
    }

    public UserControllerLocal getUserControllerLocal() {
        return userControllerLocal;
    }

    public void setUserControllerLocal(UserControllerLocal userControllerLocal) {
        this.userControllerLocal = userControllerLocal;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
     
  
     
    
         
   /* public void onItemSelect(SelectEvent<String> event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject()));
    }  */

    public NotesControllerLocal getNotesControllerLocal() {
        return notesControllerLocal;
    }

    public void setNotesControllerLocal(NotesControllerLocal notesControllerLocal) {
        this.notesControllerLocal = notesControllerLocal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
 
    
 

}