package cn.Ideal.demo.util;

import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.entity.Job;
import cn.Ideal.demo.service.IForumService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SolrForumUtil {
	public static SolrClient client;
	private static String url;
	static {
		url = "http://localhost:8983/solr/forum";
		client = new HttpSolrClient.Builder(url).build();
	}
	public static  boolean batchSaveOrUpdate(List<Forum> entities) throws IOException, SolrServerException {
		DocumentObjectBinder binder = new DocumentObjectBinder();
		int total = entities.size();
		int count = 0;
		for (Forum entity : entities) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.setField("id",entity.getId());
			doc.setField("forumContent",entity.getAbbreviationContent());
			doc.setField("forumTipNames",StringUtil.objectToString(entity.getForumTipNames()));
			doc.setField("forumTitle",entity.getForumTitle());
			doc.setField("insertDate",Date.from(entity.getInsertDate().minusDays(7).toInstant(ZoneOffset.UTC)));
			LocalDateTime updateDate = entity.getUpdateDate()==null?entity.getInsertDate():entity.getUpdateDate();
			doc.setField("updateDate",Date.from(updateDate.minusDays(7).toInstant(ZoneOffset.UTC)));
			client.add(doc);
			System.out.printf("添加数据到索引中-Forum，总共要添加 %d 条记录，当前添加第%d条 %n",total,++count);
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
	public static SolrPage queryHighlight(String keywords,Integer current,Integer pageSize) throws SolrServerException, IOException {
		// 标签字段
		List<String> tagsKey = StringUtil.extractMessageByBrackets(keywords);
		// 关键词字段
		List<String> questionKey = StringUtil.extractMessageByQuotationMarks(keywords);
		List<String> timeKey = StringUtil.extractMessageByTime(keywords);
		StringBuilder sb = new StringBuilder();
		if (tagsKey==null && questionKey == null && timeKey == null){
			sb.append(" ((forumContent:\""+keywords+"\") ");
			sb.append(" OR (forumTitle:\""+keywords+"\"))");
		}
		if (tagsKey!=null){
			for (int i = 0; i < tagsKey.size(); i++) {
				if (sb.length()!=0){
					sb.append(" AND (forumTipNames:\""+tagsKey.get(i)+"\") ");
				}else {
					sb.append("(forumTipNames:\""+tagsKey.get(i)+"\") ");
				}
			}
		}
		if (questionKey!=null){
			for (int i = 0; i < questionKey.size(); i++) {
				if (sb.length()!=0){
					sb.append(" AND ((forumContent:\""+questionKey.get(i)+"\") ");
					sb.append(" OR (forumTitle:\""+questionKey.get(i)+"\"))");
				}else {
					sb.append("((forumContent:\""+questionKey.get(i)+"\") ");
					sb.append(" OR (forumTitle:\""+questionKey.get(i)+"\"))");

				}
			}
		}
		if (timeKey!=null){
			if (sb.length()!=0){
				sb.append(" AND (insertDate:["+timeKey.get(0)+"T00:00:00Z TO "+timeKey.get(1)+"T23:59:59Z]");
			}else {
				sb.append(" (insertDate:["+timeKey.get(0)+"T00:00:00Z TO "+timeKey.get(1)+"T23:59:59Z]");

			}
		}
		// sb 就是query字段！！！
		SolrQuery q = new SolrQuery();

		if (StringUtil.isNullOrSpace(keywords)){
			q.setQuery("*")	;
		} else {
			q.setQuery(sb.toString());
			// 高亮字段
			q.addHighlightField("forumTitle");
			// 高亮字段
			q.addHighlightField("forumContent");
		}
		q.set("qf","forumTitle^3 forumContent^5");
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
		List<Forum> forums = new ArrayList<>();
		int lastPage = (pageAll%pageSize)==0?pageAll/pageSize-1:pageAll/pageSize;
		int flag = 0;
		if (current == lastPage){
			lastPage = pageAll - (lastPage + 1) * pageSize;
			flag = 1;
		}
		int count = 0;
		for (SolrDocument solrDocument : results) {
			count++;
			Forum forum = new Forum();
			// 获取高亮显示的集合
			List<String> hightTitles = highlighting.get(solrDocument.get("id")).get("forumTitle");
			List<String> hightContents = highlighting.get(solrDocument.get("id")).get("forumContent");
			String forumTitle = CollectionUtils.isNotEmpty(hightTitles)? hightTitles.get(0) : (String) solrDocument.get("forumTitle");
			String forumContent = CollectionUtils.isNotEmpty(hightContents)? hightContents.get(0) : (String) solrDocument.get("forumContent");
			forum.setId(Integer.valueOf((String) solrDocument.get("id")));
			forum.setForumTitle(forumTitle);
			forum.setAbbreviationContent(forumContent);
			forum.setForumTipNames(StringUtil.stringToObj((String)solrDocument.get("forumTipNames"),List.class));
			forum.setInsertDate(((Date) solrDocument.get("insertDate")).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime());
			forum.setUpdateDate(((Date) solrDocument.get("updateDate")).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime());
			forums.add(forum);
		}
		SolrPage<Forum> jobsSolrPage = new SolrPage<Forum>(forums,current,((pageAll%pageSize)==0?pageAll/pageSize:pageAll/pageSize+1));
		return jobsSolrPage;
	}
	public static <T> boolean saveOrUpdate(T entity) throws SolrServerException, IOException {
		DocumentObjectBinder binder = new DocumentObjectBinder();
		SolrInputDocument doc = binder.toSolrInputDocument(entity);
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
