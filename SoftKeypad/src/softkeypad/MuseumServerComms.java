/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softkeypad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author neil
 */
public class MuseumServerComms {

    private String url;
    private String mac;
    
    MuseumServerComms(String url, String mac) {
        this.url = url;
        this.mac = mac;               
    }

    public String logHandset(String pin) {

        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(this.url + "?" + "pin=" + pin + "&mac=" + this.mac);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            while ((line = rd.readLine()) != null) {
                result += line + "&";
            }
            
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (result.equalsIgnoreCase("")){
            return "[]";
        }
        
        String[] es = result.split("&");        
        return es[2];
    }   
       
    public String requestAudio(String pin, String exhibit){
     
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(this.url + "?" + "pin=" + pin + "&mac=" + this.mac + "&exhibit=" + exhibit);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            while ((line = rd.readLine()) != null) {                
                    result += line + "&";
            }
            
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(result);        
        return result;        
    }
    
    
}
