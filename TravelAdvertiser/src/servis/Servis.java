package servis;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import database.dao.ReklamaDAO;
import database.dto.Reklama;

@Path("/reklame")
public class Servis {
	
	public Servis(){
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Reklama> reklame() {
	
		ArrayList<Reklama> reklame = ReklamaDAO.selectReklameZaDanas();

		return reklame;
		
	}

}
