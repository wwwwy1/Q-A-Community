package cn.Ideal.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Jobs implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer jobId;//自动增长id
	private String jobName; //岗位
	private String companyName;//公司名
	private String workAddr;//公司地址
	private String salary;//薪水
	private String pushDate;//发布日期
	private String url;//跳转地址
}