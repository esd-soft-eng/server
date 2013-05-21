/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softkeypad;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author neil
 */
public class Server implements Runnable {

    private int port;
    private ServerSocket serverSocket;
    private AudioPlayer player;
    private DefaultHttpServerConnection conn;

    public Server(int port, AudioPlayer player) {

        this.player = player;
        this.port = port;
        this.initServerSocket();
    }

    @Override
    public void run() {

        while (true) {
            try {
                this.acceptConnections();
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void initServerSocket() {

        try {
            this.conn = new DefaultHttpServerConnection();
            this.serverSocket = new ServerSocket(this.port);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void acceptConnections() throws Exception {

        this.conn.bind(serverSocket.accept(), new BasicHttpParams());
        HttpRequest request = conn.receiveRequestHeader();
        conn.receiveRequestEntity((HttpEntityEnclosingRequest) request);
        HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
        String params = EntityUtils.toString(entity);
        this.handleParams(params);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        //response.setEntity(new StringEntity("<html><body>YARR</body></html>"));        
        StringEntity responseEntity = new StringEntity(
                        "<html><body><h1>YARR</h1></body></html>",
                        ContentType.create("text/html", "UTF-8"));

        conn.sendResponseHeader(response);
        conn.sendResponseEntity(response);
        conn.close();
    }

    private void handleParams(String params) {
        
        String[] args = params.split("&");

        if (this.pinIsValid(args[0])) {
            processURL(args[1]);
        }
    }

    private boolean pinIsValid(String string) {
        return true;
    }

    private void processURL(String string) {

        if (string.startsWith("url=")) {
            String[] urls = string.split("=");
            player.playUrl(urls[1]);
        }
    }
}
// GET /pin=20&ex=21 HTTP/1.1
// GET /favicon.ico HTTP/1.1