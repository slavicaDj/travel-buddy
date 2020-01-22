package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.PorukaDAO;
import dto.Korisnik;
import dto.Poruka;
import util.Consts;
import util.Util;

@ManagedBean(name="porukaBean")
@ViewScoped
public class PorukaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1143664348511811954L;

	private ArrayList<Korisnik> kontakti = new ArrayList<>();
	
	public PorukaBean() {
		
	}

	public ArrayList<Korisnik> getKontakti() {
				
		kontakti = PorukaDAO.selectMojiKontakti(Util.getKorisnik().getId());
		
		return kontakti;
	}
	
	public String pregledajPorukeDetaljno() {
				
		Map<String,String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int kontaktId = Integer.valueOf(map.get("kontaktId"));
		
		//FacesContext.getCurrentInstance().getExternalContext().getFlash().put("kontaktId", kontaktId);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.KONTAKT_ID, kontaktId);

		return "poruke_detaljno.xhtml?faces-redirect=true";
	}
	
	public Poruka najnovijaPoruka(int kontaktId) {
		
		int mojId = Util.getKorisnik().getId();
		
		Poruka poruka = PorukaDAO.selectNajnovijaPorukaOdKontakta(mojId, kontaktId);
		
		return poruka;
		
	}

	public void setKontakti(ArrayList<Korisnik> kontakti) {
		this.kontakti = kontakti;
	}

	
}
