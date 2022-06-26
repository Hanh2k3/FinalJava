package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import Model.ServerImplement;
import View.ServerViewChat;


public class Server {
	public static void main(String[] args) {
		try {
			
			SecureRandom sr = new SecureRandom(); 
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024,sr);
			
			// Khoi tao cap khoa 
			KeyPair kp = kpg.genKeyPair(); 
			// public key 
			PublicKey publicKey = kp.getPublic(); 
			// privateKey
			
			PrivateKey privateKey = kp.getPrivate(); 
			
			File publicKeyFile = new  File("D:\\Project-Java\\publicKey.rsa");
			File privateKeyFile = new File("D:\\Project-Java\\privateKey.rsa");
			
			// luu publickey 
			
			FileOutputStream fos = new FileOutputStream(publicKeyFile); 
			fos.write(publicKey.getEncoded());
			fos.close();
			
			// luu private key 
			fos = new FileOutputStream(privateKeyFile); 
			fos.write(privateKey.getEncoded());
			fos.close();
			
			System.out.println("Create key success");

			
			
			
			LocateRegistry.createRegistry(200); 
			
			Naming.rebind("rmi://localhost:200/hanhdeptrai", new ServerImplement());
			System.out.println("Server !...");
			
			ServerViewChat serverViewChat = new ServerViewChat(); 
			serverViewChat.setVisible(true);
		
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	

}
