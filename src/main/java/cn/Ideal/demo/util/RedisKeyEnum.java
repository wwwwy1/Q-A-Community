package cn.Ideal.demo.util;

import lombok.Data;

@Data
public class RedisKeyEnum {
	public static final String FORUM_KEY = "forum_key";
	public static final String REPLY_KEY = "reply_key";
	public static final String THUMB_UP_FORUM = "thumb_up_forum";
	public static final String THUMB_UP_REPLY = "thumb_up_reply";
	public static final String THUMB_DOWN_FORUM = "thumb_down_forum";
	public static final String THUMB_DOWN_REPLY = "thumb_down_reply";
	public static final String TASK_LIST_KEY = "task_list_key";

	public static final String USER_TOP_LIST = "user_top_list";


	// 每日统计用户获赞量
	public static final String STATISTIC_USER = "statistic_user";
	// 每日统计标签使用量
	public static final String STATISTIC_TAGS = "statistic_tags";


}
