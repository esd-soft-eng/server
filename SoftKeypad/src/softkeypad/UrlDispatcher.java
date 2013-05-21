/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softkeypad;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neil
 */
public class UrlDispatcher {

    public void nailIt() {

        try {
            Process proc;
            Runtime rt = Runtime.getRuntime();
            proc = rt.exec("curl --data \"pid=2222&url=rtsp://some.stupid.url\" http://127.0.0.1:8085/");
            
        } catch (IOException ex) {
            Logger.getLogger(UrlDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}