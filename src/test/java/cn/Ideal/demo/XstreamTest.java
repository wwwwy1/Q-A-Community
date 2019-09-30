package cn.Ideal.demo;

import cn.Ideal.demo.util.ListTestXmlClass;
import cn.Ideal.demo.util.TestXmlClass;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class XstreamTest {

	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		//XStream xStream = new XStream(new Xpp3DomDriver());
		XStream xStream = new XStream(new Xpp3DomDriver());
		//xStream.alias("Data", ListTestXmlClass.class);
		//XStreamImplicit
		//xStream.addImplicitCollection(TestXmlClass.class,"Head");
		//xStream.alias("Data", ListTestXmlClass.class);
		//xStream.alias("Head", TestXmlClass.class);
		xStream.processAnnotations(ListTestXmlClass.class);
		//ListTestXmlClass result = (ListTestXmlClass) xStream.fromXML(xml);
		try {
			ListTestXmlClass result = (ListTestXmlClass) xStream.fromXML(new URL("http://192.168.0.220/cgi-bin/fglccgi/topprod/tiptop/out/CUSTOMER12019071615403388500.xml"));
			/*for (TestXmlClass testXmlClass : result.getList()) {
				System.out.println(testXmlClass);
			}*/
			System.out.println(df.format(new Date()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}


}
