package businessDomainObjects;

import businessDomainObjects.UserTypes.UserType;
import java.util.HashSet;

/**
 *
 * @author Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
 * @author Oliver  
 * @author Simon
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 * 
 */
public class User {
    
    private int userID;
    private String userName;
    private String password;
    HashSet<UserTypes.UserType> types;
    HashSet<String> accessibleDirectories;
    
    public User(String userName, String password, int userID) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.types = new HashSet();
        accessibleDirectories = new HashSet<String>();
    }
    
    public void addAccessibleDirectory(String directory)
    {
        this.accessibleDirectories.add(directory);
    }
    
    public HashSet<String> getAccessibleDirectories()
    {
        return this.accessibleDirectories;
    }
    
    public void addUserType(UserTypes.UserType type) {
        types.add(type);
    }
    
    public void deleteUserType(UserTypes.UserType type) {
        types.remove(type);
    }
    
    public boolean checkUserType(UserTypes.UserType type) {
        return types.contains(type);
    }
    
    public void modifyUserName(String newUserName) {
        this.userName = newUserName;
    }
    
    public void modifyPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public int getUserID(){
        return userID;
    }
    
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getPassword() {
        return this.password;
    }

    public HashSet<UserType> getTypes() {
        return types;
    } 
}
