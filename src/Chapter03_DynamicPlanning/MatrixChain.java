package Chapter03_DynamicPlanning;

public class MatrixChain {

	public static void main(String[] args) {
		//各矩阵的维数
		int[][] A_0 =new int[30][35];
		int[][] A_1 =new int[35][15];
		int[][] A_2 =new int[15][5];
		int[][] A_3 =new int[5][10];
		int[][] A_4 =new int[10][20];
		int[][] A_5 =new int[20][25];
		
		//输入参数{p_0,p_1,...,p_n}存储于数组p中
		int[] p = {30,35,15,5,10,20,25};
		
		//m[i][j]即计算A[i：j]所需的最少数乘次数
		int[][] m = new int[6][6];
		
		//将对应于m[i][j]的断开位置k记为s[i][j]
		int[][] s = new int[6][6];
		
		matrixChain(p,m,s);
		traceback(0,5,s);
	}

	/**
	 * 将矩阵连乘积A_i*A_(i+1)...A_j简记为A[i：j]
	 * @param p 输入参数{p_0,p_1,...,p_n}存储于数组p中
	 * @param m m[i][j]即计算A[i：j]所需的最少数乘次数
	 * @param s 将对应于m[i][j]的断开位置k记为s[i][j]，
	 *          在计算出最优值m[i][j]后，可递归地由s[i][j]构造出相应的最优解
	 */
	public static void matrixChain(int[] p,int[][] m,int[][] s){
		
		int n = p.length-1;
		
		//初始化数组m
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				m[i][j] = -1;
			}
		}
		
		//初始化数组s
				for(int i=0;i<s.length;i++){
					for(int j=0;j<s[0].length;j++){
						s[i][j] = -1;
					}
				}
				
		//当i=j时，A[i:j]=A_i为单一矩阵，无需计算，因此，m[i][j]=0，i=1,2，..，n.
		for(int i=0; i<n; i++)
			m[i][i] = 0;
		
		for(int r=2; r<=n; r++)//r的值代表相乘的矩阵的个数
			for(int i=0; i<n-r+1; i++){//矩阵的起点编号
				int j=i+r-1;//矩阵的终点编号
				//k=i时,即从第一个矩阵断开矩阵链
				m[i][j] = m[i+1][j]+p[i]*p[i+1]*p[j+1];
				s[i][j] = i;
				for(int k=i+1;k<j;k++){
					//i+1<k<j时
					int t=m[i][k]+m[k+1][j]+p[i]*p[k+1]*p[j+1];
					if(t<m[i][j]){
						m[i][j] = t;
						s[i][j] = k;
					}
				}
			}
	}
	
	public static void traceback(int i,int j,int[][] s){
		System.out.println("Multiply A_"+i+",A_"+s[i][j]+" and A_"+(s[i][j]+1)+",A_"+j);
	}
}
