/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handsetInteraction;

import java.util.HashSet;

/**
 *
 * @author neil
 */
public class AudioPortManager {
    
    private HashSet<String> portList;
    private int minPort;
    private int maxPort;
    private String live555port = "8554";
    
    public AudioPortManager(int minPort, int maxPort){
        this.minPort = minPort;
        this.maxPort = maxPort;
    }
    
    public String getNewPort(){
        
        return live555port;
        
        /*String newPort;
        do{
            newPort = this.generateNewPortNumber();
        } while (this.portList.contains(newPort));
        
        portList.add(newPort);
        return newPort;*/
    }

    private String generateNewPortNumber() {
        
        int newPort = (int) (this.minPort + (Math.random() * (this.maxPort - this.minPort)));
        return Integer.toString(newPort);
    }
}
