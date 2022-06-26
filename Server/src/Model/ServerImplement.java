package Model;

import General.Iproduct;
import General.Product;
import Server.IServerChat;
import XML.LinhKien;
import XML.xml;
import xmlRead.Account;
import xmlRead.readAdmin;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.html.parser.DocumentParser;
import org.w3c.dom.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;
import java.io.InputStream;
import Client.IClient;
import Controller.Decryption;

public class ServerImplement extends UnicastRemoteObject implements inforDataBase, Iproduct{

	

	private Connection conn;
	private org.w3c.dom.Document doc;
	
	
	
	
	public ServerImplement() throws RemoteException {
		
				try {
					creareDocument();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	
	private static final long serialVersionUID = 1L;
	
	public static Connection connection() {
		Connection conn = null ;
		try {
			  conn = DriverManager.getConnection(connectionURL, userName, password);
			  return conn; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return  conn; 
		
	}
	
	// kiểm tra xem sản phẩm đó đã tồn tại hay chưa 

	@Override
	public boolean check(int id) throws RemoteException {
		Connection conn = connection(); 
		
		String sql = "SELECT ID FROM ProductTBL ORDER BY ID ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int temp = rs.getInt("ID");
				if(id==temp) return true ;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	
	// nếu thêm vào sản phẩm mới thì sẽ thêm theo kiểu bình thường  
	

	@Override
	public String insert(int id, String name, float price, int amount) throws RemoteException {
		Connection conn = connection(); 
		int rc = 0 ; 

		String sql = "INSERT INTO ProductTBL (ID, Name, Price, Amount)"
			    + "VALUES (?,?,?,?)";
		 
		
		try {
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setFloat(3, price);
			ps.setInt(4, amount);
			rc = ps.executeUpdate(); 
			
	
		
		} catch (Exception e) {
			// TODO: handle exception
		}
					
		return "Insert success!";
	}

	@Override
	public String delete(int id) throws RemoteException {
		
		
		Connection conn = connection();
		String sql = "DELETE FROM ProductTBL " + "WHERE ID= ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate(); 
			return "Delete success! ";
		
			
		} catch (Exception e) {
			// TODO: handle exception 
		}
		return null ;
		
	}

	@Override
	public String update(int id, String name, float price, int amount ) throws RemoteException {
		String result = "fail"; 
		int rc =0 ; 
		Connection conn = connection(); 
		String sql = "UPDATE ProductTBL SET"  
				      + " Name = ?,  "+ "Price= ? ," + "Amount = ? "
				      + "WHERE ID = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setFloat(2, price);
			ps.setInt(3, amount);
			ps.setInt(4, id);
			rc = ps.executeUpdate();
			
			return "Update success!";	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
		
		
		
	}

	@Override
	public String login(String userName, String password) throws RemoteException {
		
		try {
			Connection conn = connection() ; 
			String sql = "SELECT * FROM AccountTBL";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				String name = rs.getString("UserName").trim();
				System.out.println(name);
				
				if(name.equals(userName)) {
					String pass = rs.getString("PassWord").trim();
					
					System.out.println(password + "passFrom");
					System.out.println(pass + "pass form dataBase");
					if(!password.equals(pass)) {
						return "false";
					}
					else {
						return "True";
					}
					
				}
				
			}
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "false";
	}

	// register 
	
	
	
	private Decryption Decryption() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String register(String userName, String password) throws RemoteException {
		Connection conn = connection(); 
		String sql1 = "SELECT UserName FROM AccountTBL";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				String name = rs.getString("UserName");
				String temp = name.trim(); 
				if(userName.equals(temp)) return "UserName alredy exits";
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		String sql = "INSERT INTO AccountTBL (UserName, PassWord) VALUES(?,?)" ;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.executeUpdate(); 
			return "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null; 	
		
		
	}
	
	public Product data() {
		Connection conn = connection(); 
		
		String sql = "SELECT * FROM ProductTBL ORDER BY ID ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id =   rs.getInt("ID"); 
				String name = rs.getString("Name"); 
				float price  = rs.getFloat("Price");
				int amount = rs.getInt("Amount"); 
				Product temp = new Product(id, name, price, amount); 
				return temp ; 
				
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null ;
		
		
		
		
		
		
	}



       

        @Override
        public DefaultTableModel addDataIntoTable() throws RemoteException {
            String columnNames[] = {"ID","Name","Price","Amount"}; 
            DefaultTableModel defaultTableModel = new DefaultTableModel(columnNames, 0); 
            Connection conn = connection(); 
    		
    		String sql = "SELECT * FROM ProductTBL ORDER BY ID ";
    		
    		try {
    			PreparedStatement ps = conn.prepareStatement(sql);
    			ResultSet rs = ps.executeQuery();
    			
    			while(rs.next()) {
    				Vector temp  = new Vector<>(); 
    				int id =   rs.getInt("ID"); 
    				String name = rs.getString("Name"); 
    				float price  = rs.getFloat("Price");
    				int amount = rs.getInt("Amount"); 
    				temp.add(id); 
    				temp.add(name);
    				temp.add(price);
    				temp.add(amount);
    					
    				defaultTableModel.addRow(temp);
    				
    						    				
    			}
    		} catch (Exception e) {
    			// TODO: handle exception
    		}
            
                       
            return defaultTableModel ; 
            
        }

		@Override
		public Product search(int arg0) throws RemoteException {
			Connection conn = connection(); 
			
			String sql = "SELECT * FROM ProductTBL ORDER BY ID ";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int id =   rs.getInt("ID"); 
					String name = rs.getString("Name"); 
					float price  = rs.getFloat("Price");
					int amount = rs.getInt("Amount"); 
					if(arg0==id) {
						Product temp = new Product(id, name, price, amount); 
						return temp ; 
					}
							
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null ;
			
			

		

	
		
	
		}
		
		
		
		// xml 
		
		public void  creareDocument() throws Exception {
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance(); 
			
			
			documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder(); 
			
			try {
				doc =  documentBuilder.parse(new File("D:\\Project-Java\\Server\\src\\Model\\NewFile.xml"));
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	
		
		}
		

//		

		@Override
		public boolean check(String arg0, String arg1) throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public General.Account getAccount(String arg0, String arg1) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void writeXml(String name, String password) throws RemoteException {
			
			File file = new File("D:\\Project-Java\\Server\\src\\Model\\NewFile.xml"); 
			try {
				
				
				Element elementRoot = doc.getDocumentElement();
				
				Element element = doc.createElement("account"); 
					
				Element name1 = doc.createElement("username"); 
				name1.setTextContent(name);
				element.appendChild(name1);
				
				Element pass = doc.createElement("password"); 
				pass.setTextContent(password);
				element.appendChild(pass); 
				
				elementRoot.appendChild(element);
				 TransformerFactory transformerFactory = TransformerFactory.newInstance();
				  Transformer transformer = transformerFactory.newTransformer();
		            DOMSource domSource = new DOMSource(doc);
		            StreamResult streamResult = new StreamResult(file);
		            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		            transformer.transform(domSource, streamResult);
				
				System.out.println("Success add element!");
				
				
						
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		
		
		

		
		
		
	
			
		
		
}

