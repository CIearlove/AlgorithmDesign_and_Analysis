package Chapter03_DynamicPlanning;

public class MatrixChain {

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
		
		matrixChain(p,m,s);
		traceback(0,5,s);
	}

	/**
	 * ���������˻�A_i*A_(i+1)...A_j���ΪA[i��j]
	 * @param p �������{p_0,p_1,...,p_n}�洢������p��
	 * @param m m[i][j]������A[i��j]������������˴���
	 * @param s ����Ӧ��m[i][j]�ĶϿ�λ��k��Ϊs[i][j]��
	 *          �ڼ��������ֵm[i][j]�󣬿ɵݹ����s[i][j]�������Ӧ�����Ž�
	 */
	public static void matrixChain(int[] p,int[][] m,int[][] s){
		
		int n = p.length-1;
		
		//��ʼ������m
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
				
		//��i=jʱ��A[i:j]=A_iΪ��һ����������㣬��ˣ�m[i][j]=0��i=1,2��..��n.
		for(int i=0; i<n; i++)
			m[i][i] = 0;
		
		for(int r=2; r<=n; r++)//r��ֵ������˵ľ���ĸ���
			for(int i=0; i<n-r+1; i++){//����������
				int j=i+r-1;//������յ���
				//k=iʱ,���ӵ�һ������Ͽ�������
				m[i][j] = m[i+1][j]+p[i]*p[i+1]*p[j+1];
				s[i][j] = i;
				for(int k=i+1;k<j;k++){
					//i+1<k<jʱ
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
