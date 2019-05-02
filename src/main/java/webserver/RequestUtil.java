package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
	
	private ArrayList<String> headers = new ArrayList<String>();
	private Map<String, String> parameter = new HashMap<String, String>();
	
	public RequestUtil(InputStream is) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while((line = br.readLine()) != null) {
				headers.add(line);
				if(!br.ready()) {
					break;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPath() {
		return headers.get(0).split(" ")[1];
	}
	
	public String getParameter(String key) {
		return parameter.get(key);
	}
	
	public Map<String, String> getParameter() {
		return parameter;
	}
	
	public String getHeader(String key) {
		return null;
	}
	
	public String getMethod() {
		return null;
	}
}
