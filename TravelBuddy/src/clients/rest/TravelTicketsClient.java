package clients.rest;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Karta;
import model.Prevoz;
import util.Util;

public class TravelTicketsClient {
	
	private static final String url = "http://localhost:8080/TravelTickets/rest/karte/";

	public static ArrayList<Karta> getKarte(String odrediste) {
		
		ArrayList<Karta> karte = new ArrayList<>();
		try {
			
			JSONObject jsonObject = RestClient.readJSONFromUrl(url + URLEncoder.encode(odrediste, "UTF-8"));
			
			if (jsonObject != null) {
				
				Object obj = jsonObject.get("karta");
				
				if (obj instanceof JSONArray) {
					
					JSONArray jsonArray = jsonObject.getJSONArray("karta");
					
					for (int i = 0; i < jsonArray.length(); i++) {
						
						JSONObject element = jsonArray.getJSONObject(i);
						Karta karta = popuniKartu(element);							
						karte.add(karta);
						
					}
				}
				if (obj instanceof JSONObject) {
					
					Karta karta = popuniKartu(jsonObject.getJSONObject("karta"));
					karte.add(karta);
					
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return karte;
		
	}
	
	private static Karta popuniKartu(JSONObject element) {
		
		Karta karta = new Karta();
		
		try {
			karta.setCijena(element.getDouble("cijena"));
			karta.setDestinacija(element.getString("destinacija"));
			karta.setPolaziste(element.getString("polaziste"));
			
			String prevozString = element.getString("prevoz");
			switch(prevozString) {
				case "autobus":
					karta.setPrevoz(Prevoz.autobus);
					break;
				case "avion":
					karta.setPrevoz(Prevoz.avion);
					break;
				case "voz":
					karta.setPrevoz(Prevoz.voz);
					break;
			}
			String vrijemeDolaskaString = element.getString("vrijemeDolaska");
			karta.setVrijemeDolaska(Util.parseJSONStringToDate(vrijemeDolaskaString));
			
			String vrijemePolaskaString = element.getString("vrijemePolaska");
			karta.setVrijemePolaska(Util.parseJSONStringToDate(vrijemePolaskaString));	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return karta;
		
	}

}


