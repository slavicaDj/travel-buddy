package database.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;;

@XmlRootElement
public class Reklama implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -115437955479132292L;
	
	private int id;
	private String sadrzaj;
	private String slikaString;
	private String naziv;
	private transient String slikaPath;
	private transient String emailKorisnika;
	private transient Date datumPocetka;
	private transient Date datumZavrsetka;
	private transient Double cijenaPoDanu;

	public Reklama() {
		
	}




	public Reklama(int id, String sadrzaj, String slikaString, String naziv, String slikaPath, String emailKorisnika,
			Date datumPocetka, Date datumZavrsetka, Double cijenaPoDanu) {
		this.id = id;
		this.sadrzaj = sadrzaj;
		this.slikaString = slikaString;
		this.naziv = naziv;
		this.slikaPath = slikaPath;
		this.emailKorisnika = emailKorisnika;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.cijenaPoDanu = cijenaPoDanu;
	}




	public String getSlikaString() {
		return slikaString;
	}

	public void setSlikaString(String slikaString) {
		this.slikaString = slikaString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public String getSlikaPath() {
		return slikaPath;
	}

	public void setSlikaPath(String slikaPath) {
		this.slikaPath = slikaPath;
	}

	public String getEmailKorisnika() {
		return emailKorisnika;
	}

	public void setEmailKorisnika(String emailKorisnika) {
		this.emailKorisnika = emailKorisnika;
	}

	public Double getCijenaPoDanu() {
		return cijenaPoDanu;
	}

	public void setCijenaPoDanu(Double cijenaPoDanu) {
		this.cijenaPoDanu = cijenaPoDanu;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}


	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}


	public Date getDatumZavrsetka() {
		return datumZavrsetka;
	}


	public void setDatumZavrsetka(Date datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}
	
	public void print() {
		System.out.println(sadrzaj + " " + datumPocetka + " " + datumZavrsetka); 
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	

}
