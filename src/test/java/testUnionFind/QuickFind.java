package testUnionFind;

public class QuickFind {
	int[] id;
	int count;
	public void init(int n){
		id = new int[n];
		count=n;
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	public void union(int p,int q){
		int i = find(p);
		int j = find(q);
		if (i==j)return;
		for (int k = 0; k < id.length; k++) {
			if (id[k]==i)id[k]=j;
		}
		count--;
	}
	public int find(int p){
		return id[p];
	}
	public int makeConnected(int n, int[][] connections) {
		init(n);
		for (int i = 0; i < connections.length; i++) {
			union(connections[i][0],connections[i][1]);
		}
		return count-1;
	}
}
