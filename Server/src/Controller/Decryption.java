package Controller;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class Decryption {
	public Decryption() {
		
	}
	public static String getPassword(String password) {
		try {
			FileInputStream fis = new FileInputStream("D:\\Project-Java\\privateKey.rsa"); 
			byte[] b = new byte[fis.available()]; 
			fis.read(b); 
			fis.close();
			
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b); 
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = factory.generatePrivate(spec); 
			
			Cipher c = Cipher.getInstance("RSA"); 
			c.init(Cipher.DECRYPT_MODE, privateKey);
			String temp = "eneFg8n4vqwA43BymPKDAv79wCse6HpIiaRaM5urEiH52p4BVdNM2sBlLpCGQFeFSUTcevCtO8BS/QgZT2ORwPI7/A+kPdYq168sbDSY65ThkylgdkNWoJe6zva45nB/wy/Or6v4u9uz+PV6SNUQFZkIC7PlWPmPEMCV5/c2k/k=";
			byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(temp));
			String result = new String(decryptOut); 
			System.out.println(result);
			return result ; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Khong giai ma duoc"; 
		
	}
	public static void main(String[] args) {
		Decryption de = new Decryption(); 
		de.getPassword("ORwU");
		
	}

}
