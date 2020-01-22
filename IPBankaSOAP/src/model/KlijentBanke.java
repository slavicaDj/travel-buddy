package model;

public class KlijentBanke {
	
	private String eMail;
	private String ime;
	private String prezime;
	private String brojKartice;
	private String tipKartice;
	private String datumIsteka;
	private String cvc;
	private double stanje;
	
	public KlijentBanke() {
		
	}
	
	public KlijentBanke(String eMail, String ime, String prezime, String brojKartice, String tipKartice, String datumIsteka, String cvc, double stanje) {
		this.eMail = eMail;
		this.ime = ime;
		this.prezime = prezime;
		this.brojKartice = brojKartice;
		this.tipKartice = tipKartice;
		this.datumIsteka = datumIsteka;
		this.cvc = cvc;
		this.stanje = stanje;
	}


	public String getEmail() {
		return eMail;
	}


	public void setEmail(String eMail) {
		this.eMail = eMail;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getBrojKartice() {
		return brojKartice;
	}


	public void setBrojKartice(String brojKartice) {
		this.brojKartice = brojKartice;
	}


	public String getTipKartice() {
		return tipKartice;
	}


	public void setTipKartice(String tipKartice) {
		this.tipKartice = tipKartice;
	}


	public String getDatumIsteka() {
		return datumIsteka;
	}


	public void setDatumIsteka(String datumIsteka) {
		this.datumIsteka = datumIsteka;
	}


	public String getCvc() {
		return cvc;
	}


	public void setCvc(String cvc) {
		this.cvc = cvc;
	}


	public double getStanje() {
		return stanje;
	}


	public void setStanje(double stanje) {
		this.stanje = stanje;
	}
	
	
	
	

}
