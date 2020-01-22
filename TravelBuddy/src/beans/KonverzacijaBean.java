package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.KorisnikDAO;
import dao.PorukaDAO;
import dto.Poruka;
import util.Consts;
import util.Util;

@ManagedBean(name="konverzacijaBean")
@ViewScoped
public class KonverzacijaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4706824615147211005L;
	
	private int kontaktId;
	private ArrayList<Poruka> poruke = new ArrayList<>();
	private String sadrzajPoruke;
	
	public KonverzacijaBean() {
		
	}
	
	@PostConstruct
	public void init() {
		
		kontaktId = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Consts.KONTAKT_ID);
		poruke = PorukaDAO.selectByUcesniciId(Util.getKorisnik().getId(), kontaktId);
		sadrzajPoruke = "";

	}
	
	public String posaljiPoruku() {
		
		sadrzajPoruke = sadrzajPoruke.trim();
		
		if (("".equals(sadrzajPoruke))) {
			FacesContext.getCurrentInstance().addMessage("formPoruka:messagesPoruka", new FacesMessage("Unesite sadržaj poruke!"));
			return null;
		}
		
		Poruka poruka = new Poruka();
		poruka.setSadrzaj(sadrzajPoruke);
		poruka.setPosiljalac(Util.getKorisnik());
		poruka.setPrimalac(KorisnikDAO.selectById(kontaktId));
		
		if(PorukaDAO.insert(poruka)) {
			poruke = PorukaDAO.selectByUcesniciId(Util.getKorisnik().getId(), kontaktId);
		}
		
		sadrzajPoruke = "";
		
		return null;
	}
	
	public String prijaviSadrzaj(int porukaId) {
				
		for (Poruka p : poruke) {
			if (p.getId() == porukaId && p.getPosiljalac().getId() != Util.getKorisnik().getId()) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.PRIJAVA_PORUKA, p);
				return "prijavi_sadrzaj.xhtml?faces-redirect=true";
			}
		}
		
		return null;
	}

	public ArrayList<Poruka> getPoruke() {
		return poruke;
	}

	public void setPoruke(ArrayList<Poruka> poruke) {
		this.poruke = poruke;
	}

	public String getSadrzajPoruke() {
		return sadrzajPoruke;
	}

	public void setSadrzajPoruke(String sadrzajPoruke) {
		this.sadrzajPoruke = sadrzajPoruke;
	}
	
	
	
	

}
