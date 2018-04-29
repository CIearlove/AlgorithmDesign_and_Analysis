package Chapter03_DynamicPlanning;

public class MemoizedMatrixChain {

	public static void main(String[] args) {
		
		//�������ά��
		int[][] A_0 =new int[30][35];
		int[][] A_1 =new int[35][15];
		int[][] A_2 =new int[15][5];
		int[][] A_3 =new int[5][10];
		int[][] A_4 =new int[10][20];
		int[][] A_5 =new int[20][25];
								
		//�������{p_0,p_1,...,p_n}�洢������p��
		int[] p = {30,35,15,5,10,20,25};
								
		//m[i][j]������A[i��j]������������˴���
		int[][] m = new int[6][6];
								
		//����Ӧ��m[i][j]�ĶϿ�λ��k��Ϊs[i][j]
		int[][] s = new int[6][6];
				
		//Ϊÿ��������m[i][j],i=1,2,..,n,j=i,..,n.����һ����¼�
		//��ʼ��ʱ���ü�¼�����һ������ֵ����ʾ����������δ��⡣
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				m[i][j] = -1;
			}
		}
				
		//��ʼ������s
		for(int i=0;i<s.length;i++){
			for(int j=0;j<s[0].length;j++){
				s[i][j] = -1;
			}
		}
		
		System.out.println("Optimal calculation times: "
		+memoizedmatrixChain(1,6,m,s,p));
		
		
	}

	/**
	 * 
	 * @param begin �������˵Ŀ�ʼ����A_i
	 * @param end �������˵Ľ�������A_j
	 * @param m m[i][j]������A[i��j]������������˴���
	 * @param s ��Ӧ��m[i][j]�ĶϿ�λ��k��Ϊs[i][j]
	 * @param p �������{p_0,p_1,...,p_n}�洢������p��
	 * @return �������˻����õ�������ֵ
	 */
	public static int memoizedmatrixChain(int begin, int end, int[][] m, int[][] s, int[] p){
		return lookupChain(begin-1,end-1,m,s,p);
	}

	/**
	 * 
	 * @param i �������˵Ŀ�ʼ����A_i
	 * @param j �������˵Ľ�������A_j
	 * @param m m[i][j]������A[i��j]������������˴���
	 * @param s ��Ӧ��m[i][j]�ĶϿ�λ��k��Ϊs[i][j]
	 * @param p �������{p_0,p_1,...,p_n}�洢������p��
	 * @return ��Ҫ��������ļ�����
	 */
	public static int lookupChain(int i, int j, int[][] m, int[][] s, int[] p) {
		
		if(m[i][j]>0)
			return m[i][j];
		if(i==j)
			return 0;
		
		int u = lookupChain(i,i,m,s,p)+lookupChain(i+1,j,m,s,p)+p[i]*p[i+1]*p[j+1];
		s[i][j] = i;
		for(int k=i+1;k<j;k++){
			int t = lookupChain(i,k,m,s,p)+lookupChain(k+1,j,m,s,p)+p[i]*p[k+1]*p[j+1];
			if(t < u){
				u = t;
				s[i][j] = k;
			}
		}
		m[i][j] = u;
		return u;
	}
	
	public static void traceback(int i,int j,int[][] s){
		System.out.println("Multiply A_"+i+",A_"+s[i][j]+" and A_"+(s[i][j]+1)+",A_"+j);
	}
}
