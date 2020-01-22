package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.KorisnikDAO;
import dto.Korisnik;
import util.Sha3Enkripcija;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875786874714556134L;
	
	private Korisnik korisnik;
	
	public RegisterBean() {
		
	}
	
	@PostConstruct
	public void init() {
		
		korisnik = new Korisnik();
				
	}
	
	public String register() {
		
		korisnik.setLozinka(Sha3Enkripcija.encrypt(korisnik.getLozinka()));
		
		if (KorisnikDAO.insert(korisnik)) {
			FacesContext.getCurrentInstance().addMessage("registerForma:messageRegistrujSe", new FacesMessage("Registracija uspješna."));
			korisnik = null;
		}
		else {
			FacesContext.getCurrentInstance().addMessage("registerForma:messageRegistrujSe", new FacesMessage("Registracija nije uspjela! Provjerite unesene podatke!"));

		}
		return null;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}
