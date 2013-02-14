package domainObjects;

import java.util.ArrayList;
import persistance.PersistanceRepositoryUser;

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
    
    public ArrayList<User> initUserList() {
        
        pr.getAllUsers();
        
        return new ArrayList<User>();
    }
}
