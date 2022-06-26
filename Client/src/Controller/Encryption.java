package Controller;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HexFormat;

import javax.crypto.Cipher;

public class Encryption {
	
	public static String enCryption(String password) {
		try {
			// Doc file chua public key 
			FileInputStream fis = new FileInputStream("D:\\Project-Java\\publicKey.rsa");
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			
			// Tao public key 
			
			X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
			KeyFactory factory = KeyFactory.getInstance("RSA"); 
			PublicKey pubkey = factory.generatePublic(spec);
			
			// ma hoa du lieu 
			Cipher c = Cipher.getInstance("RSA"); 
			c.init(Cipher.ENCRYPT_MODE, pubkey);
			
			
			byte encryptOut[] = c.doFinal(password.getBytes()); 
			
			String result = Base64.getEncoder().encodeToString(encryptOut);
			System.out.println(result);
			
			return result ; 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return " "; 
	}
	public static void main(String[] args) {
		Encryption en = new Encryption(); 
		en.enCryption("123"); 
		
	}
	
	

}
