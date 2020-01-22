package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.KorisnikDAO;
import dto.Korisnik;

@ManagedBean(name="odobravanjeNalogaBean")
@RequestScoped
public class OdobravanjeNalogaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5355629841767816168L;
	
	private ArrayList<Korisnik> korisnici;
	
	public OdobravanjeNalogaBean() {
		
	}
	
	@PostConstruct
	public void init() {
		
		korisnici = KorisnikDAO.selectNeodobreniNalozi();
		
	}
	
	public String odobriNalog(Korisnik korisnik) {
		
		if (KorisnikDAO.odobriNalog(korisnik.getId())) {
			FacesContext.getCurrentInstance().addMessage("naloziForm:odobriNalogMessages", new FacesMessage("Korisnik " + korisnik.getIme() + korisnik.getPrezime() + " (" + korisnik.getKorisnickoIme() + ") " + "je uspješno odobren."));
			korisnici = KorisnikDAO.selectNeodobreniNalozi();
		}
		else {
			FacesContext.getCurrentInstance().addMessage("naloziForm:odobriNalogMessages", new FacesMessage("Greška! Korisnik " + korisnik.getIme() + korisnik.getPrezime() + " (" + korisnik.getKorisnickoIme() + ") " + "nije uspješno odobren!"));
		}
		
		return null;
	}
	
	public boolean isTableRendered() {
		return !korisnici.isEmpty();
	}

	public ArrayList<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(ArrayList<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

}
