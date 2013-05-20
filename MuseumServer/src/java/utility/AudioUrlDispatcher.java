/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
public class AudioUrlDispatcher {

    public static void DispatchUrlToHandset(int pin, String audioUrl, String hostName) {
        
        
        
        try {
            Process proc;
            Runtime rt = Runtime.getRuntime();
            proc = rt.exec("curl --data \"pid=" + pin + "&url=" + audioUrl + " http://127.0.0.1:8084/");
        } catch (IOException ex) {
            Logger.getLogger(AudioUrlDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
