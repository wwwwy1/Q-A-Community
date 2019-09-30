package cn.Ideal.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SnapshotArray {
	List<List<Integer>> list=new ArrayList<>();
	int snap_id=0;
	int snap_Length=0;
	public SnapshotArray(int length) {
		List<Integer> ff=new ArrayList<>(length);
		snap_Length=length;
		for(int i=0;i<snap_Length;i++){
			ff.add(0);
		}
		list.add(ff);
	}

	public void set(int index, int val) {
		if (list.size()<=snap_id)return;
		if(index>=list.get(snap_id).size())return;
		list.get(snap_id).set(index,val);
	}

	public int snap() {
		snap_id++;
		List<Integer> ff=new ArrayList<>();
		for(int i=0;i<snap_Length;i++){
			ff.add(list.get(snap_id-1).get(i));
		}
		list.add(ff);
		return snap_id-1;
	}

	public int get(int index, int snap_id) {
		if (snap_id>=this.snap_id)return 0;
		return list.get(snap_id).get(index);
	}
}
