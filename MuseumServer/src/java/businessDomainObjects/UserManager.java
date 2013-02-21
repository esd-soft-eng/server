package businessDomainObjects;

import java.util.ArrayList;
import persistance.PersistanceRepositoryUser;
import utility.MD5Hasher;

/**
 *
 * @author Alex
 */
public class UserManager {

    private ArrayList<User> listOfUsers;
    private PersistanceRepositoryUser persistance;

    public UserManager(PersistanceRepositoryUser pr) {
        this.persistance = pr;
        this.listOfUsers = this.initUserList();
    }

    private ArrayList<User> initUserList() {

        return persistance.getAllUsers();
    }

    public User validateUser(String userName, String password) {

        for (User u : listOfUsers) {
            if (u.userName.equals(userName) && u.checkPassword(MD5Hasher.hashMD5(password))) {
                return u;
            }
        }
        return null;
    }
    
    public ArrayList<User> getAllUsers(){
        return listOfUsers;
    }

    public synchronized boolean removeUser(int userID) {
        
        if (persistance.removeUser(userID)){
            listOfUsers.remove(this.getUserByID(userID));
            return true;
        }
        return false;
    }

    private User getUserByID(int userID) {
        
        for(User user : listOfUsers){
            if (user.getUserID() == userID){
                return user;
            }
        }
        return null;
    }
}