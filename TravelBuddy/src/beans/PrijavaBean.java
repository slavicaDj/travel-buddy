package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.KorisnikDAO;
import dao.PrijavaSadrzajaDAO;
import dto.Korisnik;
import dto.PrijavaSadrzaja;

@ManagedBean(name="prijavaBean")
@RequestScoped
public class PrijavaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1919019269093048512L;
	
	private ArrayList<PrijavaSadrzaja> prijave;
	

	public PrijavaBean() {
				
	}
	
	@PostConstruct
	public void init() {
		
		setPrijave(PrijavaSadrzajaDAO.selectAll());
		
	}
	
	public boolean isTableRendered() {
		
		return !prijave.isEmpty();
		
	}
	
	public String izbrisiSadrzaj(PrijavaSadrzaja prijava) {
		
		if (PrijavaSadrzajaDAO.zatvoriPrijavu(prijava)) {
			FacesContext.getCurrentInstance().addMessage("prijaveForm:zatvoriPrijavuMessages", new FacesMessage("Prijava " + prijava.getId() + " je uspješno zatvorena."));
			setPrijave(PrijavaSadrzajaDAO.selectAll());
		}
		else {
			FacesContext.getCurrentInstance().addMessage("prijaveForm:zatvoriPrijavuMessages", new FacesMessage("Greška! Prijava " + prijava.getId() + " neuspješno zatvorena!"));
		}
		
		return null;
	}
	
	
	
	public String izbrisiPrijavu(PrijavaSadrzaja prijava) {
		
		if (PrijavaSadrzajaDAO.izbrisi(prijava.getId())) {
			FacesContext.getCurrentInstance().addMessage("prijaveForm:zatvoriPrijavuMessages", new FacesMessage("Prijava " + prijava.getId() + " je uspješno izbrisana."));
			setPrijave(PrijavaSadrzajaDAO.selectAll());
		}
		else {
			FacesContext.getCurrentInstance().addMessage("prijaveForm:zatvoriPrijavuMessages", new FacesMessage("Greška! Prijava " + prijava.getId() + " nije izbrisana!"));
		}
		
		return null;
	}
	
	public String deaktivirajKorisnika(PrijavaSadrzaja prijava) {
		
		Korisnik korisnik = null;
		if (prijava.getOglas() != null) {
			korisnik = prijava.getOglas().getKorisnik();
		}
		else if (prijava.getKomentar() != null) {
			korisnik = prijava.getKomentar().getKorisnik();
		}
		else {
			korisnik = prijava.getPoruka().getPosiljalac();
		}
		
		if (PrijavaSadrzajaDAO.izbrisi(prijava.getId()) && KorisnikDAO.deaktivirajNalog(korisnik.getId())) {
			FacesContext.getCurrentInstance().addMessage("prijaveForm:zatvoriPrijavuMessages", new FacesMessage("Korisnik " + korisnik.getIme() + " " + korisnik.getPrezime() + " je uspješno deaktiviran."));
			setPrijave(PrijavaSadrzajaDAO.selectAll());
		}
		else {
			FacesContext.getCurrentInstance().addMessage("prijaveForm:zatvoriPrijavuMessages", new FacesMessage("Greška! Korisnik " + korisnik.getIme() + " " + korisnik.getPrezime() + " nije deaktiviran!"));
		}
		
		return null;
	}
	
	
	
	public ArrayList<PrijavaSadrzaja> getPrijave() {
		return prijave;
	}

	public void setPrijave(ArrayList<PrijavaSadrzaja> prijave) {
		this.prijave = prijave;
	}

	
	
	
}
