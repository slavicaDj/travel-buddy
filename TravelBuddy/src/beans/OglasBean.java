package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import dao.KomentarDAO;
import dao.OglasDAO;
import dto.Komentar;
import dto.Oglas;
import util.Consts;
import util.Util;

@ManagedBean(name="oglasBean")
@ViewScoped
public class OglasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3325393941902669540L;
	
	private SelectItem[] kategorije;
	
	private Oglas noviOglas;
	private Oglas oglasDetaljno;
	
	private String sadrzajKomentara;
	
	private ArrayList<Komentar> komentari;
	
	
	public OglasBean() {
		
	}
	
	@PostConstruct
	public void init() {
		
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(Consts.OGLAS_ID)) {
			int oglasId = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Consts.OGLAS_ID);
			oglasDetaljno = OglasDAO.selectById(oglasId);
			komentari = KomentarDAO.selectByOglasId(oglasId);
		}
		
		noviOglas = new Oglas();
		
		kategorije = new SelectItem[2];
		kategorije[0] = new SelectItem(Consts.KR_TRAZIM, Consts.TRAZIM);
		kategorije[1] = new SelectItem(Consts.KR_NUDIM, Consts.NUDIM);
				
	}
	
	public String objaviOglas() {
				
		noviOglas.setKorisnik(Util.getKorisnik());

		if (OglasDAO.insert(noviOglas)) {
			FacesContext.getCurrentInstance().addMessage("oglasForma:messageObjaviOglas", new FacesMessage("Oglas je uspješno objavljen!"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage("oglasForma:messageObjaviOglas", new FacesMessage("Greška! Oglas nije objavljen!"));
		}
		
		return null;
		
	}
	
	public String objaviKomentar() {
		
		sadrzajKomentara = sadrzajKomentara.trim();
		
		if ("".equals(sadrzajKomentara)) {
			FacesContext.getCurrentInstance().addMessage("formOglasDetaljno:messagesOglasDetaljno", new FacesMessage("Unesite sadržaj komentara!"));
			return null;
		}
		
		Komentar komentar = new Komentar();
		komentar.setOglas(oglasDetaljno);
		komentar.setSadrzaj(sadrzajKomentara);
		komentar.setVrijeme(Calendar.getInstance().getTime());
		
		komentar.setKorisnik(Util.getKorisnik());
		
		if (KomentarDAO.insert(komentar)) {
			setKomentari(KomentarDAO.selectByOglasId(oglasDetaljno.getId()));
		}

		sadrzajKomentara = "";
		
		return null;
		
	}
	
	public String napisiPoruku(int kontaktId) {
						
		if (kontaktId == Util.getKorisnik().getId()) {
			return null;
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.KONTAKT_ID, kontaktId);
		return "poruke_detaljno.xhtml?faces-redirect=true";
	}
	
	public String prijaviSadrzaj(Komentar komentar) {
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.PRIJAVA_KOMENTAR, komentar);
		return "prijavi_sadrzaj.xhtml?faces-redirect=true";
		
	}
	
	
	public boolean isLinkVisible(int korisnikId) {
		
		if (korisnikId == Util.getKorisnik().getId()) {
			return false;
		}
		return true;
		
	}
	
	public Oglas getNoviOglas() {
		return noviOglas;
	}

	public void setNoviOglas(Oglas noviOglas) {
		this.noviOglas = noviOglas;
	}

	public SelectItem[] getKategorije() {
		return kategorije;
	}

	public void setKategorije(SelectItem[] kategorije) {
		this.kategorije = kategorije;
	}

	public Oglas getOglasDetaljno() {
		return oglasDetaljno;
	}

	public void setOglasDetaljno(Oglas oglasDetaljno) {
		this.oglasDetaljno = oglasDetaljno;
	}

	public ArrayList<Komentar> getKomentari() {
		return komentari;
	}

	public void setKomentari(ArrayList<Komentar> komentari) {
		this.komentari = komentari;
	}

	public String getSadrzajKomentara() {
		return sadrzajKomentara;
	}

	public void setSadrzajKomentara(String sadrzajKomentara) {
		this.sadrzajKomentara = sadrzajKomentara;
	}



}
