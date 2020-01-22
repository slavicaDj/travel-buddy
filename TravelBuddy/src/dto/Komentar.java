package dto;

import java.io.Serializable;
import java.util.Date;

import util.Util;

public class Komentar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8750895034823022711L;
	
	private int id;
	private Korisnik korisnik;
	private Oglas oglas;
	private Date vrijeme;
	private String sadrzaj;
	private boolean aktivan;

	public Komentar() {
		
	}

	public Komentar(int id, Korisnik korisnik, Oglas oglas, Date vrijeme, String sadrzaj) {
		this.id = id;
		this.korisnik = korisnik;
		this.oglas = oglas;
		this.vrijeme = vrijeme;
		this.sadrzaj = sadrzaj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
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
		return "Korisnik: " + korisnik.getIme() + " " + korisnik.getPrezime() + ", oglas: {" + oglas.toString() + "}, vrijeme: " + Util.parseDate(vrijeme, "dd.MM.yyyy HH:mm") + ", sadržaj: " + sadrzaj;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	
	

}
