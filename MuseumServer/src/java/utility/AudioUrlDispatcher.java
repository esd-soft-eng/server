/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import handsetInteraction.HandsetRequestManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
public class AudioUrlDispatcher {

    public static void DispatchUrlToHandset(int pin, String audioUrl, String hostName) {
        
        try {            
            String urlParameters = "pin="+ pin +"&url="+audioUrl;
            URL url = new URL("http://" + hostName);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(urlParameters);
            writer.flush();

            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            writer.close();
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(HandsetRequestManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
