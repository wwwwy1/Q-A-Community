package cn.Ideal.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndergroundSystem {
	// 站点之间的时间表
	Map<String,Map<String,List<Integer>>> time;
	// checkin
	Map<Integer,Map<String,Integer>> cki;
	// checkin
	Map<Integer,Map<String,Integer>> cko;
	public UndergroundSystem() {
		time = new HashMap<>();
		cki = new HashMap<>();
		cko = new HashMap<>();
	}
	public void checkIn(int id, String stationName, int t) {
		Map<String,Integer> temp = new HashMap<>();
		temp.put(stationName,t);
		cki.put(id,temp);
	}

	public void checkOut(int id, String stationName, int t) {
		Map<String, Integer> stringIntegerMap = cki.get(id);
		for (String s : stringIntegerMap.keySet()) {
			int ti = stringIntegerMap.get(s);
			Map<String,List<Integer>> temp = new HashMap<>();
			if (time.containsKey(s)){
				Map<String, List<Integer>> sL = time.get(s);
				if (sL.containsKey(stationName)){
					List<Integer> list = sL.get(stationName);
					list.add(t-ti);
				}else {
					List<Integer> ss = new ArrayList<>();
					ss.add(t-ti);
					sL.put(stationName,ss);
				}
			}else {
				List<Integer> ss = new ArrayList<>();
				ss.add(t-ti);
				temp.put(stationName,ss);
				time.put(s,temp);
			}
		}
	}

	public double getAverageTime(String startStation, String endStation) {
		Map<String, List<Integer>> sL = time.get(startStation);
		List<Integer> list = sL.get(endStation);
		double ans = 0 ;
		for (int i = 0; i < list.size(); i++) {
			ans+=list.get(i);
		}
		return ans/list.size();
	}
}
