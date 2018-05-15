package Chapter03_DynamicPlanning;

public class LcsLength {

	public static void main(String[] args) {
		
		char[] x = {' ','A','B','C','B','D','A','B'};
		char[] y = {' ','B','D','C','A','B','A'};
		
		//b[i][j]记录c[i][j]的值是由哪一个子问题的解得到的
		int[][] b = new int[x.length][y.length];
		
		System.out.println("X和Y的最长公共子序列的长度为： "+lcsLength(x,y,b));
		System.out.print("带入x序列且最长公共子序列为：");
		lcs2(x.length-1,y.length-1,x,b);
		System.out.println();
		System.out.print("带入y序列且最长公共子序列为：");
		lcs1(x.length-1,y.length-1,y,b);
	}

	/**
	 * 我并没有看懂最长公共子序列问题的最优子结构性质
	 * @param x 序列X={x_1,x_2,...,x_m}
	 * @param y 序列Y={y_1,y_2,...,y_n}
	 * @param b b[i][j]记录c[i][j]的值是由哪一个子问题的解得到的
	 * @return
	 */
	public static int lcsLength(char[] x,char[] y,int[][] b){
		
		int m = x.length-1;
		int n = y.length-1;
		
		//c[i][j]存储X_i和Y_j的最长公共子序列的长度 
		int[][] c = new int[m+1][n+1];
		
		//i=0或j=0时空序列是X_i和Y_j的最长公共子序列
		for(int i=1;i<=m;i++){
			//j等于0
			c[i][0] = 0;
		}
		for(int i=1;i<=n;i++){
			//i等于0
			c[0][i] = 0;
		}
		
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				//i,j>0;x_i=y_j时
				if(x[i] == y[j]){
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = 1;//子问题1
				}
				//i,j>0;x_i!=y_j时
				else if(c[i-1][j]>=c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = 2;//子问题2
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = 3;//子问题3
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
	public static void lcs1(int i,int j,char[] y,int[][] b){
		
		if(i==0 ||j==0)
			return;
		if(b[i][j] == 1){
			lcs1(i-1,j-1,y,b);
			System.out.print(y[j]);
		}
		else if(b[i][j] == 2){
			lcs1(i-1,j,y,b);
		}
		else
			lcs1(i,j-1,y,b);
	}
	
    public static void lcs2(int i,int j,char[] x,int[][] b){
		
		if(i==0 ||j==0)
			return;
		if(b[i][j] == 1){
			lcs2(i-1,j-1,x,b);
			System.out.print(x[i]);
		}
		else if(b[i][j] == 2){
			lcs2(i-1,j,x,b);
		}
		else
			lcs2(i,j-1,x,b);
	}
}
