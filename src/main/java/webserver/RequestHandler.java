package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());
        
        try (InputStream is = connection.getInputStream();
        		OutputStream out = connection.getOutputStream();
        		DataOutputStream dos = new DataOutputStream(out);){
        	
        	RequestUtil req = new RequestUtil(is);
        	ResponseUtil res = new ResponseUtil(dos);
        	res.response(req.getPath(), req.getParameter());
        }catch(IOException ioe) {
        	log.error(ioe.getMessage(), ioe);
        }
    }

}
