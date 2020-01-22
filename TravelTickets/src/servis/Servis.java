package servis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Karta;
import oodb.KartaDBUtils;

@Path("/karte")
public class Servis {
	
	public Servis(){
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Karta> karte() {
	
		ArrayList<Karta> karte = KartaDBUtils.selectAll();

		return karte;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{destinacija}")
	public ArrayList<Karta> karte(@PathParam("destinacija") String destinacija) throws UnsupportedEncodingException {
		
		ArrayList<Karta> karte = KartaDBUtils.selectPoDestinaciji(URLDecoder.decode(destinacija, "UTF-8"));

		System.out.println("servis: " + karte.isEmpty());
		
		return karte;
		
	}

}
