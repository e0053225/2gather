/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Poll;
import error.NoResultException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author valen
 */
@Stateless
public class PollSession implements PollSessionLocal {

    @PersistenceContext
    private EntityManager em;

    public void createPoll(Poll p) {
        em.persist(p);
    }

    public void editPoll(Poll p) throws NoResultException {
        Poll currPoll = em.find(Poll.class, p.getId());

        if (currPoll != null) {
            currPoll.setName(p.getName());
            currPoll.setEndDate(p.getEndDate());
            currPoll.setOptions(p.getOptions());
            currPoll.setParticipants(p.getParticipants());
            currPoll.setUserIds(p.getUserIds());
            currPoll.setVotes(p.getVotes());
        } else {
            throw new NoResultException("Not Found");
        }

    }
    
    public void deletePoll(Long pId) throws NoResultException{
        Poll p = em.find(Poll.class, pId);
        if ( p == null){
            throw new NoResultException("Not found!");
        }
        else{
            em.remove(p);
        }
    }
}
