package cn.Ideal.demo.util;

import cn.Ideal.demo.entity.TaskList;
import com.alibaba.fastjson.JSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtil {
	private static String regEx_html = "<[^>]+>";
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

	public static List<TaskList> jsonToTaskList(String json,String userId){
		//((Map<String,String>)
		List list = StringUtil.stringToObj(json, List.class);
		List<TaskList> ans = new ArrayList<>();
		for (Object o : list) {
			TaskList taskList = new TaskList();
			taskList.setTaskDate(LocalDateTime.parse(((Map<String,String>)o).get("taskDate"),df));
			taskList.setUserId(userId);
			taskList.setContent(((Map<String,String>)o).get("content"));
			taskList.setContent(((Map<String,String>)o).get("rank"));
		}
		return ans;
	}



}
