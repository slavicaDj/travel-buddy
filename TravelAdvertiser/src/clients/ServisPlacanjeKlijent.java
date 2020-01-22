package clients;

import model.KlijentBanke;
import servis.soap.ServisPlacanjeServiceLocator;

public class ServisPlacanjeKlijent {

	public static String uplati(KlijentBanke klijentBanke, Double iznos) {
		
		String rezultat = "";
		
		try {
			ServisPlacanjeServiceLocator loc = new ServisPlacanjeServiceLocator();
			ServisPlacanje servis = loc.getServisPlacanje();
			rezultat = servis.uplati(klijentBanke, iznos);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return rezultat;
	}

}
