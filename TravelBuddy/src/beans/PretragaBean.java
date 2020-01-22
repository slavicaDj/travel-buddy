package beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import clients.rest.TravelTicketsClient;
import dao.OglasDAO;
import dto.Komentar;
import dto.Oglas;
import model.Karta;
import util.Consts;
import util.Util;

@ManagedBean(name="pretragaBean")
@SessionScoped
public class PretragaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6461426077792506496L;
	
	private ArrayList<Oglas> oglasi = new ArrayList<>();
	private ArrayList<Komentar> komentari = new ArrayList<>();
	private ArrayList<Karta> karte = new ArrayList<>();
	
	private SelectItem[] kriterijumi = {
										new SelectItem(Consts.KR_POLAZIŠTE, Consts.POLAZIŠTE),
										new SelectItem(Consts.KR_ODREDIŠTE, Consts.ODREDIŠTE),
										new SelectItem(Consts.KR_DATUM_POLASKA, Consts.DATUM_POLASKA)
									   };
	
	private SelectItem[] kategorije =  {
										new SelectItem(Consts.KR_TRAZIM, Consts.TRAZIM), 
										new SelectItem(Consts.KR_NUDIM, Consts.NUDIM),
									   };
	
	private String[] odabraneKategorije;
	
	private String kriterijum;
	private String parametar;
		
	public PretragaBean() {
		
	}
	
	public void pretraziOglase(AjaxBehaviorEvent event) {
		
		if (odabraneKategorije.length == 0 && "".equals(parametar)) {
			FacesContext.getCurrentInstance().addMessage("formPretraga:messagePretraga", new FacesMessage("Unesite ili odaberite kriterijume za pretragu!"));
			oglasi = new ArrayList<>();
			karte = new ArrayList<>();
			return;
		}
		
		Object parametarObj = parametar;
		String kategorija = null;

		if (Consts.KR_DATUM_POLASKA.equals(kriterijum)) {
			try {
				Date date = new SimpleDateFormat("dd.MM.yyyy").parse(parametar);
				parametarObj = date;
			} catch (ParseException e) {
				FacesContext.getCurrentInstance().addMessage("formPretraga:messagePretraga", new FacesMessage("Unesite vrijeme u formatu dd.MM.yyyy!"));
				oglasi = new ArrayList<>();
				karte = new ArrayList<>();
				return;
			}
		}
		
		if (odabraneKategorije.length == 1) {
			kategorija = odabraneKategorije[0];
		}
		
		oglasi = OglasDAO.selectOglasiTrazimNudim(kriterijum, parametarObj, kategorija);
		
		if (Consts.KR_ODREDIŠTE.equals(kriterijum) && !"".equals(parametar)) {
			karte = TravelTicketsClient.getKarte(parametar);
		}
		else {
			karte = new ArrayList<>();
		}
		
	}
	
	
	
	public String prikaziOglasDetaljno() {
		
		Map<String,String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int id = Integer.valueOf(map.get("oglasId"));
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.OGLAS_ID, id);
		
		return "oglas_detaljno?faces-redirect=true";
	}	
	
	
	public String prijaviSadrzaj(int oglasId) {
		
		for (Oglas o : oglasi) {
			if (o.getId() == oglasId && o.getKorisnik().getId() != Util.getKorisnik().getId()) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.PRIJAVA_OGLAS, o);
				return "prijavi_sadrzaj.xhtml?faces-redirect=true";
			}
		}
		
		return null;
	}
	
	
	public String kupiKartu(Karta karta) {
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.KARTA, karta);
		return "kupi_kartu.xhtml?faces-redirect=true";
		
	}

	public ArrayList<Oglas> getOglasi() {
		return oglasi;
	}

	public void setOglasi(ArrayList<Oglas> oglasi) {
		this.oglasi = oglasi;
	}

	public String getParametar() {
		return parametar;
	}

	public void setParametar(String parametar) {
		this.parametar = parametar;
	}

	public String getKriterijum() {
		return kriterijum;
	}

	public void setKriterijum(String kriterijum) {
		this.kriterijum = kriterijum;
	}

	public SelectItem[] getKriterijumi() {
		return kriterijumi;
	}

	public void setKriterijumi(SelectItem[] kriterijumi) {
		this.kriterijumi = kriterijumi;
	}

	public SelectItem[] getKategorije() {
		return kategorije;
	}

	public void setKategorije(SelectItem[] kategorije) {
		this.kategorije = kategorije;
	}

	public String[] getOdabraneKategorije() {
		return odabraneKategorije;
	}

	public void setOdabraneKategorije(String[] odabraneKategorije) {
		this.odabraneKategorije = odabraneKategorije;
	}

	public ArrayList<Komentar> getKomentari() {
		return komentari;
	}

	public void setKomentari(ArrayList<Komentar> komentari) {
		this.komentari = komentari;
	}

	public ArrayList<Karta> getKarte() {
		return karte;
	}

	public void setKarte(ArrayList<Karta> karte) {
		this.karte = karte;
	}
	
	
}
