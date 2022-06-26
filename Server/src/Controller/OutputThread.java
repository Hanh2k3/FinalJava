package Controller;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class OutputThread extends Thread {
	
	Socket socket ; 
	JTextArea txt ; 
	BufferedReader bf ;
	String sender  ;
	String receiver ; 
	
	
	
	public OutputThread(Socket socket, JTextArea txt, BufferedReader bf, String sender, String receiver) {
		
		this.socket = socket;
		this.txt = txt;
		this.bf = bf;
		this.sender = sender;
		this.receiver = receiver;
		
		
		
	}


    @Override
	public void run() {
		while(true) {
			try {
				if(socket!=null) {
					String msg=""; 
					if((msg=bf.readLine())!=null && msg.length()>0) {
						txt.append("\n" + receiver + ": " + msg );
					}
					Thread.sleep(1000);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}

