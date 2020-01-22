package util;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class Sha3Enkripcija {
	
	public static String encrypt(String input) {
		
		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
	    byte[] digest = digestSHA3.digest(input.getBytes());
	    
	    return Hex.toHexString(digest);
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(encrypt("david"));
		
	}

}
