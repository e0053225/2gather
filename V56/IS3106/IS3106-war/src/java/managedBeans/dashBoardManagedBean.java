package managedBeans;

import entity.Note;
import entity.User;
import error.NoResultException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import session.NotesControllerLocal;
import session.UserControllerLocal;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class dashBoardManagedBean implements Serializable {
     
    private DashboardModel model;
    
    @EJB
    private NotesControllerLocal notesControllerLocal;
    @EJB
    private UserControllerLocal userControllerLocal;
    
    @ManagedProperty(value= "#{Session}")
    private Session session;
    
    private Long notesId;
    private String title;
    private String message = "BIGSHIT";
    private String value;
    
     
    @PostConstruct
    public void init() {
        
            model = new DefaultDashboardModel();
            DashboardColumn column1 = new DefaultDashboardColumn();
            DashboardColumn column2 = new DefaultDashboardColumn();
            DashboardColumn column3 = new DefaultDashboardColumn();
           
            
            column1.addWidget("sports");
            column1.addWidget("finance");
            
            
            column2.addWidget("lifestyle");
            column2.addWidget("weather");
            column2.addWidget("politics");
            
           
            
            
            
            
            model.addColumn(column1);
            model.addColumn(column2);
           
           
            
       
    }

    public NotesControllerLocal getNotesControllerLocal() {
        return notesControllerLocal;
    }

    public void setNotesControllerLocal(NotesControllerLocal notesControllerLocal) {
        this.notesControllerLocal = notesControllerLocal;
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

    public Long getNotesId() {
        return notesId;
    }

    public void setNotesId(Long notesId) {
        this.notesId = notesId;
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
    
    
    
    
     
    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
         
        addMessage(message);
    }
     
    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
         
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addNote() throws NoResultException{
        if(model.getColumn(4) == null){
             System.out.println("####session.getuId() : " + session.getuId());
            User selectedUser = userControllerLocal.getUser(session.getuId());
            Note newNote = null;
            notesControllerLocal.createNote(newNote);
            
            DashboardColumn column4 = new DefaultDashboardColumn();
            column4.addWidget(newNote.getTitle() + " " + newNote.getMessage());
            model.addColumn(column4);
            newNote.setId(newNote.getId());
        }else{
            System.out.println("#Id : " + notesId);
            
            Note n = notesControllerLocal.findNoteById(notesId);
            n.setTitle(n.getTitle());
            notesControllerLocal.updateNote(n);
        }
        
    }
    
     
    public DashboardModel getModel() {
        return model;
    }
    
    public void viewAllNotes() throws NoResultException{
        User selectedUser = userControllerLocal.getUser(session.getuId());
        System.out.println("selectedUser" + selectedUser);
        for(Note n : notesControllerLocal.retrieveAllNotesForUser(selectedUser)){
            
            model = new DefaultDashboardModel();
            DashboardColumn column4 = new DefaultDashboardColumn();
            column4.addWidget(n.getTitle());
            model.addColumn(column4);
        }
    }
    
    public String viewNoteTitleForUserById(Long id) throws NoResultException{
       User selectedUser = userControllerLocal.getUser(session.getuId());
      
       List<Note> listOfNotes = notesControllerLocal.retrieveAllNotesForUser(selectedUser);
       for(Note n : listOfNotes){
           if(id == n.getId()){
               return n.getTitle();
           }
       }
       return "NOT WORKING";
    }
     public String viewNoteMessageForUserById(Long id) throws NoResultException{
       User selectedUser = userControllerLocal.getUser(session.getuId());
      
       List<Note> listOfNotes = notesControllerLocal.retrieveAllNotesForUser(selectedUser);
       for(Note n : listOfNotes){
           if(id == n.getId()){
               return n.getMessage();
           }
       }
       return "NOT WORKING";
    }
    
    
    
    public String viewNoteTitleById(Long id) throws NoResultException{
         for(Note n: notesControllerLocal.retrieveAllNotes()){
           System.out.print( n.toString() + "hihhooohooho");
            if(id == n.getId()){
              return n.getTitle();
            }
        }
        return "it is working NOT";
    }
    
    public String viewNoteById(Long id)throws NoResultException{
        
        for(Note n: notesControllerLocal.retrieveAllNotes()){
           System.out.print( n.toString() + "hihhooohooho");
            if(id == n.getId()){
              return n.getMessage();
            }
        }
        return "it is working NOT";
    }
}