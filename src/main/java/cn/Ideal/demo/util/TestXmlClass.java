package cn.Ideal.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class TestXmlClass {
	private String occ01;
	private String occ02;
	private String occ18;
	private String occacti;

	public TestXmlClass() {
	}

	public TestXmlClass(String occ01, String occ02, String occ18, String occacti, String occdate) {
		this.occ01 = occ01;
		this.occ02 = occ02;
		this.occ18 = occ18;
		this.occacti = occacti;
		this.occdate = occdate;
	}

	@Override
	public String toString() {
		return "TestXmlClass{" +
				"occ01='" + occ01 + '\'' +
				", occ02='" + occ02 + '\'' +
				", occ18='" + occ18 + '\'' +
				", occacti='" + occacti + '\'' +
				", occdate=" + occdate +
				'}';
	}

	public String getOcc01() {
		return occ01;
	}

	public void setOcc01(String occ01) {
		this.occ01 = occ01;
	}

	public String getOcc02() {
		return occ02;
	}

	public void setOcc02(String occ02) {
		this.occ02 = occ02;
	}

	public String getOcc18() {
		return occ18;
	}

	public void setOcc18(String occ18) {
		this.occ18 = occ18;
	}

	public String getOccacti() {
		return occacti;
	}

	public void setOccacti(String occacti) {
		this.occacti = occacti;
	}

	public String getOccdate() {
		return occdate;
	}

	public void setOccdate(String occdate) {
		this.occdate = occdate;
	}

	private String occdate;
}
