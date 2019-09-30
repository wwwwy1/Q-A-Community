package cn.Ideal.demo;

import cn.Ideal.demo.util.TestXmlClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;


public class JaxSimpleDemo {
	private static TestXmlClass find() throws Exception {
		Document document = getDocument();
		NodeList list = document.getElementsByTagName("student");
		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			TestXmlClass student = new TestXmlClass();
			student.setOcc01(element.getElementsByTagName("occ01").item(0).getTextContent());
			student.setOcc02(element.getElementsByTagName("occ02").item(0).getTextContent());
			student.setOcc18(element.getElementsByTagName("occ18").item(0).getTextContent());
			student.setOccacti(element.getElementsByTagName("occacti").item(0).getTextContent());
			//student.setOccdate(new Date(element.getElementsByTagName("occdate").item(0).getTextContent()));
			System.out.println(student);
		}
		return null;
	}

	private static Document getDocument() throws ParserConfigurationException,
			IOException, org.xml.sax.SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream("http://192.168.0.220/cgi-bin/fglccgi/topprod/tiptop/out/CUSTOMER12019071609310273200.xml"));
		Element e = document.getDocumentElement();
		return document;
	}

	public static void main(String[] args) throws Exception {
		find();
	}
}
