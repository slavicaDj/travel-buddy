package servis.soap;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import model.KlijentBanke;
import redis.clients.jedis.Jedis;

import util.Util;

@WebService
public class ServisPlacanje {
	
	public ServisPlacanje(){
	}
 
	@WebMethod
	public String uplati(KlijentBanke korisnik, double iznos){
		
		Jedis jedis = null;
		String rezultat = "";
		try {
			jedis = new Jedis("localhost");
			
			Map<String,String> korisnikMapa = jedis.hgetAll("user:" + korisnik.getEmail());
			
			String ime = korisnikMapa.get("ime");
			String prezime = korisnikMapa.get("prezime");
			String brojKartice = korisnikMapa.get("broj_kartice");
			String tipKartice = korisnikMapa.get("tip_kartice");
			String datumIsteka = korisnikMapa.get("datum_isteka");
			String cvc = korisnikMapa.get("cvc");
			
			if (korisnik.getIme().equals(ime) && korisnik.getPrezime().equals(prezime) && korisnik.getBrojKartice().equals(brojKartice)
				&& korisnik.getTipKartice().equals(tipKartice) && korisnik.getDatumIsteka().equals(datumIsteka) && korisnik.getCvc().equals(cvc)) {
			
				if (!Util.isKarticaVazeca(datumIsteka)) {
					return Consts.ISTEKAO_ROK_TRAJANJA_KARTICE;
				}
				double stanje = Double.valueOf(korisnikMapa.get("stanje"));
				if (iznos <= stanje) {
					stanje -= iznos;
					jedis.hset("user:" + korisnik.getEmail(), "stanje", "" + stanje);
					return Consts.OK;
				}
				else {
					return Consts.NEDOVOLJAN_IZNOS;
				}
				
			}
			else {
				return Consts.NETACNI_PODACI;
			}
	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (jedis!= null) jedis.close();
		}
		return rezultat;
	}
 	
}
