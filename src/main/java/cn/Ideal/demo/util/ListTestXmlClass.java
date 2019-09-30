package cn.Ideal.demo.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;
@XStreamAlias("Data")
public class ListTestXmlClass {
	@XStreamImplicit(itemFieldName = "Head")
	private List<TestXmlClass> data;
	private TestXmlClass head;
	public List<TestXmlClass> getList() {
		return data;
	}

	public void setList(List<TestXmlClass> data) {
		this.data = data;
	}
}
