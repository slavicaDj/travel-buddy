package clients.rest;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherClient {
	
	private static String url = "http://api.openweathermap.org/data/2.5/weather?&appid=7d3d7f48ded7a5dbb5b09587cdb25986&units=metric&q=";
	
	public static String getCityWeatherInfo(String grad) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			
			JSONObject jsonObject = RestClient.readJSONFromUrl(url + grad);
			
			if (jsonObject != null && jsonObject.getString("name") != null) {
							
				String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
				String temp = jsonObject.getJSONObject("main").getString("temp");
				
				stringBuilder.append("Description: " + description);
				stringBuilder.append("\n");
				stringBuilder.append("Temperature: " + temp);
			}
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return stringBuilder.toString();
		
	}

}
