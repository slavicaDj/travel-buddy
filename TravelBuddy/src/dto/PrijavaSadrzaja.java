package dto;

import java.io.Serializable;
import java.util.Date;

public class PrijavaSadrzaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8240246967300377506L;
	
	private int id;
	private Oglas oglas;
	private Komentar komentar;
	private Poruka poruka;
	private Korisnik korisnik;
	private String napomena;
	private Date vrijeme;
	private boolean aktivan;
	
	public PrijavaSadrzaja() {
		
	}

	public PrijavaSadrzaja(Oglas oglas, Komentar komentar, Poruka poruka, Korisnik korisnik, String napomena) {
		this.oglas = oglas;
		this.komentar = komentar;
		this.poruka = poruka;
		this.korisnik = korisnik;
		this.napomena = napomena;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public Komentar getKomentar() {
		return komentar;
	}

	public void setKomentar(Komentar komentar) {
		this.komentar = komentar;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Date getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Poruka getPoruka() {
		return poruka;
	}

	public void setPoruka(Poruka poruka) {
		this.poruka = poruka;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
