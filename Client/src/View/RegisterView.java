package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Encryption;
import Controller.EncryptionMD5;
import General.Iproduct;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class RegisterView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textUser;
	private JPasswordField textPassword;
	private JPasswordField textEnterPassword;
	private Iproduct iproduct ;
	private JButton btnCancel; 
	private JButton btnCancel_1;

	
	public RegisterView(Iproduct iproduct) {
		this.iproduct = iproduct; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 447);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTER");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(100, 39, 238, 60);
		contentPane.add(lblNewLabel);
		
		textUser = new JTextField();
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textUser.setBounds(182, 137, 194, 34);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPassword.setBounds(182, 196, 190, 34);
		contentPane.add(textPassword);
		
		textEnterPassword = new JPasswordField();
		textEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textEnterPassword.setBounds(182, 263, 190, 34);
		contentPane.add(textEnterPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\Users-Administrator-icon.png"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(25, 134, 149, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Confirm password:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(10, 263, 165, 33);
		contentPane.add(lblNewLabel_3);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(230, 230, 250));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCancel.setForeground(new Color(95, 158, 160));
		
		btnCancel.setBounds(219, 336, 135, 34);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\icons8-password-30.png"));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(25, 196, 149, 34);
		contentPane.add(lblNewLabel_1_1);
		
		btnCancel_1 = new JButton("Create");
		btnCancel_1.setForeground(new Color(95, 158, 160));
		btnCancel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCancel_1.setBackground(new Color(230, 230, 250));
		btnCancel_1.setBounds(51, 336, 135, 34);
		contentPane.add(btnCancel_1);
		btnCancel_1.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		if(a.equals("Cancel")) {
			this.dispose();
		}
		else {
			if(a.equals("Create")) {
				String pas1 = textEnterPassword.getText();
				String pas = textPassword.getText();
				
				if(!pas1.equals(pas)) {
					JOptionPane.showMessageDialog(this,
		                    "Enter error",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						addAccount(pas);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			
				
			}
		}
		
	}


	private void addAccount(String pas ) throws RemoteException, NoSuchAlgorithmException {
		
		EncryptionMD5 enMd5 = new EncryptionMD5(); 
		String a = enMd5.encryptPass(pas);
		System.out.println(a);
		String result = iproduct.register(textUser.getText(), a);
		if(result.equals("Success")) {
			JOptionPane.showMessageDialog(this,
                    "Create succes",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			if(result.equals("UserName alredy exits")) {
				JOptionPane.showMessageDialog(this,
	                    "UserName alredy exits",
	                    "Notify",
	                    JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
	}
}
