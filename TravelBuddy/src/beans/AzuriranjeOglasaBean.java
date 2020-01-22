package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import dao.OglasDAO;
import dto.Oglas;
import util.Consts;

@ManagedBean(name="azuriranjeOglasaBean")
@RequestScoped
public class AzuriranjeOglasaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8423912637430403933L;
	
	private Oglas oglas;
	private SelectItem[] kategorije = { new SelectItem(Consts.KR_TRAZIM, Consts.TRAZIM), new SelectItem(Consts.KR_NUDIM, Consts.NUDIM)};
	private SelectItem[] status = {new SelectItem(true,"Zatvoren")};
	
	public AzuriranjeOglasaBean() {
		
	}
	
	@PostConstruct
	public void init() {		
		setOglas((Oglas) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Consts.OGLAS_AZURIRANJE));
	}
	
	public String posaljiIzmjene() {
		
		if (OglasDAO.update(oglas)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(Consts.OGLAS_AZURIRANJE);
			oglas = new Oglas();
			FacesContext.getCurrentInstance().addMessage("formAzuriraj:messageAzuriraj", new FacesMessage("Oglas uspješno ažuriran!"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage("formAzuriraj:messageAzuriraj", new FacesMessage("Greška! Oglas nije ažuriran!"));
		}
		
		return null;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public SelectItem[] getKategorije() {
		return kategorije;
	}

	public void setKategorije(SelectItem[] kategorije) {
		this.kategorije = kategorije;
	}

	public SelectItem[] getStatus() {
		return status;
	}

	public void setStatus(SelectItem[] status) {
		this.status = status;
	}

	

}
