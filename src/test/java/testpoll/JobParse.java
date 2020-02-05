package testpoll;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JobParse {

	public static List<Jobs> getData(String entity) {

		List<Jobs> data = new ArrayList<Jobs>();
		Document doc = Jsoup.parse(entity);
		Elements elements = doc.select("div.el");
		/*Elements title = elements.select("p.t1").select("span").select("a"); //标题
		Elements complany = elements.select("span.t2").select("a"); //公司
		Elements address = elements.select("span.t3");//地址
		Elements salary = elements.select("span.t4");//薪水
		Elements datas = elements.select("span.t5");//发布日期
		Elements SrcId = elements.select("p.t1").select("input.checkbox");//招聘信息对应的id*/


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
}
