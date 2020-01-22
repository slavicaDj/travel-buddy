package model;

import java.io.Serializable;

public class Reklama implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7543158793286632666L;
	
	private int id;
	private String sadrzaj;
	private String path;
	
	public Reklama() {
		
	}

	public Reklama(int id, String tekst, String path) {
		this.setId(id);
		this.sadrzaj = tekst;
		this.path = path;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}
	
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Reklama [id=" + id + ", sadrzaj=" + sadrzaj + ", path=" + path + "]";
	}
	
	

}
