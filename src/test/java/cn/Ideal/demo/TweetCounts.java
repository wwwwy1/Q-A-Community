package cn.Ideal.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TweetCounts {
	Map<String,List<Integer>> ans;

	public TweetCounts() {
		ans = new HashMap<>();
	}

	public void recordTweet(String tweetName, int time) {
		if (ans.containsKey(tweetName)){
			ans.get(tweetName).add(time);
		}else {
			List<Integer> temp = new ArrayList<>();
			temp.add(time);
			ans.put(tweetName,temp);
		}
	}

	public List getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
		if (!ans.containsKey(tweetName)){
			return new ArrayList<>();
		}
		int time = 59;
		if (freq.equals("hour")) time = 3599;
		if (freq.equals("day")) time = 86399;

		List<Integer> rec = new ArrayList<>();
		List<Integer> count = ans.get(tweetName);
		for (int i = startTime; i <= endTime; i=i+time) {
			int flag=0;
			for (int j = 0; j < count.size(); j++) {
				int end = (i+time)>endTime? endTime:(i+time);
				if (count.get(j)>=i && count.get(j)<=end){
					flag++;
				}
			}
			rec.add(flag);
			i++;
		}
		return rec;
	}
}