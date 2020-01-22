package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import dao.OglasDAO;
import dto.Oglas;
import util.Consts;

@ManagedBean(name="pretragaBeanGost")
@SessionScoped
public class PretragaBeanGost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6461426077792506496L;
	
	private String kategorija;
	private ArrayList<Oglas> oglasiKategorija = new ArrayList<>();
	private SelectItem[] kategorije = {
									   new SelectItem(Consts.KR_TRAZIM, Consts.TRAZIM), 
									   new SelectItem(Consts.KR_NUDIM, Consts.NUDIM)
									  };
	

	public PretragaBeanGost() {
	}
	
	@PostConstruct
	public void init() {
		kategorija = Consts.KR_TRAZIM;
		setOglasiKategorija(OglasDAO.selectOglasiTrazimNudim(kategorija));

	}
		
	public void getOglasiTrazimNudimListener(AjaxBehaviorEvent event) {
		
		setOglasiKategorija(OglasDAO.selectOglasiTrazimNudim(kategorija));
	}


	public String getKategorija() {
		return kategorija;
	}


	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public ArrayList<Oglas> getOglasiKategorija() {
		return oglasiKategorija;
	}


	public void setOglasiKategorija(ArrayList<Oglas> oglasiKategorija) {
		this.oglasiKategorija = oglasiKategorija;
	}


	public SelectItem[] getKategorije() {
		return kategorije;
	}


	public void setKategorije(SelectItem[] kategorije) {
		this.kategorije = kategorije;
	}


	
	
}
