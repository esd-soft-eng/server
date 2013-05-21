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
public class AudioPlayer {

    //private Thread t;
    //private GstreamProc stream;
    private Process proc;

    public void playUrl(String url) {

        //if(this.t.isAlive()){
        //this.stream.killprocess();
        //}
        //this.stream = new GstreamProc(url);
        //this.t = new Thread(this.stream);
        //t.start();

        if (this.proc != null) {
            proc.destroy();
        }
        try {
            Runtime rt = Runtime.getRuntime();
            String execString = "gst-launch-0.10 playbin2 uri=" + url;
            this.proc = rt.exec(execString);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}