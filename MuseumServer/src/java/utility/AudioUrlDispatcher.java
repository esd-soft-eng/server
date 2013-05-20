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
            String execCommand = "curl --data \"pid=" + pin + "&url=" + audioUrl + "\" http://"+ hostName +":8085";
            proc = rt.exec(execCommand);
        } catch (IOException ex) {
            Logger.getLogger(AudioUrlDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
