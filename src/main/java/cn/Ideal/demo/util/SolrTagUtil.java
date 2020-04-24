package cn.Ideal.demo.util;

import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.entity.Tags;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SolrTagUtil {
	public static SolrClient client;
	private static String url;
	static {
		url = "http://localhost:8983/solr/tags";
		client = new HttpSolrClient.Builder(url).build();
	}
	public static  boolean batchSaveOrUpdate(List<Tags> entities) throws IOException, SolrServerException {
		DocumentObjectBinder binder = new DocumentObjectBinder();
		int total = entities.size();
		int count = 0;
		for (Tags entity : entities) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.setField("id",entity.getId());
			doc.setField("tagsName",entity.getTagsName());
			doc.setField("tagsRemarks",entity.getTagsRemarks());
			doc.setField("tagsCount",entity.getTagsCount());
			doc.setField("insertDate",Date.from(entity.getInsertDate().minusDays(7).toInstant(ZoneOffset.UTC)));
			LocalDateTime updateDate = entity.getUpdateDate()==null?entity.getInsertDate():entity.getUpdateDate();
			doc.setField("updateDate",Date.from(updateDate.minusDays(7).toInstant(ZoneOffset.UTC)));
			doc.setField("lastWeekCount",entity.getLastWeekCount());
			client.add(doc);
			System.out.printf("添加数据到索引中-Tags，总共要添加 %d 条记录，当前添加第%d条 %n",total,++count);
		}
		client.commit();
		return true;
	}
	public static QueryResponse queryById(int id) throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		SolrDocument byId = client.getById(String.valueOf(id));
		QueryResponse rsp = client.query(query);
		return rsp;
	}
	public static SolrPage queryHighlight(String keywords,Integer current,Integer pageSize,Integer rank) throws SolrServerException, IOException {
		// 标签字段
		// 关键词字段

		StringBuilder sb = new StringBuilder();
		if (!StringUtil.isNullOrSpace(keywords)){
			sb.append(" ((tagsName:\""+keywords+"\") ");
			sb.append(" OR (tagsRemarks:\""+keywords+"\"))");
		}
		// sb 就是query字段！！！
		SolrQuery q = new SolrQuery();
		if (StringUtil.isNullOrSpace(keywords)){
			q.setQuery("*")	;
		} else {
			q.setQuery(sb.toString());
			// 高亮字段
			q.addHighlightField("tagsName");
			// 高亮字段
			q.addHighlightField("tagsRemarks");
		}
		if (rank==null)rank=1;
		if (rank==1){
			q.addSort("insertDate",SolrQuery.ORDER.asc);
		}else if (rank==2){
			q.addSort("tagsName",SolrQuery.ORDER.asc);
		}else {
			q.addSort("tagsCount",SolrQuery.ORDER.desc);
		}
		q.set("qf","tagsName^3 tagsRemarks^5");
		//开始页数
		q.setStart((current-1)*pageSize);
		//每页显示条数
		q.setRows(pageSize);
		// 开启高亮
		q.setHighlight(true);

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
		List<Tags> tagsList = new ArrayList<>();
		int lastPage = (pageAll%pageSize)==0?pageAll/pageSize-1:pageAll/pageSize;
		int flag = 0;
		if (current == lastPage){
			lastPage = pageAll - (lastPage + 1) * pageSize;
			flag = 1;
		}
		int count = 0;
		for (SolrDocument solrDocument : results) {
			count++;
			Tags tags = new Tags();
			// 获取高亮显示的集合
			List<String> hightName = highlighting.get(solrDocument.get("id")).get("tagsName");
			List<String> hightRemarkss = highlighting.get(solrDocument.get("id")).get("tagsRemarks");
			String tagsName = CollectionUtils.isNotEmpty(hightName)? hightName.get(0) : (String) solrDocument.get("tagsName");
			String tagsRemarks = CollectionUtils.isNotEmpty(hightRemarkss)? hightRemarkss.get(0) : (String) solrDocument.get("tagsRemarks");
			tags.setId(Integer.valueOf((String) solrDocument.get("id")));
			tags.setTagsName(tagsName);
			tags.setTagsRemarks(tagsRemarks);
			tags.setLastWeekCount((Integer) solrDocument.get("lastWeekCount"));
			tags.setTagsCount((Integer) solrDocument.get("tagsCount"));
			tags.setInsertDate(((Date) solrDocument.get("insertDate")).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime());
			tags.setUpdateDate(((Date) solrDocument.get("updateDate")).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime());
			tagsList.add(tags);
		}
		SolrPage<Tags> jobsSolrPage = new SolrPage<Tags>(tagsList,current,((pageAll%pageSize)==0?pageAll/pageSize:pageAll/pageSize+1));
		return jobsSolrPage;
	}
	public static <T> boolean saveOrUpdate(T entity) throws SolrServerException, IOException {
		DocumentObjectBinder binder = new DocumentObjectBinder();
		SolrInputDocument doc = binder.toSolrInputDocument(entity);
		client.add(doc);
		client.commit();
		return true;
	}
	public static  boolean addForum(Tags entity) throws SolrServerException, IOException {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id",entity.getId());
		doc.setField("tagsName",entity.getTagsName());
		doc.setField("tagsRemarks",entity.getTagsRemarks());
		doc.setField("tagsCount",entity.getTagsCount());
		doc.setField("lastWeekCount",entity.getLastWeekCount());
		doc.setField("insertDate",Date.from(entity.getInsertDate().minusDays(7).toInstant(ZoneOffset.UTC)));
		LocalDateTime updateDate = entity.getUpdateDate()==null?entity.getInsertDate():entity.getUpdateDate();
		doc.setField("updateDate",Date.from(updateDate.minusDays(7).toInstant(ZoneOffset.UTC)));
		client.add(doc);
		client.commit();
		return true;
	}

	public static boolean deleteAll() {
		try {
			client.deleteByQuery("*");
			client.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
