package util;

import net.glxn.qrgen.QRCode;

public class QRGenerator {
	
	public static QRCode getQR(long timeInMillis) {
		
		return QRCode.from("" + timeInMillis);
		
	}

}
