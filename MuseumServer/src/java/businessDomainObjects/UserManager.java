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
    private PersistanceRepositoryUser persistance;

    public UserManager(PersistanceRepositoryUser pr) {
        this.persistance = pr;
        this.listOfUsers = this.initUserList();
    }

    private synchronized ArrayList<User> initUserList() {
        return persistance.getAllUsers();
    }

    public User validateUser(String userName, String password) {
        for (User u : listOfUsers) {
            if (u.getUserName().equals(userName) && u.checkPassword(MD5Hasher.hashMD5(password))) {
                return u;
            }
        }
        return null;
    }
    
    public ArrayList<User> getAllUsers(){
        return listOfUsers;
    }
    
    public boolean modifyUser(int userID, String user, String pass, String[] userTypeArray){
        if(persistance.modifyUser(userID, user, MD5Hasher.hashMD5(pass), userTypeArray)){
            return true;
        }
        return false;
    }

    public synchronized boolean removeUser(int userID) {
        
        if (persistance.removeUser(userID)){
            listOfUsers.remove(this.getUserByID(userID));
            return true;
        }
        return false;
    }
    
    public synchronized boolean addUser(String userName, String password) {        
        if (persistance.addUser(userName, password)) {
            int userId = persistance.getLastEnteredUser();
            this.listOfUsers.add(new User(userName, password, userId));
            return true;
        }        
        return false;
    }
    
    public synchronized boolean addUserType(int typeID) {
        int userId = persistance.getLastEnteredUser();
        if (persistance.addUserType(userId, typeID)) {
            // add type to list of users?
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
    
    public String[] getAllUserStrings() {
        String[] listOfUserStrings = new String[listOfUsers.size()];
        
        for (int i = 0; i < listOfUsers.size(); i++) {
            listOfUserStrings[i] = listOfUsers.get(i).getUserID() + "," + 
                    listOfUsers.get(i).getUserName();
        }        
        return listOfUserStrings;
    }
}
