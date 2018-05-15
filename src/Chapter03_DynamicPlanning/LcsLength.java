package Chapter03_DynamicPlanning;

public class LcsLength {

	public static void main(String[] args) {
		
		char[] x = {' ','A','B','C','B','D','A','B'};
		char[] y = {' ','B','D','C','A','B','A'};
		
		//b[i][j]��¼c[i][j]��ֵ������һ��������Ľ�õ���
		int[][] b = new int[x.length][y.length];
		
		System.out.println("X��Y������������еĳ���Ϊ�� "+lcsLength(x,y,b));
		System.out.print("����x�����������������Ϊ��");
		lcs2(x.length-1,y.length-1,x,b);
		System.out.println();
		System.out.print("����y�����������������Ϊ��");
		lcs1(x.length-1,y.length-1,y,b);
	}

	/**
	 * �Ҳ�û�п������������������������ӽṹ����
	 * @param x ����X={x_1,x_2,...,x_m}
	 * @param y ����Y={y_1,y_2,...,y_n}
	 * @param b b[i][j]��¼c[i][j]��ֵ������һ��������Ľ�õ���
	 * @return
	 */
	public static int lcsLength(char[] x,char[] y,int[][] b){
		
		int m = x.length-1;
		int n = y.length-1;
		
		//c[i][j]�洢X_i��Y_j������������еĳ��� 
		int[][] c = new int[m+1][n+1];
		
		//i=0��j=0ʱ��������X_i��Y_j�������������
		for(int i=1;i<=m;i++){
			//j����0
			c[i][0] = 0;
		}
		for(int i=1;i<=n;i++){
			//i����0
			c[0][i] = 0;
		}
		
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				//i,j>0;x_i=y_jʱ
				if(x[i] == y[j]){
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = 1;//������1
				}
				//i,j>0;x_i!=y_jʱ
				else if(c[i-1][j]>=c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = 2;//������2
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = 3;//������3
				}
			}
		}
			return c[m][n];
	}
	
	/**
	 * 
	 * @param i ����X_i��Ԫ�ظ���
	 * @param j ����Y_j��Ԫ�ظ���
	 * @param x ����X_i������Y_j��Ԫ�ظ����϶������
	 * @param b b[i][j]��¼c[i][j]��ֵ������һ��������Ľ�õ���
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
