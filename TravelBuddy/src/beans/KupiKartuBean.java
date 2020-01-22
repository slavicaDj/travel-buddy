package beans;

import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import clients.rest.IPBankRESTClient;
import clients.soap.IPBankSOAPClient;
import model.Karta;
import model.KlijentBanke;
import util.Consts;
import util.EmailClient;
import util.PdfGenerator;

@ManagedBean(name="kupiKartuBean")
@RequestScoped
public class KupiKartuBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3751813565305398294L;
	
	private KlijentBanke klijent;
	private Karta karta;
	
	public KupiKartuBean() {
		
	}
	
	@PostConstruct
	public void init() {
		
		karta = (Karta)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Consts.KARTA);
		klijent = new KlijentBanke();
		
	}
	
	public String izvrsiUplatu() {
		
		String odgovorProvjera = IPBankRESTClient.provjeriKlijenta(klijent.getEmail(), karta.getCijena());
		//provjeri stanje
		if (Consts.OK.equals(odgovorProvjera)) {
			
			//izvrsi uplatu putem soap servisa
			String odgovorUplata = IPBankSOAPClient.uplati(klijent, karta.getCijena());
			if (Consts.OK.equals(odgovorUplata)) {
				
			
				File kartaPdf = PdfGenerator.generisiKartu(klijent, karta);
				if (kartaPdf != null) {
					//posalji kartu na email
					EmailClient.sendMail(kartaPdf, klijent.getEmail());
					FacesContext.getCurrentInstance().addMessage("placanjeForm:placanjeMessages", new FacesMessage("Plaćanje uspješno! Karta je poslata na Vaš e-mail."));
				}		
				
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(Consts.KARTA);
				karta = new Karta();
				klijent = new KlijentBanke();

			}
			else {
				FacesContext.getCurrentInstance().addMessage("placanjeForm:placanjeMessages", new FacesMessage("Plaćanje neuspješno! Razlog: " + odgovorUplata));
			}
		
		}
		else {
			FacesContext.getCurrentInstance().addMessage("placanjeForm:placanjeMessages", new FacesMessage("Plaćanje neuspješno! Razlog: " +  odgovorProvjera));
		}
		
		return null;
	}

	public KlijentBanke getKlijent() {
		return klijent;
	}

	public void setKlijent(KlijentBanke klijent) {
		this.klijent = klijent;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

}
