package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.EncryptionMD5;
import General.Iproduct;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class LoginView extends JFrame implements ActionListener  {

	private JPanel contentPane;
	private JTextField textUserName;
	private JPasswordField textPassWord;
	private JButton btnLogin;
	private JButton btnRegister;
	private Iproduct iproduct;
	private JCheckBox remember; 
	private String re =""; 
	
	

	 
	public LoginView(Iproduct iproduct) {
		this.iproduct = iproduct; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 297, 327);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\logo-he-thong-ban-le-bestbuy.jpg"));
		lblNewLabel_1.setBounds(-39, 0, 336, 327);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 218, 185));
		panel_1.setBounds(308, 0, 427, 327);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterView(iproduct); 
				
			}
		});
		btnRegister.setBackground(new Color(176, 224, 230));
		btnRegister.setFont(new Font("Montserrat", Font.BOLD, 16));
		btnRegister.setForeground(new Color(139, 0, 0));
		btnRegister.setBounds(245, 254, 119, 28);
		panel_1.add(btnRegister);
		
		btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(176, 224, 230));
		btnLogin.setForeground(new Color(165, 42, 42));
		btnLogin.setFont(new Font("Montserrat", Font.BOLD, 16));
		btnLogin.setBounds(80, 254, 119, 28);
		panel_1.add(btnLogin);
		
		textUserName = new JTextField();
		textUserName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textUserName.setBackground(new Color(255, 255, 255));
		textUserName.setBounds(160, 128, 227, 28);
		panel_1.add(textUserName);
		textUserName.setColumns(10);
		
		textPassWord = new JPasswordField();
		textPassWord.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPassWord.setBackground(new Color(255, 255, 255));
		textPassWord.setBounds(160, 178, 227, 28);
		panel_1.add(textPassWord);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(153, 50, 204));
		lblNewLabel.setFont(new Font("Montserrat ExtraBold", Font.BOLD, 36));
		lblNewLabel.setBounds(142, 11, 119, 106);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblNewLabel_2.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\Users-Administrator-icon.png"));
		lblNewLabel_2.setBounds(10, 119, 140, 42);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password:");
		lblNewLabel_2_1.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\icons8-password-30.png"));
		lblNewLabel_2_1.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(10, 173, 130, 34);
		panel_1.add(lblNewLabel_2_1);
		
		remember = new JCheckBox("Remember?");
		remember.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 16));
		remember.setBackground(new Color(255, 218, 185));
		remember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				re = "true"; 
				System.out.println("da tick");
				
			}
		});
		remember.setBounds(160, 213, 227, 23);
		panel_1.add(remember);
		btnLogin.addActionListener(this);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String a = e.getActionCommand(); 
		if(a.equals("Login")) {
			String username = textUserName.getText(); 
			String password = textPassWord.getText(); 
			
			
			try {
				login(username, password);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
	}


	private void login(String userName, String password) throws RemoteException, NoSuchAlgorithmException {
		EncryptionMD5 encryptionMD5 = new EncryptionMD5(); 
		String pass = encryptionMD5.encryptPass(password); 
		String result = iproduct.login(userName, pass); 
		System.out.println(result);
		
		if(result.equals("false")) {
			JOptionPane.showMessageDialog(this,
                    "Incorrect account or password",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
		} else {
			new MainView(iproduct, textUserName.getText(), pass,  re); 
			this.dispose();
		}
		
		
	}
}