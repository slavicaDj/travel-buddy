package dto;

import java.io.Serializable;
import java.util.Date;

import util.Util;

public class Oglas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -455649671526703465L;
	
	private int id;
	private String naziv;
	private String kategorija;
	private Date vrijemeKreiranja;
	private Date vrijemePolaska;
	private String lokacijaPolazak;
	private String lokacijaDolazak;
	private int brojOsoba;
	private Korisnik korisnik;
	private String mapaPolazak;
	private String mapaDolazak;
	private boolean zatvoren;
	private boolean aktivan;
	
	public Oglas() {
		
	}
	


	public Oglas(int id, String naziv, String kategorija, Date vrijemeKreiranja, Date vrijemePolaska,
			String lokacijaPolazak, String lokacijaDolazak, int brojOsoba) {
		this.id = id;
		this.naziv = naziv;
		this.kategorija = kategorija;
		this.vrijemeKreiranja = vrijemeKreiranja;
		this.vrijemePolaska = vrijemePolaska;
		this.lokacijaPolazak = lokacijaPolazak;
		this.lokacijaDolazak = lokacijaDolazak;
		this.brojOsoba = brojOsoba;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getKategorija() {
		return kategorija;
	}
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
	public Date getVrijemeKreiranja() {
		return vrijemeKreiranja;
	}
	public void setVrijemeKreiranja(Date vrijemeKreiranja) {
		this.vrijemeKreiranja = vrijemeKreiranja;
	}
	public String getLokacijaPolazak() {
		return lokacijaPolazak;
	}
	public void setLokacijaPolazak(String lokacijaPolazak) {
		this.lokacijaPolazak = lokacijaPolazak;
	}
	public String getLokacijaDolazak() {
		return lokacijaDolazak;
	}
	public void setLokacijaDolazak(String lokacijaDolazak) {
		this.lokacijaDolazak = lokacijaDolazak;
	}
	public int getBrojOsoba() {
		return brojOsoba;
	}
	public void setBrojOsoba(int brojOsoba) {
		this.brojOsoba = brojOsoba;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public String getMapaDolazak() {
		return mapaDolazak;
	}
	public void setMapaDolazak(String mapaDolazak) {
		this.mapaDolazak = mapaDolazak;
	}
	public boolean isZatvoren() {
		return zatvoren;
	}
	public void setZatvoren(boolean zatvoren) {
		this.zatvoren = zatvoren;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Date getVrijemePolaska() {
		return vrijemePolaska;
	}

	public void setVrijemePolaska(Date vrijemePolaska) {
		this.vrijemePolaska = vrijemePolaska;
	}

	public String getMapaPolazak() {
		return mapaPolazak;
	}

	public void setMapaPolazak(String mapaPolazak) {
		this.mapaPolazak = mapaPolazak;
	}



	@Override
	public String toString() {
		return "Naziv: " + naziv + ", kategorija: " + kategorija + ", vrijeme kreiranja: "
				+ Util.parseDate(vrijemeKreiranja, "dd.MM.yyyy HH:mm") + ", vrijeme polaska: " + Util.parseDate(vrijemePolaska, "dd.MM.yyyy HH:mm") + ", polazište: " + lokacijaPolazak
				+ ", dolazište: " + lokacijaDolazak + ", broj osoba: " + brojOsoba + ", korisnik: " + korisnik.getIme() + " " + korisnik.getPrezime();
	}
	
	

}
