package Model;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class test2 {
	public static void main(String[] args) {
		File file = new File("D:\\Project-Java\\Server\\src\\Model\\NewFile.xml"); 
		try {
			 DocumentBuilderFactory dbFactory = 
	                    DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         
	         
	         Element rootElement = doc.createElement("class");
	         
	 
	    	 
	         Element product = doc.createElement("account");
	         rootElement.appendChild(product);
	            
	            
	         Element id = doc.createElement("username");
	         id.appendChild(doc.createTextNode("admin"));
	         product.appendChild(id);
	           
	         Element name = doc.createElement("password");
	         name.appendChild(doc.createTextNode("123"));
	         product.appendChild(name);
	         
	         
	      
	         System.out.println("hanh");
	         
	        
	         
	           
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
