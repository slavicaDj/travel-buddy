package beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.KorisnikDAO;
import dto.Korisnik;
import util.Consts;
import util.Sha3Enkripcija;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5022669426687458041L;
	
	private Korisnik korisnik = new Korisnik();
	private String korisnickoIme;
	private String lozinka;
	private String dobrodosli;
	
	public UserBean(){
		
	}
	
	public String login() {
		
		//aktivan i odobren => login
		//aktivan, nije odobren => ceka se verifikacija
		//nije aktivan, odobren => blokiran
		//odobren <=> registrovan
				
		if (korisnik.getKorisnickoIme() == null) {
			Korisnik korisnikTemp = KorisnikDAO.selectKorisnik(korisnickoIme);
			if (korisnikTemp != null && korisnikTemp.getKorisnickoIme().equals(korisnickoIme)) {				
				if (korisnikTemp.isAktivan()) {
					if (korisnikTemp.isOdobren()) {
						if (korisnikTemp.getLozinka().equals(Sha3Enkripcija.encrypt(lozinka))) {
							korisnik = korisnikTemp;
							dobrodosli = "Dobrodošli na Travel Buddy, korisniče " + korisnik.getKorisnickoIme();
							FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.KORISNIK_BEAN, this);
						}
						else {
							FacesContext.getCurrentInstance().addMessage("loginForma:messagePrijaviSe", new FacesMessage("Pogrešna lozinka!"));
						}
					}
					else {
						FacesContext.getCurrentInstance().addMessage("loginForma:messagePrijaviSe", new FacesMessage("Vaš nalog čeka na odobravanje administratora."));
					}
				}
				else {
					if (korisnikTemp.isOdobren()) {
						FacesContext.getCurrentInstance().addMessage("loginForma:messagePrijaviSe", new FacesMessage("Vaš nalog je deaktiviran radi nedoličnog ponašanja!"));
					}
				}
			}
			else {
				FacesContext.getCurrentInstance().addMessage("loginForma:messagePrijaviSe", new FacesMessage("Ne postoji nalog sa datim korisničkim imenom!"));
			}
		}
		korisnickoIme = "";
		lozinka = "";
		
		return null;
		
	}
	
	public String logout() {
		
		korisnik = new Korisnik();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(Consts.KORISNIK_BEAN);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "index.xhtml?faces-redirect=true";
		
	}
	
	public String preusmjeriNaKorisnickiDio() {
		
		if (korisnik.isAdmin()) {
			return "odobravanje_naloga.xhtml?faces-redirect=true";
		}
		else {
			return "home.xhtml?faces-redirect=true";
		}
		
	}
	
	public boolean isLinkVisible(int id) {
		
		return korisnik.getId() != id;
		
	}
	
	
	public boolean isLoggedIn() {
		return korisnik.getKorisnickoIme() != null;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getDobrodosli() {
		return dobrodosli;
	}

	public void setDobrodosli(String dobrodosli) {
		this.dobrodosli = dobrodosli;
	}
	
	

}
