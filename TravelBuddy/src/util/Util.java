package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;

import beans.UserBean;
import dto.Korisnik;

public class Util {

	public static Date parseDate(String date, String format) {
	    SimpleDateFormat formatter = new SimpleDateFormat(format);
	    Date resultDate = null;
	    try {
			resultDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return resultDate;
	}
	
	public static String parseDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern(format);
		return formatter.format(date);
	}
	
	 public static Date parseJSONStringToDate(String jsonDatumString) {

		 return javax.xml.bind.DatatypeConverter.parseDateTime(jsonDatumString).getTime();
	        
	 }
	
	public static Korisnik getKorisnik() {
		
		UserBean korisnikBean = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("korisnikBean");
		return korisnikBean.getKorisnik();
		
	}
}


