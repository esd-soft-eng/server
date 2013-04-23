package businessDomainObjects;

import java.util.ArrayList;
import persistance.PersistanceRepositoryUser;
import utility.MD5Hasher;

/**
 *
 * @author Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
 * @author Simon
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 * 
 * Provides all of the business logic for the management of users with access
 * to the server side of the system.
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
    
    /*
     * Creates an array list containing all users registered with the system of
     * type User and returns it
     * @return list of users
     */
    public ArrayList<User> getAllUsers(){
        return listOfUsers;
    }
    
    public boolean modifyUser(int userID, String user, String pass, String[] userTypeArray){
        if(persistance.modifyUser(userID, user, MD5Hasher.hashMD5(pass), userTypeArray)){
            return true;
        }
        return false;
    }

    /*
     * Takes the ID of a user to remove and removes them from the system
     * @param user ID
     * @return success/failure
     */
    public synchronized boolean removeUser(int userID) {        
        if (persistance.removeUser(userID)){
            listOfUsers.remove(this.getUserByID(userID));
            return true;
        }
        return false;
    }
    
    /*
     * Takes the user name and password of a user and adds them to the system
     * with these details
     * @param user name
     * @param password
     * @return success/failure
     */
    public synchronized boolean addUser(String userName, String password) {        
        if (persistance.addUser(userName, password)) {
            int userId = persistance.getLastEnteredUser();
            this.listOfUsers.add(new User(userName, password, userId));
            return true;
        }        
        return false;
    }
    
    /*
     * When a new user is added, this method will also be called. It gets the
     * most recent user added to the system (which will be the one we're
     * currently adding), and adds the user type information associated with
     * that user that has been provided through IDs of all user types selected
     * for that user
     * @param type id
     * @return success/failure
     */
    public synchronized boolean addUserType(int typeID) {
        int userId = persistance.getLastEnteredUser();
        if (persistance.addUserType(userId, typeID)) {
            User user = getUserByID(userId);
            switch (typeID) {
                case 1: user.addUserType(UserTypes.UserType.MAINTAINER);
                        break;
                case 2: user.addUserType(UserTypes.UserType.ADMINISTRATOR);
                        break;
                case 3: user.addUserType(UserTypes.UserType.CLIENTHANDSET);
                        break;
                case 4: user.addUserType(UserTypes.UserType.KIOSK);
                        break;
                case 5: user.addUserType(UserTypes.UserType.MANAGER);
                        break;
                default: 
                        break;
            }            
            return true;
        }
        return false;
    }

    /*
     * Loops through a list of all users registered with the system to return
     * the user of type User specified by the provided user ID
     * @param user ID
     * @return user
     */
    public User getUserByID(int userID) {        
        for(User user : listOfUsers){
            if (user.getUserID() == userID){
                return user;
            }
        }
        return null;
    }
    
    /*
     * Loops through and creates a readable list of the names of all users
     * registered with the system to be returned
     * @return list of users' names
     */
    public String[] getAllUserStrings() {
        String[] listOfUserStrings = new String[listOfUsers.size()];
        
        for (int i = 0; i < listOfUsers.size(); i++) {
            listOfUserStrings[i] = listOfUsers.get(i).getUserID() + "," + 
                    listOfUsers.get(i).getUserName();
        }        
        return listOfUserStrings;
    }
}