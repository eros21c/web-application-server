package webserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.User;
import webserver.persistence.UserStore;

public class ServiceDelegate {
	
	private static final Logger log = LoggerFactory.getLogger(Response.class);
	
	public byte[] mapper(String filePath, Map<String, String> params) throws IOException {
		if(filePath.startsWith("/user/create")) {
			User user = new User(params.get("userId")
								, params.get("password")
								, params.get("name")
								, params.get("email"));
			
			UserStore.add(user);
			
			return "success".getBytes();
		}else {
			File file = new File("./webapp", filePath);
			try{
				if(file.exists()) {
					return Files.readAllBytes(file.toPath());
				}else {
					throw new FileNotFoundException(file.getPath() + " not found");
				}
			}catch(IOException ioe) {
				log.error(ioe.getMessage(), ioe);
				throw ioe;
			}
		}
	}
}
