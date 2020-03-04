package cn.Ideal.demo.util;

import com.alibaba.fastjson.JSON;

import java.util.*;
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
	// 判空
	public static Boolean isNullOrSpace(String keyWords){
		if (keyWords==null || keyWords.equals("") || keyWords.equals("null")) return true;
		else return false;
	}
	// StringToSet
	public static HashSet<Integer> stringToSet(String val){
		if (isNullOrSpace(val)) return null;
		HashSet<Integer> ans = new HashSet<>();
		String[] split = val.split(",");
		for (String s : split) {
			ans.add(Integer.valueOf(s.trim()));
		}
		return ans;
	}
	// SetToString
	public static String setToString(Set val){
		String string = val.toString();
		return string.substring(1,string.length()-1);
	}
	public static String objectToString(Object object){
		return JSON.toJSONString(object);
	}
	public static <T> T stringToObj(String jsonStr,Class<T> clazz){
		return JSON.parseObject(jsonStr,clazz);
	}



}
