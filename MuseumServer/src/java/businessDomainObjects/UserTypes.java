package businessDomainObjects;

/**
 *
 * @author Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
 * 
 * Public enum for the different user types a user can be for interacting with
 * the server side of the system.
 */
public class UserTypes {    
    public enum UserType {
        MAINTAINER, ADMINISTRATOR, CLIENTHANDSET, KIOSK, MANAGER
    }
}