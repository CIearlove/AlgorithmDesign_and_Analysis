package Chapter04_GreedyAlgorithm;

public class GreedySelector {

	public static void main(String[] args) {
		
		//开始时刻
		int s[] = {1,3,0,5,3,5,6,8,8,2,12};
		//结束时刻
		int f[] = {4,5,6,7,8,9,10,11,12,13,14};
		//活动i在集合A中，当且仅当A[i]的值为true
		boolean a[] = new boolean[11]; 
		
		greedySelector(s,f,a);
		System.out.print("贪心算法所选择的活动:");
		for(int i=0;i<a.length;i++){
			if(a[i]){
				System.out.print(" "+i);
			}
		}
	}
	/**
	 * 
	 * @param s 各活动的起始时间存储于数组s
	 * @param f 各活动的结束时间存储于数组f，且按结束时间的非减序f_1<=f_2<=...<=f_n排列
	 * @param a 集合A存储所选择的活动。活动i在集合A中，当且仅当A[i]的值为true。
	 * @return  所选择的活动个数
	 */
	public static int greedySelector(int[] s,int[] f,boolean[] a){
		
		int n = s.length-1;
		a[0] = true;
		int j = 0;//记录最近一次加入A的活动，第一次加入的是活动1
		int count = 1;
		for(int i=1;i<=n;i++){
			/*
			 * 由于f_j总是当前集合A中所有活动的最大结束时间，
			 * 故活动i与当前集合A中所有活动相容的充分必要条件是其开始时间s_i不小于最近加入集合A的活动j的结束时间f_j，
			 * 即s_i>=f_j。
			 */
			if(s[i] >= f[j]){
				a[i] = true;
				//若活动i与A相容，则i成为最近加入集合A中的活动，并取代活动j的位置。
				j=i;
				count++;
			}
			else
				a[i] = false;
		}
		return count;
		
	}
}
