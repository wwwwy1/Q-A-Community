package cn.Ideal.demo.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtil {
	private static String regEx_html = "<[^>]+>";
	private static Pattern compile = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	//过滤html标签
	public static String ignoreHtml(String html){
		if (isNullOrSpace(html))return "";
		Matcher matcher = compile.matcher(html);
		return matcher.replaceAll("");
	}
	// 根据ids获取list
	public static List<String> getIdList(String ids){
		String[] split = ids.split(",");
		return Arrays.stream(split).collect(Collectors.toList());
	}
	public static String removeSpace(String keyWords){
		String[] split = keyWords.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			if (!"".equals(split) && !" ".equals(split)){
				sb.append(split[i]);
			}
		}
		return sb.toString();
	}
	public static Boolean isNullOrSpace(String keyWords){
		if (keyWords==null || keyWords.equals("") || keyWords.equals("null")) return true;
		else return false;
	}
}
