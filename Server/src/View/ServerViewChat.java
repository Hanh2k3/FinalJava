package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;

public class ServerViewChat extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnNewButton;
	private ServerSocket serverSocket ;
	private Socket socket ; 
	private DataInputStream dataInputStream; 
	private DataOutputStream dataOutputStream ; 
	private String name ; 

	public ServerViewChat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 282, 270, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Send");
		btnNewButton.setBackground(new Color(255, 255, 204));
		btnNewButton.setBounds(290, 282, 107, 31);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")) {
					return; 
				} else {
					String temp = textField.getText(); 
					
					try {
						dataOutputStream.writeUTF(temp);
						textArea.append("\n" +"Server: " + "" + temp);
						textField.setText("");
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
					
				}
				
			}
		});
		contentPane.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 28, 392, 239);
		contentPane.add(textArea);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		System.out.println("co vo trong socket thread ");
		try {
			serverSocket = new ServerSocket(1234);
			socket = serverSocket.accept(); 
			System.out.println("socket connected ! ");
			dataInputStream = new DataInputStream(socket.getInputStream()); 
			dataOutputStream = new DataOutputStream(socket.getOutputStream()); 
			name = dataInputStream.readUTF(); 
			while(true) {
				String temp = dataInputStream.readUTF(); 
				
				textArea.append("\n" + name + " " + temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	

}
