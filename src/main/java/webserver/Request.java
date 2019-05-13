package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.HttpRequestUtils;

public class Request {
	
	private ArrayList<String> headers = new ArrayList<String>();
	private Map<String, String> parameter = new HashMap<String, String>();
	
	@SuppressWarnings("unused")
	public Request(InputStream is) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while(!(line = br.readLine()).equals("")) {
				headers.add(line);
				System.out.println(">>> " + line);
				
				if(line == null) {
					break;
				}
			}
			
			if(!headers.isEmpty()) {
				String path = headers.get(0).split(" ")[1];
				int index = path.indexOf("?");
				String queryString = path.substring(index+1);
				parameter = HttpRequestUtils.parseQueryString(queryString);
			}else {
				throw new Exception("Request Empty Stream");
			}
			
		} catch (IOException e) {
			System.out.println("========");
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
		return headers.get(0).split(" ")[0];
	}
}
