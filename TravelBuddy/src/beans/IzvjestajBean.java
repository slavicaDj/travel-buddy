package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import dao.KorisnikDAO;
import dao.OglasDAO;
import dto.Korisnik;
import dto.Oglas;

@ManagedBean(name="izvjestajBean")
@ViewScoped
public class IzvjestajBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8106134491245155376L;
	
	private ArrayList<Korisnik> korisnici;
	private ArrayList<Oglas> oglasi;
	
	private Date datumOd;
	private Date datumDo;
	
	public IzvjestajBean() {
		
	}
	
	@PostConstruct
	public void init() {
		
		datumOd = new Date();
		datumDo = new Date();
	}
	
	public void odabranKriterijumListener(AjaxBehaviorEvent event) {
			
		if (!datumOd.before(datumDo)) {
			FacesContext.getCurrentInstance().addMessage("izvjestajiForm:izvjestajiMessages", new FacesMessage("Odaberite validan vremenski interval."));
		}
		else {
				korisnici = KorisnikDAO.selectRegistracijePeriod(datumOd,datumDo);
				oglasi = OglasDAO.selectByPeriod(datumOd, datumDo);
		}
	}
	
	public ArrayList<Korisnik> getKorisnici() {
		return korisnici;
	}
	
	
	public void setKorisnici(ArrayList<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	
	
	public ArrayList<Oglas> getOglasi() {
		return oglasi;
	}
	
	
	public void setOglasi(ArrayList<Oglas> oglasi) {
		this.oglasi = oglasi;
	}

	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}

}
