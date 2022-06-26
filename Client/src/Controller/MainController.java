package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import General.Iproduct;
import View.MainView;

public class MainController implements ActionListener {
	
	private MainView mainView ; 
	private Iproduct iproduct;
	
	public MainController(MainView mainView, Iproduct iproduct) {
		this.iproduct = iproduct; 
		
		this.mainView = mainView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand(); 
		switch (a) {
		case "Reset":
			mainView.reset();
			
			break;
		case "Update" :
			
			try {
				mainView.modify();
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			break; 
		case "Add" : 
			try {
				mainView.add();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break ;
		case "Delete" : 
			
			try {
				mainView.remove();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			break ;
		case "Search": 
			try {
				mainView.search();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			

		default:
			break;
		}
	}

}
