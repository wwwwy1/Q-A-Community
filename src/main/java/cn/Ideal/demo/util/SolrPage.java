package cn.Ideal.demo.util;

import lombok.Data;

import java.util.List;

@Data
public class SolrPage<T> {
	private List<T> data;
	private Integer pageNum; // 当前页
	private Integer pages; // 总页数
	private String keyWords; // 查询词

	public SolrPage(List<T> data, Integer pageNum, Integer pages, String keyWords) {
		this.data = data;
		this.pageNum = pageNum;
		this.pages = pages;
		this.keyWords = keyWords;
	}
	public SolrPage(List<T> data, Integer pageNum, Integer pages) {
		this.data = data;
		this.pageNum = pageNum;
		this.pages = pages;
	}
}
