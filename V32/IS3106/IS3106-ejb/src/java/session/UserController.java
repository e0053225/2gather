package session;

import entity.User;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jiaying
 */

@Stateless
public class UserController implements UserControllerLocal{
    @PersistenceContext
    private EntityManager em;
      /**
       * private Long id;
    private String name;
    private String password;
    private String email;
    private String[] friends;
       */
    
    @Override
    public void createUser(User u) {
        em.persist(u);
        System.out.println("User " + u + " created");
    }

    @Override
    public User editUser(User existing) throws NoResultException {
        try{
            em.merge(existing);
            em.flush();
           
            System.out.println("User updated");
            return existing;
    }
        catch (Exception ex){
            throw new NoResultException("Failed to update user");
        }
    }
   
    
    @Override 
    public List<User> retrieveAllFriends(User u) throws NoResultException {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :inUserId");
        query.setParameter("inUserId", User.class);

        return query.getResultList();
    }
    
      @Override 
    public void addFriend(User u, User friend) throws NoResultException {
        
        try{
        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :inUserId");
        query.setParameter("inUserId", User.class);
        u.getFriends().add(friend);
       System.out.println("Friend "+ friend.getName() +"is added");
    } catch (Exception ex){
        throw new NoResultException("Failed to add friend");
    }
        
    }
    
     @Override 
    public void removeFriend(User u, User friend) throws NoResultException {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :inUserId");
        query.setParameter("inUserId", User.class);
        
        u.getFriends().remove(friend);

       System.out.println("Friend "+ friend.getName() +"is removed");
    }
           
}