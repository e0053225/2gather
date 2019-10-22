package session;

import entity.Poll;
import error.NoResultException;
import javax.ejb.Local;

/**
 *
 * @author valen
 */
@Local
public interface PollSessionLocal {

    public void createPoll(Poll p);

    public void deletePoll(Long pId) throws NoResultException;

    public void editPoll(Poll p) throws NoResultException;
    
}