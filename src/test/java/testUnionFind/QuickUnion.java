package testUnionFind;

public class QuickUnion {
	int[] id;
	int count;
	public void init(int n){
		id = new int[n];
		count = n;
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	public int find(int q){
		while (q!=id[q]){
			q=id[q];
		}
		return q;
	}
	public void union(int q,int p){
		int i = find(q);
		int j = find(p);
		if (i==j)return;
		id[i]=j;
		count--;
	}
	public int makeConnected(int n, int[][] connections) {
		int line = connections.length;
		if (n-1>line) return -1;
		init(n);
		for (int i = 0; i < connections.length; i++) {
			union(connections[i][0],connections[i][1]);
		}
		return count-1;
	}
}
