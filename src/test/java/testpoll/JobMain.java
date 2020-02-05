package testpoll;

import cn.hutool.http.HttpUtil;
import java.util.ArrayList;
import java.util.List;

public class JobMain {

	public static void main(String[] args) {
	//https://search.51job.com/list/080200%252C020000%252C070200%252C070300%252C080300,000000,0000,00,9,99,java,2,1.html
	//https://search.51job.com/list/080200%252C020000%252C070200%252C070300%252C080300,000000,0000,00,9,99,java,2,1.html?workyear=01
		int pagesize = 20;
		List<Jobs> ans = new ArrayList<>();
		for (int i = 1; i <= pagesize; i++) {
			String url = "https://search.51job.com/list/080200%252C020000%252C070200%252C070300%252C080300,000000,0000,00,9,99,java,2," + i + ".html";
			String s = HttpUtil.get(url);
			ans.addAll(JobParse.getData(s));
		}
		System.out.println(ans.size());
		ans.forEach(jobs -> System.out.println(jobs));
	}
}