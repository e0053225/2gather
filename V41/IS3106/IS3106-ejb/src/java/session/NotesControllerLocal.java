package session;

import entity.Notes;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS
 */
@Local
public interface NotesControllerLocal {
    public Notes createNote(Notes newNote);
    
    public Notes updateNote(Notes currEvent) throws NoResultException;
    
    public void deleteNote(Long notesId) throws NoResultException;
    
    public List getNote(Long id)throws NoResultException;
}