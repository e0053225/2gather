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
    public void createUser(String username, String password, String email) {
        User u = new User(username,password,email);
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
    public List<User> retrieveAllUsers() {
          Query query = em.createQuery("SELECT u FROM User u");
          return query.getResultList();
    
    }
    @Override 
    public List<User> retrieveAllFriends(User u) throws NoResultException {
       
        Query query = em.createQuery("SELECT u FROM User u where uId = :inId");
        query.setParameter("inId", User.class);

        return query.getResultList();
    }
    
      @Override 
    public void addFriend(User u, User friend) throws NoResultException {
        User newU = em.find(User.class, u.getId());
        User newFriend = em.find(User.class, friend.getId());
        System.out.println("User u is "+ u);
        System.out.println("User friend is "+ friend);
        try{
        //Query query = em.createQuery("SELECT u FROM User newU WHERE newU.id = :inId");
       //query.setParameter("inId", User.class);
        newU.getFriends().add(newFriend);
       System.out.println("Friend "+ newFriend.getName() +"is added");
    } catch (Exception ex){
        throw new NoResultException("Failed to add friend");
    }
        
    }
    
     @Override 
    public void removeFriend(User u, User friend) throws NoResultException {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :inId");
        query.setParameter("inId", u.getId());
        User newU = (User)query.getSingleResult();
        
        Query friendQuery = em.createQuery("SELECT friend FROM User friend WHERE friend.id = :inId");
        friendQuery.setParameter("inId", friend.getId());
        User newFriend = (User)query.getSingleResult();
        
        newU.getFriends().remove(newFriend);
        

       System.out.println("Friend "+ friend.getName() +"is removed");
    }
    @Override
     public List<User> searchUsers(String name) {

        Query q;
        if (name != null) {
            q = em.createQuery("SELECT u FROM User u WHERE LOWER(u.name) LIKE :name");
            q.setParameter("name", "%" + name.toLowerCase() + "%");
        } else {
            q = em.createQuery("SELECT u FROM User u");
        }

        return q.getResultList();
    }
   @Override
    public User getUser(Long uId) throws NoResultException {
        User u = em.find(User.class, uId);

        if (u != null) {
            return u;
        } else {
            throw new NoResultException("Not found");
        }
    }

    public List<User> findAllFriends(String name) {
        
        Query q;
        q = em.createQuery("SELECT f FROM User u, IN(u.friends) f WHERE u.name = ?1");
        q.setParameter(1, name);
      //  User u = (User) q.getSingleResult();
        
        return q.getResultList();
    }

   
           
}