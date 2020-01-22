package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.OglasDAO;
import dto.Oglas;
import util.Consts;
import util.Util;

@ManagedBean(name="mojiOglasiBean")
@ViewScoped
public class MojiOglasiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1793019340407866233L;
	
	private ArrayList<Oglas> oglasi;
	
	public MojiOglasiBean() {
		
	}
	
	@PostConstruct
	public void init() {
		
		setOglasi(OglasDAO.selectByKorisnikId(Util.getKorisnik().getId()));
		
	}
	
	public String prikaziOglasDetaljno() {
		
		Map<String,String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int oglasId = Integer.valueOf(map.get("oglasId"));
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.OGLAS_ID, oglasId);
		
		return "oglas_detaljno?faces-redirect=true";
	}
	
	public String azurirajOglas(int oglasId) {
		
		for (Oglas o : oglasi) {
			if (o.getId() == oglasId) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.OGLAS_AZURIRANJE, o);
				return "azuriraj_oglas?faces-redirect=true";
			}
		}
		
		return null;		
		
	}

	public ArrayList<Oglas> getOglasi() {
		return oglasi;
	}

	public void setOglasi(ArrayList<Oglas> oglasi) {
		this.oglasi = oglasi;
	}

}
