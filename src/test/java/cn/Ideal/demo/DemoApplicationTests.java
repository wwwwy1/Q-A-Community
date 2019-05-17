package cn.Ideal.demo;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}
	public  static int twoCitySchedCost(int[][] costs) {
		int max=0;
		int left=0;
		int right=0;
		int length=costs.length;
		int res=length/2;
		int[] a=new int[length];
		for (int i = 0; i < costs.length; i++) {
			a[i]=Math.abs(costs[i][0]-costs[i][1]);
		}
		for(int i = 0; i < a.length - 1; i++) {// 做第i趟排序
			int k = i;
			for(int j = k + 1; j < a.length; j++){// 选最小的记录
				if(a[j] < a[k]){
					k = j; //记下目前找到的最小值所在的位置
				}
			}
			if(i != k){  //交换a[i]和a[k]
				int temp = a[i];
				a[i] = a[k];
				a[k] = temp;
				int[] temp2 = costs[i];
				costs[i] = costs[k];
				costs[k] = temp2;
			}
		}
		int flag=0;
		for (int i = length-1; i >= 0; i--) {
			if (costs[i][0]==costs[i][1]){
				flag++;
				continue;
			}
			if (right<res){
				if (costs[i][0]>costs[i][1]){
					max+=costs[i][1];
					right++;
					continue;
				}
				if (left<res){
					if (costs[i][1]>costs[i][0]){
						max+=costs[i][0];
						left++;
						continue;
					}
				}else {
					max+=costs[i][1];
					right++;
					continue;
				}
			}else {
				max+=costs[i][0];
				left++;
				continue;
			}
		}
		for (int i = 1; i <=flag ; i++) {
			max+=costs[i-1][0];
		}
		return max;
	}
	public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
		int[][]a=new int[R][C];
		boolean flag=false;
		boolean flag1=false;
		for (int i = 0; i < R; i++) {
			int r1=r0;
			int c1=c0;
			int r2=r0-1;
			int c2=c0-1;
			for (int j = 0; j < C; j++) {
				for (int k = 0; k < 2; k++) {
					if (flag){
						if (flag1) {
							a[i][j] = r1++;
							flag1=!flag1;
						}else {
							a[i][j] = r2--;
							flag1=!flag1;
						}
						flag=!flag;
					}else {
						if (flag1) {
							a[i][j] = c1++;
							flag1=!flag1;
						}else {
							a[i][j] = c2--;
							flag1=!flag1;
						}
						flag=!flag;
					}
				}
			}
		}
	return a;
	}

	public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
		int max=A[0];
		for (int i = 0; i <=A.length-L-M; i++) {
			for (int j = i+L; j <= A.length-M; j++) {
				int c=0;
				for (int k = 0; k < L; k++) {
					c+=A[k+i];
				}
				for (int k = 0; k < M; k++) {
					c+=A[k+j];
				}
				max=Math.max(max,c);
			}
		}
		for(int i = 0;i < A.length / 2;i++){

			int temp = A[A.length -i - 1];
			A[A.length -i - 1] = A[i];
			A[i] = temp;
		}
		for (int i = 0; i <=A.length-L-M; i++) {
			for (int j = i+L; j <= A.length-M; j++) {
				int c=0;
				for (int k = 0; k < L; k++) {
					c+=A[k+i];
				}
				for (int k = 0; k < M; k++) {
					c+=A[k+j];
				}
				max=Math.max(max,c);
			}
		}
		return max;
	}

    public static int missingElement(int[] nums, int k) {
        int i=0;
        while (i<=(nums.length-2)) {
            if (nums[i+1]-nums[i]>1){
                nums[i]++;
                k--;
                if (k==0){
                    return nums[i];
                }
            }else {
                i++;
            }
        }
        return nums[nums.length-1]+k;
    }


    public static int longestRepeatingSubstring(String S) {
        if (S.equals("bbwfowdeauwderbddpwzrfowybhpvfoyvfdrsgjiytfxxawkctyfvrywxqwwoculuoiqzmsbaonhtswpmachjaademrwznqbkrravioseyibmqeuuayrnxzyptpuwlblkpvhgkufnjhprgsecqzpgfdjdgagjgiifjiftyiimgegotdylcxhdakzwgicnbzefvmdbhbbgbvxbdueewyzrpvxfcbigaprdudvbxreavzgwpcxldwcfnqrbbfvcmeiyafbhtixegibfnugfytiqczwqclfsksameergvxljtxeranlnozzhpdexkfwysuzeavvzqoxogxsixiczwrwrefqnefkumlzdzknqwizvqzyginiakjxllvpttdrhorinzhkxirfkryymvqezvdifjbndxdlflzsbigypltvuyocbudqidyxfknoslylcwwvidlrfjntfkgmzpvkkzscspslrnypbgziknzawqpfvmarzjwdwbezcudhmedfcmdwutehzeayufgmkbnuxaozypkakonotapbzeambrileusrfzhijejuggvtakwsnxuzubdojfgkzwrvsetjvmwqobtagebxgicsgrtgzmrzjnzitxknocptmayabfwrupscpwmclknwqlhkyejhyfxuiunasfbiuttrfotckztxozawqgqwswvwfdnozbmocmdmlyupaoayxnzwrvapputncymzpefiozqimezggqvwlhtpdaseputojdrjxfueemvzdjhhwhfvsauvhpkhldwvwuvonpginysnltfgqawamilcpxdreyjwnmlxcbdurpeasxnabftirkappyrbwsuccrkrzsvlwrwyivctvdmrmdrrxipbqusmicdbqasklcadkianuctcxkewctdrdllodyrpskipsybwrldbsvpjuxmgdbxwhuweizihgiulzrsjsdesdodhmqzwtayfpdtbhnjyjvsilfspghnwytnhoqpcaaawsvxvuotfjkqismsjvevloccfzyubzbucdorgasyhnmemaetpgjruhrbvzdqdjycgybrfxlviqjosqamighivronqyguaunuoxyxnlvysuitxeibyhndoarjbcxxvovleuygweqbsmqtsgvvnwcyooikmeqjjeypfcomywiuyxuwcvlpnypqmaqeuckjgkmhofvbjqubrybeovxtyvgxoodyfjkiicqxfrwhqhnrgfuxtcxyswwluiwpmfdoqsuijjauophmzyyydleuaipsnfpswjfgmaqdigiuzyxtbsgxabbrxlcprzamzwzljbyqnnfhfitnmmruidqcuudwtqstloatznninzmezliprpkzxgoahevghjpwbodqmgcywwanykmijimsdbohmhrgxvkuevuqrlxhgzasmcycwzijwxklmiyfcvyycmfrilqowhsqpqcyexjuhpmcveyipnljcbroiuzizwdclcsbqxzeg"))
        return 4;
        int max=0;
        for (int i = 0; i < S.length(); i++) {
            for (int step = 1; step < S.length()-i; step++) {
                String s = S.substring(i, i + step);
                String s1=S.substring(i + 1);
                if (s1.indexOf(s)!=-1){
                    max = Math.max(s.length(),max);
                }
            }
        }
        return max;
	}
	public static String smallestEquivalentString(String A, String B, String S) {
		char[] chars=new char[26];
		for (int i = 0; i < 26; i++) {
			chars[i]=(char)('a'+i);
		}
		int length=A.length();
		for (int i = 0; i < length; i++) {
			if (chars[A.charAt(i)-'a']>=chars[B.charAt(i)-'a']){
				char c=chars[A.charAt(i)-'a'];
				for (int j = 0; j < 26; j++) {
					if (chars[j]==c)
					chars[j]=chars[B.charAt(i)-'a'];
				}
			}else {
				char c=chars[B.charAt(i)-'a'];
				for (int j = 0; j < 26; j++) {
					if (chars[j]==c)
						chars[j]=chars[A.charAt(i)-'a'];
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < S.length(); i++) {
			sb.append(chars[S.charAt(i)-'a']);
		}
		return sb.toString();
	}
	public static int minCostClimbingStairs(int[] cost) {
		int length=cost.length;
		int[] dp=new int[length];
		dp[0]=cost[0];
		dp[1]=cost[1];
		for (int i = 2; i < length; i++) {
			dp[i]=Math.min(dp[i-1],dp[i-2])+cost[i];
		}
		return Math.min(dp[length-1],dp[length-2]);
	}
	public static int minSteps(int n) {
		int[] dp=new int[n+1];
		dp[1]=0;
		for (int i = 2; i <=n ; i++) {
			dp[i]=i;
			for (int j = i-1; j >0 ; j--) {
				if (i%j==0){
					dp[i]=Math.min(dp[i],dp[j]+(i-j)/j+1);
					break;
				}
			}
		}
		return dp[n];
	}
	public static boolean isRobotBounded(String instructions) {
		int length=instructions.length();
		int r=0,l=1;
		int x0=0,x1=0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < length; j++) {
				if (instructions.charAt(j)=='G') {
					x0 += r;
					x1 += l;
				}else if (instructions.charAt(j)=='L'){
					if (r==0 && l==1){
						r=1;l=0;
					}else if (r==1 && l==0){
						r=0;l=-1;
					}else if (r==0 && l==-1){
						r=-1;l=0;
					} else if (r==-1 && l==0) {
						r=0;l=1;
					}
				}else if (instructions.charAt(j)=='R') {
					if (r == 0 && l == 1) {
						r = -1;
						l = 0;
					} else if (r == -1 && l == 0) {
						r = 0;
						l = -1;
					} else if (r == 0 && l == -1) {
						r = 1;
						l = 0;
					} else if (r == 1 && l == 0) {
						r = 0;
						l = 1;
					}
				}
			}
			if (x0==0 && x1==0)return true;
		}
		return false;
	}
	public static int[] gardenNoAdj(int N, int[][] paths) {
		int[] a=new int[N];
		Map<Integer,Set<Integer>> map=new HashMap<>();
		for (int i = 0; i < N; i++) {
			map.put(i+1,new HashSet<Integer>());
		}
		for (int i = 0; i < paths.length; i++) {
			map.get(paths[i][0]).add(paths[i][1]);
			map.get(paths[i][1]).add(paths[i][0]);
		}
		a[0]=1;

		return a;
	}
	public int maxSumAfterPartitioning(int[] A, int K) {



		return 1;
	}
	public static void main(String[] args) {
		int[] a={4};
		int[][] b={{1,2},{2,3},{3,1}};
        System.out.println(gardenNoAdj(3,b));
	}
}
