package Chapter03_DynamicPlanning;

public class LcsLength {

	public static void main(String[] args) {
		
		char[] x = {'A','B','C','B','D','A','B'};
		char[] y = {'B','D','C','A','B','A'};
		
		//b[i][j]记录c[i][j]的值是由哪一个子问题的解得到的
		int[][] b = new int[x.length+1][y.length+1];
		
		System.out.println("X和Y的最长公共子序列的长度为： "+lcsLength(x,y,b));
		System.out.print("且最长公共子序列为：");
		lcs(x.length,y.length,x,b);
	}

	/**
	 * 我并没有看懂最长公共子序列问题的最优子结构性质
	 * @param x 序列X={x_1,x_2,...,x_m}
	 * @param y 序列Y={y_1,y_2,...,y_n}
	 * @param b b[i][j]记录c[i][j]的值是由哪一个子问题的解得到的
	 * @return
	 */
	public static int lcsLength(char[] x,char[] y,int[][] b){
		
		int m = x.length;
		int n = y.length;
		
		//c[i][j]存储X_i和Y_j的最长公共子序列的长度 
		int[][] c = new int[m+1][n+1];
		
		//i=0或j=0时空序列是X_i和Y_j的最长公共子序列
		for(int i=0;i<m+1;i++){
			//j等于0
			c[i][0] = 0;
			b[i][0] = 1;//子问题1
		}
		for(int i=0;i<n+1;i++){
			//i等于0
			c[0][i] = 0;
			b[0][i] = 1;
		}
		
		for(int i=1;i<m+1;i++){
			for(int j=1;j<n+1;j++){
				//i,j>0;x_i=y_j时
				if(x[i-1] == y[j-1]){
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = 2;//子问题2
				}
				//i,j>0;x_i!=y_j时
				else if(c[i-1][j]>=c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = 3;//子问题3
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = 4;//子问题4
				}
			}
		}
			return c[m][n];
	}
	
	/**
	 * 
	 * @param i 序列X_i的元素个数
	 * @param j 序列Y_j的元素个数
	 * @param x 序列X_i与序列Y_j中元素个数较多的序列
	 * @param b b[i][j]记录c[i][j]的值是由哪一个子问题的解得到的
	 */
	public static void lcs(int i,int j,char[] x,int[][] b){
		
		//子问题1
		if(b[i][j] == 1)
			return;
		//子问题2
		if(b[i][j] == 2){
			lcs(i-1,j-1,x,b);
			System.out.print(x[i]);
		}
		//子问题3
		else if(b[i][j] == 3){
			lcs(i-1,j,x,b);
		}
		//子问题4
		else
			lcs(i,j-1,x,b);
	}
}
