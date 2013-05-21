/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softkeypad;

/**
 *
 * @author neil
 */
public class SoftKeypad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                String url = "http://localhost:8084/MuseumServer/HandsetRequestInterface";
                String mac = "af:af:af:af:af:af";
                               
                KeyPad kp = new KeyPad();
                kp.initKeyPad(new MuseumServerComms(url, mac));
                kp.setVisible(true);
            }
        });

        int port = 8085;
        Server server = new Server(8085, new AudioPlayer());

        Thread t1 = new Thread(server);
        t1.start();
    }
}
