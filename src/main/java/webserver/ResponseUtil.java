package webserver;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);
	private DataOutputStream dos;
	
	public ResponseUtil(DataOutputStream dos) throws IOException {
		this.dos = dos;
	}
	
	public void response(String filePath, Map<String, String> params) throws IOException {
		
		File file = new File("./webapp", filePath);
		try{
			if(file.exists()) {
				byte[] body = Files.readAllBytes(file.toPath());
				responseHeader("200", body.length);
				responseBody(body);
			}else {
				String message404 = "page not found";
				responseHeader("404", message404.getBytes().length);
				responseBody(message404.getBytes());
			}
		}catch(IOException ioe) {
			log.error(ioe.getMessage(), ioe);
		}
	}
	
	private void responseHeader(String statusCode, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 " + statusCode + " OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
