package Controller;

import java.rmi.Naming;
import java.rmi.Remote;
import General.Iproduct;
import View.LoginView;
import View.MainView;

public class Client {
	
	public static void main(String[] args) {
		try {
			Remote remote = Naming.lookup("rmi://localhost:200/hanhdeptrai");
			Iproduct iproduct = (Iproduct) remote ; 
			
			LoginView loginView = new LoginView(iproduct); 
			loginView.setVisible(true); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

}
  