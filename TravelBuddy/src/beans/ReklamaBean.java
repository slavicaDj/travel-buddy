package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import clients.rest.TravelAdvertiserClient;
import model.Reklama;

@ManagedBean(name="reklamaBean")
@ApplicationScoped
public class ReklamaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -918067317853875793L;
	private Reklama trenutnaReklama;
	
	public ReklamaBean() {
		
	}
	
	@PostConstruct
	public void init() {
		trenutnaReklama = new Reklama();
		trenutnaReklama.setSadrzaj("MJESTO ZA VAŠU REKLAMU");
	}
		
	public String promijeniReklamu() {
		ArrayList<Reklama> reklame = TravelAdvertiserClient.getReklame();
		int size = reklame.size();
		Random rand = new Random();
		trenutnaReklama = reklame.get(rand.nextInt(size));
		
		return null;
	}

	public Reklama getTrenutnaReklama() {
		return trenutnaReklama;
	}

	public void setTrenutnaReklama(Reklama trenutnaReklama) {
		this.trenutnaReklama = trenutnaReklama;
	}
	
	
	
	

}
