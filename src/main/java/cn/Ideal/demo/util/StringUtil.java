package cn.Ideal.demo.util;

public class StringUtil {
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
