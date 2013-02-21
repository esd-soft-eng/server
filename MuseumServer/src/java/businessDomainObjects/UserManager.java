package businessDomainObjects;

import java.util.ArrayList;
import persistance.PersistanceRepositoryUser;
import utility.MD5Hasher;

/**
 *
 * @author Alex
 * @author Simon
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
    
    public boolean modifyUser(int userID, String user, String pass){
        if(pr.modifyUser(userID, user, MD5Hasher.hashMD5(pass))){
            return true;
        }
        return false;
    }
}