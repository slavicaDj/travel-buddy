package model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Karta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8150029170352568668L;

	private String polaziste;
	private String destinacija;
	private Prevoz prevoz;
	private Double cijena;
	private Date vrijemePolaska;
	private Date vrijemeDolaska;
	
	public Karta() {
		
	}
	
	public Karta(String polaziste, String destinacija, Prevoz prevoz, Double cijena, Date vrijemePolaska, Date vrijemeDolaska) {
		this.polaziste = polaziste;
		this.destinacija = destinacija;
		this.prevoz = prevoz;
		this.cijena = cijena;
		this.vrijemePolaska = vrijemePolaska;
		this.vrijemeDolaska = vrijemeDolaska;
	}

	public String getPolaziste() {
		return polaziste;
	}
	
	public void setPolaziste(String polaziste) {
		this.polaziste = polaziste;
	}
	
	public String getDestinacija() {
		return destinacija;
	}
	
	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}
	
	public Prevoz getPrevoz() {
		return prevoz;
	}
	
	public void setPrevoz(Prevoz prevoz) {
		this.prevoz = prevoz;
	}
	
	public double getCijena() {
		return cijena;
	}
	
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	
	public Date getVrijemePolaska() {
		return vrijemePolaska;
	}
	
	public void setVrijemePolaska(Date vrijemePolaska) {
		this.vrijemePolaska = vrijemePolaska;
	}
	
	public Date getVrijemeDolaska() {
		return vrijemeDolaska;
	}
	
	public void setVrijemeDolaska(Date vrijemeDolaska) {
		this.vrijemeDolaska = vrijemeDolaska;
	}

	@Override
	public String toString() {
		return "Polaziste: " + polaziste + ",  destinacija: " + destinacija + ",  prevoz: " + prevoz + ",  cijena: "
				+ cijena + ", vrijemePolaska: " + vrijemePolaska + ", vrijemeDolaska: " + vrijemeDolaska;
	}
	
	

}

