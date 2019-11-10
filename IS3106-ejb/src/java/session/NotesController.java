package session;

import entity.Event;
import entity.User;
import entity.Notes;
import error.NoResultException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ASUS
 */
@Stateless
public class NotesController implements NotesControllerLocal {

    
    @PersistenceContext
    private EntityManager em;

    @EJB
  //  private UserControllerLocal userControllerLocal;
    @Override
    public Notes createNote(Notes newNotes) {
        em.persist(newNotes);
        em.flush();

        System.out.println("Create new Event > " + newNotes.getId());
        return newNotes;
    }
    @Override
    public Notes updateNote(Notes currentNotes ) throws NoResultException {
        try {
            em.merge(currentNotes);
            em.flush();
            
            System.out.println("Event updated");
            return currentNotes;
            

        } catch (Exception ex) {
            throw new NoResultException("Failed to Update Event");
        }
    }

    @Override
    public void deleteNote(Long notesId) throws NoResultException {
        try {
            Query query = em.createQuery("SELECT n FROM notes e WHERE n.Id = :id");
            query.setParameter("id", notesId);

           Notes delnote = (Notes) query.getSingleResult();

            em.remove(delnote);
        } catch (Exception ex) {
            throw new NoResultException("Event " + notesId + "does not exist!");
        }
        
    }
    
    @Override
    public List getNote(Long id)throws NoResultException {
        try {
            Query query = em.createQuery("Select n FROM notes WHERE n.id = :id");
            return query.getResultList();       
        }
        catch (Exception ex) {
            throw new NoResultException("No Notes saved");
        }
        
        
    }


}