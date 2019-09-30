package cn.Ideal.demo.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mxGraphModel")
public class MxGraphModel {

	private Object root;
	@XmlElement(name = "root")
	public Object getRoot() {
		return root;
	}

	public void setRoot(Object root) {
		this.root = root;
	}

}
