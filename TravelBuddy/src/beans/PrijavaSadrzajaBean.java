package beans;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.PrijavaSadrzajaDAO;
import dto.Komentar;
import dto.Oglas;
import dto.Poruka;
import dto.PrijavaSadrzaja;
import util.Consts;
import util.Util;

@ManagedBean(name="prijavaSadrzajaBean")
@ViewScoped
public class PrijavaSadrzajaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 317650183011980108L;
	
	private Oglas oglas;
	private Komentar komentar;
	private Poruka poruka;
	private String napomena;
	private String sadrzajZaPrijavu;
	
	
	public PrijavaSadrzajaBean() {
		
	}
	
	@PostConstruct
	public void init() {
		
		Map<String,Object> mapa = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		
		if (mapa.containsKey(Consts.PRIJAVA_OGLAS)) {
			setOglas((Oglas)mapa.get(Consts.PRIJAVA_OGLAS));
			sadrzajZaPrijavu = oglas.toString();
		}
		if (mapa.containsKey(Consts.PRIJAVA_KOMENTAR)) {
			setKomentar((Komentar)mapa.get(Consts.PRIJAVA_KOMENTAR));
			sadrzajZaPrijavu = komentar.toString();
		}
		if (mapa.containsKey(Consts.PRIJAVA_PORUKA)) {
			setPoruka((Poruka)mapa.get(Consts.PRIJAVA_PORUKA));
			sadrzajZaPrijavu = poruka.toString();
		}
		
		clearMap();
		
	}
	
	public String prijaviSadrzaj() {
		
		PrijavaSadrzaja prijava = new PrijavaSadrzaja(oglas, komentar, poruka, Util.getKorisnik(), napomena);
		
		if (PrijavaSadrzajaDAO.insert(prijava)) {
			FacesContext.getCurrentInstance().addMessage("formaPrijava:messagePrijava", new FacesMessage("Prijavljeno!"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage("formaPrijava:messagePrijava", new FacesMessage("Greška! Prijava nije uspjela!"));
		}
		
		napomena = "";
		clearMap();

		return null;
		
	}
	
	private void clearMap() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(Consts.PRIJAVA_KOMENTAR);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(Consts.PRIJAVA_OGLAS);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(Consts.PRIJAVA_PORUKA);
	}
	
	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public String getSadrzajZaPrijavu() {
		return sadrzajZaPrijavu;
	}

	public void setSadrzajZaPrijavu(String sadrzajZaPrijavu) {
		this.sadrzajZaPrijavu = sadrzajZaPrijavu;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public Komentar getKomentar() {
		return komentar;
	}

	public void setKomentar(Komentar komentar) {
		this.komentar = komentar;
	}

	public Poruka getPoruka() {
		return poruka;
	}

	public void setPoruka(Poruka poruka) {
		this.poruka = poruka;
	}

}
