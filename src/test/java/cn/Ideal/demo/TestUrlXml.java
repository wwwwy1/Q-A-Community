package cn.Ideal.demo;

import cn.Ideal.demo.util.TestXmlClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUrlXml {
	public static void main(String[] args) {
		/*String url = "";
		url = "http://192.168.0.220/cgi-bin/fglccgi/topprod/tiptop/out/CUSTOMER12019071609310273200.xml";
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document document = builder.parse(url);
			NodeList list = document.getElementsByTagName("Head");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				TestXmlClass student = new TestXmlClass();
				student.setOcc01(element.getElementsByTagName("occ01").item(0).getTextContent());
				student.setOcc02(element.getElementsByTagName("occ02").item(0).getTextContent());
				student.setOcc18(element.getElementsByTagName("occ18").item(0).getTextContent());
				student.setOccacti(element.getElementsByTagName("occacti").item(0).getTextContent());
				String date=element.getElementsByTagName("occdate").item(0).getTextContent().trim();
				SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
				//student.setOccdate(formatter.parse(date));
				System.out.println(student);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} *//*catch (ParseException e) {
			e.printStackTrace();
		}*/
	}
}
