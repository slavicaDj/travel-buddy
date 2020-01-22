package clients.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;

class RestClient {
		
	//pretvara String u json
	static JSONObject readJSONFromUrl(String url) {
		JSONObject jsonObject = null;
		try {
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
			String jsonText = read(reader);
			jsonObject = new JSONObject(jsonText);
			
			reader.close();
			in.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
			
	//pretvara podatke iz stream-a u String
	static String read(Reader reader) {
		StringBuilder builder = new StringBuilder();
		try {
			int character;
			while ((character = reader.read()) != -1) {
				builder.append((char)character);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
			

}
