package session;

import entity.User;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jiaying
 */
@Local
public interface UserControllerLocal  {
    
    public void createUser(String username, String password, String email);
    public User editUser(User existing) throws NoResultException;
    public List<User> retrieveAllFriends(User u) throws NoResultException;

    public void addFriend(User u, User friend) throws NoResultException;

    public void removeFriend(User u, User friend) throws NoResultException;
    public List<User> searchUsers(String name);
    public User getUser(Long uId) throws NoResultException;
    public List<User> findAllFriends(String name);
    public List<User> retrieveAllUsers();
    
}