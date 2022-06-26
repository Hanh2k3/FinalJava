package Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter.BigDecimalLayoutForm;

public class EncryptionMD5 {
	
	
	public String encryptPass(String pass) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		
		byte[] messageDigest = md.digest(pass.getBytes()); 
		
		BigInteger bigInt = new BigInteger(1, messageDigest);
		
		return bigInt.toString(16);
		
	}

}
