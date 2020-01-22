package dto;

import java.io.Serializable;
import java.util.Date;

import util.Util;

public class Poruka implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7519960996907177079L;
	
	private int id;
	private Korisnik posiljalac;
	private Korisnik primalac;
	private Date vrijeme;
	private String sadrzaj;
	private boolean aktivan;

	public Poruka() {
		
	}

	public Poruka(int id, Korisnik posiljalac, Korisnik primalac, Date vrijeme, String sadrzaj) {
		this.id = id;
		this.posiljalac = posiljalac;
		this.primalac = primalac;
		this.vrijeme = vrijeme;
		this.sadrzaj = sadrzaj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Korisnik getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(Korisnik posiljalac) {
		this.posiljalac = posiljalac;
	}

	public Korisnik getPrimalac() {
		return primalac;
	}

	public void setPrimalac(Korisnik primalac) {
		this.primalac = primalac;
	}

	public Date getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	@Override
	public String toString() {
		return "Pošiljalac: " + posiljalac.getIme() + " " + posiljalac.getPrezime() + ", primalac: " + primalac.getIme() + " " + primalac.getPrezime()  + ", vrijeme: " + Util.parseDate(vrijeme, "dd.MM.yyyy HH:mm") + ", sadržaj: " + sadrzaj;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	

}
