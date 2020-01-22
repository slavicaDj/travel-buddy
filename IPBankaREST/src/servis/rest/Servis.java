package servis.rest;

import java.io.Serializable;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import redis.clients.jedis.Jedis;
import util.Consts;

@Path("/klijenti")
public class Servis {
	
	public Servis(){
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<Karta> karte() {
//	
//		ArrayList<Karta> karte = KartaDBUtils.selectAll();
//
//		return karte;
//		
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/provjera")
	public Provjera queryValues(@DefaultValue("") @QueryParam("email") String email,
							  @DefaultValue("0.00") @QueryParam("iznos") double iznos) {
		
		
		Provjera provjera = new Provjera();
		
		Jedis jedis = new Jedis("localhost");

		if ((jedis.hget("user:" + email, "broj_kartice") != null) && (jedis.hget("user:" + email, "stanje") != null)) {
			
			double stanje = Double.valueOf(jedis.hget("user:" + email, "stanje"));
			if (stanje >= iznos) {
				provjera.odgovor = Consts.OK;
			}
			else {
				provjera.odgovor = Consts.NEDOVOLJAN_IZNOS;
			}
			
		}
		else {
			provjera.odgovor = Consts.NEPOSTOJECI_EMAIL;
		}
		
		jedis.close();
		
		return provjera;
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/{email}")
//	public String postojiLiKorisnik(@PathParam("email") String email) {
//	
//		String rezultat = "false";
//		
//		Jedis jedis = new Jedis("localhost");
//		if (jedis.hget("user:" + email, "broj_kartice") != null) {
//			rezultat = "true";
//		}
//		
//		jedis.close();
//
//		return rezultat;
//		
//	}

	@XmlRootElement
	private static class Provjera implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 3331953569635071697L;
		@XmlElement
		private String odgovor;
		
		@Override
		public String toString() {
			return odgovor;
		}
		
	}
}


