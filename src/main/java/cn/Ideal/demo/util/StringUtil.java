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
			Integer rank = ((Map<String, Integer>) o).get("rank");
			taskList.setRank(rank);
			ans.add(taskList);
		}
		return ans;
	}
	// 获取中括号中的内容
	public static List<String> extractMessageByBrackets(String msg){
		List<String> list=new ArrayList<String>();
		Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
		Matcher m = p.matcher(msg);
		while(m.find()){
			list.add(m.group().substring(1, m.group().length()-1));
		}
		return list.size()==0?null:list;
	}
	// 获取中引号中的内容
	public static List<String> extractMessageByQuotationMarks(String msg){
		Pattern p1=Pattern.compile("\"(.*?)\"");
		Matcher m = p1.matcher(msg);
		ArrayList<String> list = new ArrayList<>();
		while (m.find()) {
			list.add(m.group().trim().replace("\"",""));
		}
		return list.size()==0?null:list;
	}
	// 获取时间
	public static List<String> extractMessageByTime(String msg){
		if (msg.indexOf("created:")==-1)
			return null;
		try {
			int start = msg.indexOf("created:")+8;
			String s = msg.substring(start,start+10);
			String e = msg.substring(start+12,start+22);
			ArrayList<String> list = new ArrayList<>();
			list.add(s);
			list.add(e);
			return list;
		}catch (Exception e){
			return null;
		}
	}

}
