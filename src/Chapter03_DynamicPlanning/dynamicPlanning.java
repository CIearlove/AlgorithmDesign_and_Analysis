package Chapter03_DynamicPlanning;

public class dynamicPlanning {

	public static void main(String[] args) {
		
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
		//当i=j时，A[i:j]=A_i为单一矩阵，无需计算，因此，m[i][j]=0，i=1,2，..，n.
		for(int i=1; i<=n; i++)
			m[i][i] = 0;
		
		for(int r=2; r<=n; r++)
			for(int i=1; i<=n-r+1; i++){
				int j=i+r-1;
				//
				m[i][j] = m[i+1][j]+p[i-1]*p[i]*p[j];
				s[i][j] = i;
				for(int k=i+1;k<j;k++){
					int t=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
					if(t<m[i][j]){
						m[i][j] = t;
						s[i][j] = k;
					}
				}
			}
	}
}
