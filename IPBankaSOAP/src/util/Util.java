package util;

import java.util.Calendar;

public class Util {
	
	public static boolean isKarticaVazeca(String datumIsteka) {
		
		String mjesecKarticaString = datumIsteka.split("/")[0];
		if (mjesecKarticaString.startsWith("0")) {
			mjesecKarticaString.replaceAll("0", "");
		}
		int mjesecKartica = Integer.valueOf(mjesecKarticaString);
		int godinaKartica = Integer.valueOf("20" + datumIsteka.split("/")[1]);
		
		Calendar cal = Calendar.getInstance();
		int mjesec = cal.get(Calendar.MONTH) + 1;
		int godina = cal.get(Calendar.YEAR);
		
		if (godinaKartica > godina) {
			return true;
		}
		else if (godinaKartica == godina) {
			if (mjesecKartica < mjesec) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}		
		
	}

}
