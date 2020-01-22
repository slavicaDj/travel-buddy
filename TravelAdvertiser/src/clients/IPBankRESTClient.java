package clients;

import java.net.URLEncoder;

import org.json.JSONObject;

public class IPBankRESTClient {
	
	private static String url = "http://localhost:8080/IPBankaREST/rest/klijenti/provjera";
	
	public static String provjeriKlijenta(String email, double iznos) {
		
		String rezultat = "";
		
		try {
			
			String emailEnc = URLEncoder.encode(email, "UTF-8");
			String iznosEnc = URLEncoder.encode(String.valueOf(iznos), "UTF-8");
			
			JSONObject jsonObject = RestClient.readJSONFromUrl(url + "?email=" + emailEnc + "&iznos=" + iznosEnc);
			
			if (jsonObject != null) {
				rezultat = jsonObject.getString("odgovor");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return rezultat;
	
	}


}
