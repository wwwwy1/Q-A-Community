package cn.Ideal.demo.util;

import cn.Ideal.demo.entity.Jobs;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JobUtil {

	public static List<Jobs> jobParse(String entity) {
		List<Jobs> data = new ArrayList<Jobs>();
		Document doc = Jsoup.parse(entity);
		Elements elements = doc.select("div.el");
		for (Element element : elements) {
			Jobs jobs = new Jobs();
			Elements title = element.select("p.t1").select("span").select("a"); //标题
			Elements complany = element.select("span.t2").select("a"); //公司
			Elements address = element.select("span.t3");//地址
			Elements salary = element.select("span.t4");//薪水
			Elements datas = element.select("span.t5");//发布日期
			if (title == null || title.text().equals("") || complany == null || complany.text().equals("") || address == null ||
				address.text().equals("") || salary == null || salary.text().equals("")|| datas == null || datas.text().equals("")) {
				continue;
			}
			String href = title.attr("href");
			if (href!=null && !href.equals("")) jobs.setUrl(href);
			else continue;
			jobs.setJobName(title.text());
			jobs.setCompanyName(complany.text());
			jobs.setWorkAddr(address.text());
			jobs.setSalary(salary.text());
			jobs.setPushDate(datas.text());
			data.add(jobs);
		}
		return data;
	}

	public static List<Jobs> getJobs(){
		//https://search.51job.com/list/080200%252C020000%252C070200%252C070300%252C080300,000000,0000,00,9,99,java,2,1.html
		//https://search.51job.com/list/080200%252C020000%252C070200%252C070300%252C080300,000000,0000,00,9,99,java,2,1.html?workyear=01
		int pagesize = 20;
		List<Jobs> ans = new ArrayList<>();
		for (int i = 1; i <= pagesize; i++) {
			String url = "https://search.51job.com/list/080200%252C020000%252C070200%252C070300%252C080300,000000,0000,00,9,99,java,2," + i + ".html";
			String s = HttpUtil.get(url);
			ans.addAll(JobUtil.jobParse(s));
		}
		return ans;
	}
}
