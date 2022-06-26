package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import General.Iproduct;
import General.Product;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.MainController;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainView extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textAmount;
	private JTable table;
	private JTextField textSearch;
	private JTextField textSend;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnReset;
	private JButton btnLogout;
	private JTextArea textMessage;
	private JButton btnSend;
	private JButton btnSearch;
	private DefaultTableModel defaultTableModel ; 
    private Iproduct iproduct ;
	private JLabel labelTime;
	private JLabel labelDate;
	private DateTimeFormatter dtf;
	private LocalDate localDate;
	private LocalTime localTime;  
    private static int index = 0  ;
    private String username; 
    private Socket socket ; 
    private DataOutputStream dataOutputStream ; 
    private DataInputStream dataInputStream ; 
    private JTable table_1;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    
        
        public  void addData() {
            
            try {
                defaultTableModel = iproduct.addDataIntoTable();
                System.out.println("Hanh dep trai ");
            } catch (RemoteException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
	
	public MainView(Iproduct iproduct, String username, String password, String r)  {
		
		if(r.equals("true")) {
			
			try {
				iproduct.writeXml(username, password);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		
		}
		 ZoneId zoneHCM = ZoneId.of("Asia/Ho_Chi_Minh");
	     LocalDateTime today = LocalDateTime.now();
	     ZonedDateTime vnDateTime = ZonedDateTime.of(today, zoneHCM);
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	     
	     String time = formatter.format(vnDateTime); 
	     
	     
		
		
		
		
		this.username = username; 
		
		// time 
		 localDate = LocalDate.now();
		
		 localTime = LocalTime.now();
		 
		 
		 
		MainController mainController = new MainController(this, iproduct); 
		
		
		
                        
		this.iproduct = iproduct; 
        addData();
                
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBackground(new Color(253, 245, 230));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setForeground(Color.ORANGE);
		tabbedPane.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 196));
		panel.setForeground(Color.ORANGE);
		tabbedPane.addTab("Sản phẩm", null, panel, null);
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setForegroundAt(0, Color.ORANGE);
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 102, 51));
		panel_1.setBounds(0, 0, 1159, 76);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBounds(0, 0, 390, 76);
		panel_1.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\logo.png"));
		lblNewLabel_1.setBounds(0, 0, 390, 76);
		panel_6.add(lblNewLabel_1);
		
		labelTime = new JLabel( time + "");
		labelTime.setForeground(new Color(255, 153, 153));
		labelTime.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelTime.setBounds(583, 22, 166, 31);
		panel_1.add(labelTime);
		
		labelDate = new JLabel(localDate + "");
		labelDate.setForeground(new Color(255, 51, 255));
		labelDate.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelDate.setBounds(784, 22, 160, 31);
		panel_1.add(labelDate);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 248, 220));
		panel_3.setBounds(45, 87, 1076, 60);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Infomation Product");
		lblNewLabel_3.setBackground(new Color(255, 239, 213));
		lblNewLabel_3.setForeground(new Color(219, 112, 147));
		lblNewLabel_3.setFont(new Font("Montserrat ExtraBold", Font.BOLD, 25));
		lblNewLabel_3.setBounds(51, 11, 294, 38);
		panel_3.add(lblNewLabel_3);
		
		btnSearch = new JButton();
		btnSearch.setBackground(new Color(255, 248, 220));
		btnSearch.setBorder(null); 
		btnSearch.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\Search.png"));
		btnSearch.setBounds(902, 0, 79, 60);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					search(); 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		panel_3.add(btnSearch);
		
		
		
		textSearch = new JTextField();
		textSearch.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textSearch.setBounds(620, 11, 272, 38);
		panel_3.add(textSearch);
		textSearch.setColumns(10);
		
		
		JLabel lblNewLabel_6 = new JLabel("Search by ID");
		lblNewLabel_6.setFont(new Font("Montserrat SemiBold", Font.BOLD, 20));
		lblNewLabel_6.setBounds(479, 15, 139, 26);
		panel_3.add(lblNewLabel_6);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(495, 158, 626, 261);
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(defaultTableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow(); 
				displayDetail(index); 
			}
				
		});
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("ID: ");
		lblNewLabel.setBackground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 16));
		lblNewLabel.setBounds(58, 214, 124, 33);
		panel.add(lblNewLabel);
		
		textID = new JTextField();
		textID.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		textID.setBounds(192, 216, 205, 33);
		panel.add(textID);
		textID.setColumns(10);
		
		textName = new JTextField();
		textName.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		textName.setColumns(10);
		textName.setBounds(192, 299, 205, 33);
		panel.add(textName);
		
		textPrice = new JTextField();
		textPrice.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		textPrice.setColumns(10);
		textPrice.setBounds(192, 386, 205, 33);
		panel.add(textPrice);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 16));
		lblName.setBounds(58, 297, 124, 33);
		panel.add(lblName);
		
		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 16));
		lblPrice.setBounds(58, 386, 124, 33);
		panel.add(lblPrice);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Montserrat Medium", Font.BOLD, 17));
		btnUpdate.setBackground(new Color(255, 160, 122));
		btnUpdate.setBounds(801, 459, 124, 44);
		panel.add(btnUpdate);
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\add.png"));
		btnAdd.setFont(new Font("Montserrat Medium", Font.BOLD, 17));
		btnAdd.setBackground(new Color(255, 160, 122));
		btnAdd.setBounds(632, 458, 135, 45);
		panel.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 160, 122));
		btnDelete.setFont(new Font("Montserrat Medium", Font.BOLD, 17));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setIcon(new ImageIcon("D:\\Project-Java\\Final\\Imgae-icon\\delete.png"));
		btnDelete.setBounds(943, 459, 150, 44);
		
	
		panel.add(btnDelete);
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		lblNewLabel_2.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(63, 468, 104, 27);
		panel.add(lblNewLabel_2);
		
		textAmount = new JTextField();
		textAmount.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		textAmount.setBounds(192, 465, 205, 33);
		panel.add(textAmount);
		textAmount.setColumns(10);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			
				
			}
		});
		
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setFont(new Font("Montserrat Medium", Font.BOLD, 17));
		btnReset.setBackground(new Color(255, 160, 122));
		btnReset.setBounds(509, 459, 104, 45);
		panel.add(btnReset);
		
		btnLogout = new JButton("Log out");
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setBackground(new Color(255, 182, 193));
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView(iproduct);
				logout();
				
			}
		});
		btnLogout.setFont(new Font("Montserrat Medium", Font.BOLD, 17));
		btnLogout.setBounds(953, 514, 135, 39);
		panel.add(btnLogout);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Chat", null, panel_2, null);
		tabbedPane.setBackgroundAt(1, new Color(255, 255, 224));
		panel_2.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(157, 37, 760, 386);
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Message", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		textMessage = new JTextArea();
		textMessage.setFont(new Font("Montserrat Medium", Font.PLAIN, 17));
		panel_5.add(textMessage, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(157, 446, 749, 44);
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		textSend = new JTextField();
		textSend.setFont(new Font("Montserrat Medium", Font.BOLD, 17));
		textSend.setForeground(new Color(0, 0, 0));
		textSend.setBounds(0, 0, 595, 45);
		panel_7.add(textSend);
		textSend.setColumns(10);
		
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textSend.getText().equals("")) {
					return ; 
					
				} else {
					String a = textSend.getText(); 
					try {
						dataOutputStream.writeUTF(a);
						textMessage.append("\n" + username + ": "+ a );
						textSend.setText("");
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

				
			}

			
		});
		Thread t1 = new Thread(this); 
		t1.start();
		
		// chat // ************
		
		
		// chat end 
		btnSend.setBackground(Color.GRAY);
		btnSend.setBounds(622, 3, 137, 45);
		panel_7.add(btnSend);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Linh kiện", null, panel_8, null);
		panel_8.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(414, 11, 674, 291);
		panel_8.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_9.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(25, 33, 314, 450);
		panel_8.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel sfsfsff = new JLabel("ID:");
		sfsfsff.setFont(new Font("Montserrat ExtraBold", Font.PLAIN, 17));
		sfsfsff.setBounds(10, 80, 121, 33);
		panel_10.add(sfsfsff);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Montserrat ExtraBold", Font.PLAIN, 17));
		lblName_1.setBounds(10, 184, 121, 33);
		panel_10.add(lblName_1);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Montserrat ExtraBold", Font.PLAIN, 17));
		lblAmount.setBounds(10, 295, 121, 33);
		panel_10.add(lblAmount);
		
		textField = new JTextField();
		textField.setFont(new Font("Montserrat Black", Font.PLAIN, 16));
		textField.setBounds(10, 111, 241, 33);
		panel_10.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Montserrat Black", Font.PLAIN, 16));
		textField_1.setBounds(10, 215, 241, 35);
		panel_10.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Montserrat Black", Font.PLAIN, 16));
		textField_2.setBounds(10, 327, 241, 33);
		panel_10.add(textField_2);
		textField_2.setColumns(10);
        this.setVisible(true);
        
        btnReset.addActionListener(mainController); 
        btnAdd.addActionListener(mainController);
       
        btnDelete.addActionListener(mainController);
        btnLogout.addActionListener(mainController); 
        btnUpdate.addActionListener(mainController); 
        
        Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					 ZoneId zoneHCM = ZoneId.of("Asia/Ho_Chi_Minh");
				     LocalDateTime today = LocalDateTime.now();
				     ZonedDateTime vnDateTime = ZonedDateTime.of(today, zoneHCM);
				     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");	     
				     String time = formatter.format(vnDateTime); 
					localDate = LocalDate.now();
							
					labelDate.setText(localDate +"");
					labelTime.setText(time + "");
							
				}		
			}
		});
        t.start(); 
        
       
	}
	
	
	protected void logout() {
		this.dispose();
		
	}

	// search
	public  void search() throws RemoteException {
		int count = table.getRowCount(); 
		String temp = textSearch.getText();
		System.out.println(count);
		
	
		
		
		for(int i=0 ; i<count ; i++) {
			
			String ch =  table.getValueAt(i, 0) + "";
			if(ch.equals(temp)) {
				String id = table.getValueAt(i, 0) + "";
				String name = table.getValueAt(i, 1) + "";
				String price = table.getValueAt(i, 2) + "";
				String amount = table.getValueAt(i, 3) + "";
				
				textID.setText(id);
				textName.setText(name);
				textAmount.setText(amount);
				textPrice.setText(price);
				return ; 
							
			}
		}
		
		JOptionPane.showMessageDialog(this,
                "Not Found",
                "Error",
                JOptionPane.ERROR_MESSAGE);
		
		
		
	}
		
	
	public void reset() {
		
		textAmount.setText("");
		textID.setText("");
		textName.setText("");
		textPrice.setText("");
		
	}
	// update 
	public void modify() throws RemoteException {
		int id = Integer.parseInt(textID.getText());
		if(!iproduct.check(id)) {
			JOptionPane.showMessageDialog(this,
                    "Product not  exists",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
		} else {
			String name = textName.getText(); 
        	float price = Float.parseFloat(textPrice.getText());
        	int amount = Integer.parseInt(textAmount.getText());
        	String rs = iproduct.update(id, name, price, amount); 
        	
        	defaultTableModel = iproduct.addDataIntoTable(); 
        	table.setModel(defaultTableModel);
        	 			
		}
		
	}
	
	private void displayDetail(int index) {
		System.out.println(index);
		String id =  defaultTableModel.getValueAt(index, 0) + ""; 
		String name =  defaultTableModel.getValueAt(index, 1) + "";
		String Price =  defaultTableModel.getValueAt(index, 2) + "";
		String Amount = defaultTableModel.getValueAt(index, 3) + "";
		
		textID.setText(id);
		textName.setText(name);
		textPrice.setText(Price);
		textAmount.setText(Amount);
	}
	
	
	
	// add 
	
    public void add() throws RemoteException {
    	
    	
    	int id = Integer.parseInt(textID.getText());
    	if(iproduct.check(id)) {
    		JOptionPane.showMessageDialog(this,
                    "Product already exists",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
    			
    	} else {
    		String name = textName.getText(); 
        	float price = Float.parseFloat(textPrice.getText());
        	int amount = Integer.parseInt(textAmount.getText());
        	
        	String check = iproduct.insert(id, name, price, amount); 
        	System.out.println(check);
        	Vector temp = new Vector<>(); 
        	temp.add(id); 
        	temp.add(name);
        	temp.add(price);
        	temp.add(amount);
        	
        	defaultTableModel.addRow(temp);
        	table.setModel(defaultTableModel);
    		
    	}
    	
    
    
    
    	
    		
    }
    
 // delete 

	public void remove() throws RemoteException {
		int id = Integer.parseInt(textID.getText() + "");
		if(!iproduct.check(id)) {
			JOptionPane.showMessageDialog(this,
                    "Product not  exists",
                    "Error",
                    JOptionPane.ERROR_MESSAGE); 
			
		} else {
			String a = iproduct.delete(id); 
			
			System.out.println(a);
		    int amountRow = defaultTableModel.getRowCount(); 
		    
		    for(int i=0 ; i<amountRow; i++) {
		    	int tempID = Integer.parseInt( defaultTableModel.getValueAt(i, 0) + "");
		    	System.out.println(id + 1);
		    	    	
		    	if(tempID==id) {
		    		System.out.println(tempID + " dhs");
		    		
		    		defaultTableModel.removeRow(i);
		    		table.setModel(defaultTableModel);
		    		textID.setText("");
		    		textAmount.setText("");
		    		textName.setText("");
		    		textPrice.setText("");		
		    		return ; 
		    	}
		    	
		    }
		}
		
		
	}
	
	// sendMessage 
	

	@Override
	public void run() {
		try {
			
			socket = new Socket("localhost", 1234);
			dataInputStream = new DataInputStream(socket.getInputStream()); 
			dataOutputStream = new DataOutputStream(socket.getOutputStream()); 
			System.out.println("connect success!");
			
			dataOutputStream.writeUTF(username + ":");
			
			while(true) {
				String temp = dataInputStream.readUTF(); 
				textMessage.append("\n" + "Server :" + temp);
						
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
}