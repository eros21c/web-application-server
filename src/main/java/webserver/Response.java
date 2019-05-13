package webserver;

import java.io.DataOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Response {
	
	private static final Logger log = LoggerFactory.getLogger(Response.class);
	private DataOutputStream dos;
	
	public Response(DataOutputStream dos) throws IOException {
		this.dos = dos;
	}
	
	public void responseHeader(String statusCode, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 " + statusCode + " OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
