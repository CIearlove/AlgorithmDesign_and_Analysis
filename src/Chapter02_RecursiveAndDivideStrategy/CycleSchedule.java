package Chapter02_RecursiveAndDivideStrategy;
/**
 * 设有n=2^k个运动员要进行网球循环赛。
 * （1）每个选手必须与其他n-1个选手各赛一次；
 * （2）每个选手一天只能赛一次；
 * （3）循环赛一共进行n-1天。
 * @author DuoZhu
 *
 */
public class CycleSchedule {

	public static void main(String[] args) {
		
		int k = 2;
		int n = 1;
		
		//参赛人数
		for(int i=1;i<=k;i++){
			n*=2;
		}
		
		//比赛日程表
		int t[][] = new int[n+1][n+1];
		table(k,t);
		
		//打印比赛表
		for(int i=0;i<n+1;i++){
			for(int j=0;j<n+1;j++){
				System.out.print(t[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 可以将比赛日程表设计成有n行和n-1列的表。
	 * 表中第i行和第j列处填入第i个选手在第j天所遇到的选手。
	 * @param k k=log2 N，N为参赛运动员个数，最少为2个
	 * @param a 比赛日程表
	 */
	public static void table(int k,int[][]a){
		
		int n=1;
		
		for(int i=1;i<=k;i++)
			n*=2;
		for(int i=1;i<=n;i++)
			a[1][i] = i;
		
		int m=1;
		for(int s=1;s<=k;s++){
			n/=2;
			for(int t=1;t<=n;t++)
				for(int i=m+1;i<=2*m;i++)
					for(int j=m+1;j<=2*m;j++)
					{
						a[i][j+(t-1)*m*2] = a[i-m][j+(t-1)*m*2-m];
						a[i][j+(t-1)*m*2-m] = a[i-m][j+(t-1)*m*2];
					}
			m *= 2;
		}
	}
}
