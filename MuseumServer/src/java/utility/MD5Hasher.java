package utility;

import java.security.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class MD5Hasher {
    
    public static String hashMD5(String hash) {
        
        String returnedHash = "";
        
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(hash.getBytes(),0,hash.length());
            returnedHash = new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Hasher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnedHash;
    }
}