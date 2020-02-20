package cn.Ideal.demo.util;

import cn.Ideal.demo.entity.Jobs;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolrUtil {
	public static SolrClient client;
	private static String url;
	static {
		url = "http://localhost:8983/solr/jobs";
		client = new HttpSolrClient.Builder(url).build();
	}
	public static <T> boolean batchSaveOrUpdate(List<T> entities) throws IOException, SolrServerException {
		DocumentObjectBinder binder = new DocumentObjectBinder();
		int total = entities.size();
		int count = 0;
		for (T entity : entities) {
			SolrInputDocument doc = binder.toSolrInputDocument(entity);
			client.add(doc);
			System.out.printf("添加数据到索引中，总共要添加 %d 条记录，当前添加第%d条 %n",total,++count);
		}
		client.commit();
		return true;
	}
	public static QueryResponse query(String keywords, int startOfPage, int numberOfPage) throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.setStart(startOfPage);
		query.setRows(numberOfPage);

		query.setQuery(keywords);
		QueryResponse rsp = client.query(query);
		return rsp;
	}
	public static SolrPage queryHighlight(String keywords,Integer current,Integer pageSize) throws SolrServerException, IOException {
		SolrQuery q = new SolrQuery();
		String keyWordsEnd = "";
		// 排序
		q.setSort("id",SolrQuery.ORDER.asc);
		if (keywords==null || keywords.equals(""))
			q.setQuery("*");
		else{
			keyWordsEnd = "jobName:" + keywords + " OR companyName:"+keywords+" OR workAddr:" + keywords;
			q.setQuery(keyWordsEnd);
		}
		//开始页数
		q.setStart(current); //需要-1
		//每页显示条数
		q.setRows(pageSize);
		// 设置查询关键字

		// 开启高亮
		q.setHighlight(true);
		// 高亮字段
		q.addHighlightField("jobName");
		// 高亮字段
		q.addHighlightField("companyName");
		// 高亮字段
		q.addHighlightField("workAddr");
		// 高亮单词的前缀
		q.setHighlightSimplePre("<span style='color:red'>");
		// 高亮单词的后缀
		q.setHighlightSimplePost("</span>");
		//摘要最长100个字符
		q.setHighlightFragsize(100);

		//查询
		QueryResponse query = client.query(q);
		int pageAll = (int)query.getResults().getNumFound(); //总记录数
		//获取查询结果
		SolrDocumentList results = query.getResults();
		Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
		List<Jobs> jobs = new ArrayList<>();
		int lastPage = (pageAll%pageSize)==0?pageAll/pageSize-1:pageAll/pageSize;
		int flag = 0;
		if (current == lastPage){
			lastPage = pageAll - (lastPage - 1) * pageSize;
			flag = 1;
		}
		int count = 0;
		for (SolrDocument solrDocument : results) {
			Jobs job = new Jobs();
			// 获取高亮显示的集合
			List<String> hightNames = highlighting.get(solrDocument.get("id")).get("jobName");
			List<String> hightCompanys = highlighting.get(solrDocument.get("id")).get("companyName");
			List<String> hightWorkAddrs = highlighting.get(solrDocument.get("id")).get("workAddr");
			String jobName = CollectionUtils.isNotEmpty(hightNames)? hightNames.get(0) : (String) solrDocument.get("jobName");
			String companyName = CollectionUtils.isNotEmpty(hightCompanys)? hightCompanys.get(0) : (String) solrDocument.get("companyName");
			String workAddr = CollectionUtils.isNotEmpty(hightWorkAddrs)? hightWorkAddrs.get(0) : (String) solrDocument.get("workAddr");
			job.setJobName(jobName);
			job.setCompanyName(companyName);
			job.setWorkAddr(workAddr);
			job.setPushDate((String) solrDocument.get("pushDate"));
			job.setSalary((String) solrDocument.get("salary"));
			job.setUrl((String) solrDocument.get("url"));
			jobs.add(job);
		}
		SolrPage<Jobs> jobsSolrPage = new SolrPage<Jobs>(jobs,current+1,((pageAll%pageSize)==0?pageAll/pageSize-1:pageAll/pageSize));
		return jobsSolrPage;
	}
	public static <T> boolean saveOrUpdate(T entity) throws SolrServerException, IOException {
		DocumentObjectBinder binder = new DocumentObjectBinder();
		SolrInputDocument doc = binder.toSolrInputDocument(entity);
		client.add(doc);
		client.commit();
		return true;
	}
	public static boolean deleteById(String id) {
		try {
			client.deleteById(id);
			client.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
