package Chapter03_DynamicPlanning;

/**
 * 流水作业调度问题Johnson算法：
 * （1）令N1 = {i|A_i<B_i},N2 = {i|A_i>=B_i};
 * （2）将N1中作业依A_i的非减序排序；将N2中作业依B_i的非增序排列;
 * （3）N1中作业接N2中作业构成满足Johnson法则的最优调度。
 * 
 * 如果作业i和j满足min{B_i，A_j}>=min{B_j，A_i}，则称作业i和j满足Johnson不等式。
 * 任意两个满足Johnson法则的调度具有相同的加工时间。
 * 从而所有满足Johnson法则的调度均为最优调度。
 * 至此，将流水作业调度问题转化为求满足Johnson法则的调度问题。
 * @author DuoZhu
 *
 */
public class FlowScheduling {

	public static void main(String[] args) {
		
		  //N
		  int[] a = {5,6,7,2,1,3,9,10};
		  int[] b = {2,3,4,5,6,7,8,9};
		  int[] c = new int[a.length];
		  
		  int Total_Time_Consuming = flowShop(a,b,c);
		  System.out.println("加工完N中作业所需的最短时间"+Total_Time_Consuming);
		  System.out.println("流水作业调度序列：");
		  for(int i=0;i<c.length;i++){
			  System.out.print(c[i]+1+" ");
		  }
	}

	/**
	 * 
	 * @param a M1加工作业i所需的时间为a_i
	 * @param b M2加工作业i所需的时间为b_i
	 * @param c 最优调度序列
	 * @return k 消耗总时间的最大值
	 */
	public static int flowShop(int[] a,int[] b,int[] c){
		
		int n = a.length;
		Element[] d = new Element[n];
		
		for(int i=0;i<n;i++){
			//取a[i]与b[i]的较小值
			int key = a[i]>b[i] ? b[i]:a[i];
			boolean job = a[i]<=b[i];
			d[i] = new Element(key,i,job);
		}
		
		//按照key升序排序
		MergeSort.mergeSort(d);
		
		int j=0,k=n-1;
		for(int i=0;i<n;i++){
			if(d[i].isJob())
				c[j++] = d[i].getIndex();
			else
				c[k--] = d[i].getIndex();
		}
		
		j = a[c[0]];
		k = j+b[c[0]];
		
		for(int i=1;i<n;i++){
			j+=a[c[i]];
			k = j<k ? k+b[c[i]]:j+b[c[i]];//消耗的总时间
		}
		return k;
	}
}
