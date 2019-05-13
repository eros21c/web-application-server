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
        	
        	Request req = new Request(is);
        	ServiceDelegate service = new ServiceDelegate();
        	Response res = new Response(dos);
        	try {
        		
        		byte[] responseBody = service.mapper(req.getPath(), req.getParameter());
        		res.responseHeader("200", responseBody.length);
        		res.responseBody(responseBody);
        	}catch(IOException ioe) {
        		String message = "Page Not Found";
        		res.responseHeader("404", message.getBytes().length);
        		res.responseBody(message.getBytes());
        	}catch(NullPointerException npe) {
        		String message = "Page Not Found";
        		res.responseHeader("404", message.getBytes().length);
        		res.responseBody(message.getBytes());
        	}
        	
        }catch (IOException ioe) {
        	log.error(ioe.getMessage(), ioe);
        }catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    }

}
