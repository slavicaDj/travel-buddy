package bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import clients.IPBankRESTClient;
import clients.ServisPlacanjeKlijent;
import database.dao.KorisnikDAO;
import database.dao.ReklamaDAO;
import database.dto.Reklama;
import model.KlijentBanke;
import util.Consts;

@ManagedBean (name="reklamaBean")
@SessionScoped
public class ReklamaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2382776866611876352L;
	private static final String dir = "C:\\Users\\HP KORISNIK\\Downloads\\workspace\\TravelAdvertiser\\WebContent\\reklame\\";
	
	private String tekstReklame;
	private int trajanjeReklameDan;
	private Part slika;
	
	private KlijentBanke klijentBanke = new KlijentBanke();
	
	private double cijenaReklameDan;


	public String posaljiReklamu() {
		
		String link = null;
		
		if (slika != null) {
			FileOutputStream outputStream;
			String path = dir + slika.getSubmittedFileName();
			try {
				outputStream = new FileOutputStream(path);
				IOUtils.copy(slika.getInputStream(), outputStream);
				outputStream.close();	
				
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Consts.IMG, path);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (trajanjeReklameDan > 1) {
			
			Random rand = new Random();
			cijenaReklameDan = (rand.nextDouble() + 0.1) * 100;
			
			link = "placanje.xhtml?faces-redirect=true";
		}
		else {
			
			obradiReklamu(false);
			FacesContext.getCurrentInstance().addMessage("indexForm:indexMessages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Reklama uspješno objavljena.", null));

		}
		
		return link;
	}
	
	
	public String izvrsiUplatu() {
		
		
		Double iznos = cijenaReklameDan * trajanjeReklameDan;
		
//		if (Consts.OK.equals(ServisPlacanjeKlijent.uplati(klijentBanke, iznos))) {
//			obradiReklamu(true);
//			//FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//			FacesContext.getCurrentInstance().addMessage("indexForm:indexMessages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Uplata uspješno izvršena.", null));
//			return "index.xhtml?faces-redirect=true";
//		}
//		else {
//			FacesContext.getCurrentInstance().addMessage("placanjeForm:placanjeMessages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Uplata nije izvršena! Provjerite unesene podatke!", null));
//			return null;
//		}		
		
		
		
		String odgovorProvjera = IPBankRESTClient.provjeriKlijenta(klijentBanke.getEmail(), iznos);
		//provjeri stanje
		if (Consts.OK.equals(odgovorProvjera)) {
			
			//izvrsi uplatu putem soap servisa
			String odgovorUplata = ServisPlacanjeKlijent.uplati(klijentBanke, iznos);
			if (Consts.OK.equals(odgovorUplata)) {
			
				obradiReklamu(true);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage("indexForm:indexMessages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Uplata uspješno izvršena.", null));
				return "index.xhtml?faces-redirect=true";

			}
			else {
				FacesContext.getCurrentInstance().addMessage("placanjeForm:placanjeMessages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Plaćanje neuspješno! Razlog: " + odgovorUplata, null));
				return null;
			}
		
		}
		else {
			FacesContext.getCurrentInstance().addMessage("placanjeForm:placanjeMessages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Plaćanje neuspješno! Razlog: " + odgovorProvjera, null));
			return null;
	    }
		
		
	}
	
	private void obradiReklamu(boolean placena) {
		
		Reklama reklama = new Reklama();
		if (placena) {
			if (!KorisnikDAO.selectKorisnik(klijentBanke.getEmail())) {
				KorisnikDAO.insert(klijentBanke);
			}
			reklama.setCijenaPoDanu(cijenaReklameDan);
			reklama.setEmailKorisnika(klijentBanke.getEmail());
		}
		
		reklama.setSadrzaj(tekstReklame);
		Date danas = Calendar.getInstance().getTime();
		reklama.setDatumPocetka(danas);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 24 * trajanjeReklameDan);
		Date sutra = cal.getTime();
		reklama.setDatumZavrsetka(sutra);
		
		int id = ReklamaDAO.insert(reklama);
		
		String path = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Consts.IMG);

		if (path != null) {
			FileOutputStream outputStream;
			String pathOutput = dir + id + "." + slika.getSubmittedFileName().split("\\.")[1];
			try {
				
				outputStream = new FileOutputStream(pathOutput);
				IOUtils.copy(new FileInputStream(new File(path)), outputStream);
				outputStream.close();	
				
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			ReklamaDAO.updateSlikaPath(pathOutput, id);
		}
		
		clearReklama();
		clearKlijentBanke();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(Consts.IMG);
	}

	public void setTrajanjeReklameDan(int trajanjeReklameDan) {
		this.trajanjeReklameDan = trajanjeReklameDan;
	}

	public String getTekstReklame() {
		return tekstReklame;
	}

	public void setTekstReklame(String tekstReklame) {
		this.tekstReklame = tekstReklame;
	}

	public int getTrajanjeReklameDan() {
		return trajanjeReklameDan;
	}


	public Part getSlika() {
		return slika;
	}


	public void setSlika(Part slika) {
		this.slika = slika;
	}

	private void clearReklama() {
		this.slika = null;
		this.tekstReklame = "";
		this.trajanjeReklameDan = 0;
	}
	
	private void clearKlijentBanke() {
		this.klijentBanke = new KlijentBanke();
	}


	public KlijentBanke getKlijent() {
		return klijentBanke;
	}


	public void setKlijent(KlijentBanke klijent) {
		this.klijentBanke = klijent;
	}


	public double getCijenaReklameDan() {
		return cijenaReklameDan;
	}


	public void setCijenaReklameDan(double cijenaReklameDan) {
		this.cijenaReklameDan = cijenaReklameDan;
	}
}
