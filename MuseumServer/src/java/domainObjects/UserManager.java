package domainObjects;

import java.util.ArrayList;
import persistance.PersistanceRepositoryUser;
import utility.MD5Hasher;

/**
 *
 * @author Alex
 */
public class UserManager {
    private ArrayList<User> listOfUsers;
    private PersistanceRepositoryUser pr;
    
    public UserManager(PersistanceRepositoryUser pr) {
        this.pr = pr;
        this.listOfUsers = this.initUserList();
    }
    
    private ArrayList<User> initUserList() {
        
        return pr.getAllUsers();
    }
    
    public boolean validateUser(String userName, String password) {
        
        for (User u : listOfUsers){
            if(u.userName.equals(userName) && u.checkPassword(MD5Hasher.hashMD5(password))){
                return true;
            }
        }
        return false; 
    }
}
