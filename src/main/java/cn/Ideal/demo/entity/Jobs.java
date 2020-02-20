package cn.Ideal.demo.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

@Data
public class Jobs implements Serializable {

	private static final long serialVersionUID = 1L;
	@Field
	private String jobName; //岗位
	@Field
	private String companyName;//公司名
	@Field
	private String workAddr;//公司地址
	@Field
	private String salary;//薪水
	@Field
	private String pushDate;//发布日期
	@Field
	private String url;//跳转地址
}