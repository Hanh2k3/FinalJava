package Model;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
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
import org.xml.sax.SAXException;

public class test {
	
	
	
	private org.w3c.dom.Document doc;
	
	public test() {
		
		
		try {
			creareDocument();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
	}
	
	
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
	
	public void addElement(String name, String password) {
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
	
	public void createXMLfile() {
		File file = new File("D:\\Project-Java\\Server\\src\\Model\\NewFile.xml"); 
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            // root element
            Element root = document.createElement("class");
            document.appendChild(root);
            // employee element
            Element employee = document.createElement("account");
            root.appendChild(employee);
            // set an attribute to staff element
         
         

            //you can also use staff.setAttribute("id", "1") for this
            // firstname element
            Element firstName = document.createElement("username");
            firstName.setTextContent("admin");
            employee.appendChild(firstName);
            // lastname element
            Element lastname = document.createElement("password");
            lastname.setTextContent("123");
            employee.appendChild(lastname);
            // email element
            
            
   
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);
            System.out.println("Done creating XML File");
        } catch (Exception pce) {
            pce.printStackTrace();
        }
		
	}

    public static void main(String argv[]) {
    	
    	test t = new test(); 
    	t.addElement("admint2", "456");
    	
    	
    	
    	
    }
}
