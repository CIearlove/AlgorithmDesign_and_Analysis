package Chapter03_DynamicPlanning;

public class LcsLength {

	public static void main(String[] args) {
		
		char[] x = {'A','B','C','B','D','A','B'};
		char[] y = {'B','D','C','A','B','A'};
		
		//b[i][j]��¼c[i][j]��ֵ������һ��������Ľ�õ���
		int[][] b = new int[x.length+1][y.length+1];
		
		System.out.println("X��Y������������еĳ���Ϊ�� "+lcsLength(x,y,b));
		System.out.print("�������������Ϊ��");
		lcs(x.length,y.length,x,b);
	}

	/**
	 * �Ҳ�û�п������������������������ӽṹ����
	 * @param x ����X={x_1,x_2,...,x_m}
	 * @param y ����Y={y_1,y_2,...,y_n}
	 * @param b b[i][j]��¼c[i][j]��ֵ������һ��������Ľ�õ���
	 * @return
	 */
	public static int lcsLength(char[] x,char[] y,int[][] b){
		
		int m = x.length;
		int n = y.length;
		
		//c[i][j]�洢X_i��Y_j������������еĳ��� 
		int[][] c = new int[m+1][n+1];
		
		//i=0��j=0ʱ��������X_i��Y_j�������������
		for(int i=0;i<m+1;i++){
			//j����0
			c[i][0] = 0;
			b[i][0] = 1;//������1
		}
		for(int i=0;i<n+1;i++){
			//i����0
			c[0][i] = 0;
			b[0][i] = 1;
		}
		
		for(int i=1;i<m+1;i++){
			for(int j=1;j<n+1;j++){
				//i,j>0;x_i=y_jʱ
				if(x[i-1] == y[j-1]){
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = 2;//������2
				}
				//i,j>0;x_i!=y_jʱ
				else if(c[i-1][j]>=c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = 3;//������3
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = 4;//������4
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
	public static void lcs(int i,int j,char[] x,int[][] b){
		
		//������1
		if(b[i][j] == 1)
			return;
		//������2
		if(b[i][j] == 2){
			lcs(i-1,j-1,x,b);
			System.out.print(x[i]);
		}
		//������3
		else if(b[i][j] == 3){
			lcs(i-1,j,x,b);
		}
		//������4
		else
			lcs(i,j-1,x,b);
	}
}
