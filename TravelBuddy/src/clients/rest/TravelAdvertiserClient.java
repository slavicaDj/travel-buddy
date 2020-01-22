package clients.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Reklama;

public class TravelAdvertiserClient {

	private static final String url = "http://localhost:8080/TravelAdvertiser/rest/reklame";
	private static final String path = "C:\\Users\\HP KORISNIK\\Downloads\\workspace\\TravelBuddy\\WebContent\\resources\\images\\";
	
	public static ArrayList<Reklama> getReklame() {
		
		ArrayList<Reklama> reklame = new ArrayList<>();
		try {
			
			JSONObject jsonObject = RestClient.readJSONFromUrl(url);
			
			if (jsonObject != null && jsonObject.getJSONArray("reklama") != null) {
				
				JSONArray jsonArray = jsonObject.getJSONArray("reklama");
				
				for (int i = 0; i < jsonArray.length(); i++) {
					
					JSONObject element = jsonArray.getJSONObject(i);
					
					Reklama reklama = new Reklama();
					reklama.setId(element.getInt("id"));
					reklama.setSadrzaj(element.getString("sadrzaj"));
					
					String slikaString = null;
					String naziv = null;
					if (element.has("slikaString")) {
						slikaString = element.getString("slikaString");
						naziv = element.getString("naziv");
					}

					if (slikaString != null) {
						String path = konvertujStringUSliku(slikaString, naziv);
						reklama.setPath(path);
					}
					
					reklame.add(reklama);
					
				}
			}
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return reklame;
		
	}
	
		
		private static String konvertujStringUSliku(String slikaString, String naziv){
			
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(slikaString);
			
			try {
				BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
				
				File outputfile = new File(path + naziv);
				if (ImageIO.write(img, naziv.split("\\.")[1], outputfile)) {
					return "images/" + naziv;
				}


			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}

		
}
