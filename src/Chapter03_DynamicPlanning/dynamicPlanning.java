package Chapter03_DynamicPlanning;

public class dynamicPlanning {

	public static void main(String[] args) {
		
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
		//��i=jʱ��A[i:j]=A_iΪ��һ����������㣬��ˣ�m[i][j]=0��i=1,2��..��n.
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
