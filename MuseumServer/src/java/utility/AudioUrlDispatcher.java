/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
public class AudioUrlDispatcher {

    public static void DispatchUrlToHandset(int pin, String audioUrl, String hostName) {
        
        try {
            audioUrl = URLEncoder.encode(audioUrl, "ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AudioUrlDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Process proc;
            Runtime rt = Runtime.getRuntime();
            //String execCommand = "curl --data pid=" + pin + "&url=" + "rtsp://127.0.0.1:8554/test.mp3" + " http://"+ hostName +":8085";
            String execCommand = "curl --data pid=" + pin + "&url=" + audioUrl + " http://"+ hostName +":8085";
            proc = rt.exec(execCommand);
        } catch (IOException ex) {
            Logger.getLogger(AudioUrlDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
