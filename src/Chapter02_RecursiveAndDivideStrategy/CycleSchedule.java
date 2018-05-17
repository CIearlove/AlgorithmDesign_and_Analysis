package Chapter02_RecursiveAndDivideStrategy;
/**
 * ����n=2^k���˶�ԱҪ��������ѭ������
 * ��1��ÿ��ѡ�ֱ���������n-1��ѡ�ָ���һ�Σ�
 * ��2��ÿ��ѡ��һ��ֻ����һ�Σ�
 * ��3��ѭ����һ������n-1�졣
 * @author DuoZhu
 *
 */
public class CycleSchedule {

	public static void main(String[] args) {
		
		int k = 2;
		int n = 1;
		
		//��������
		for(int i=1;i<=k;i++){
			n*=2;
		}
		
		//�����ճ̱�
		int t[][] = new int[n+1][n+1];
		table(k,t);
		
		//��ӡ������
		for(int i=0;i<n+1;i++){
			for(int j=0;j<n+1;j++){
				System.out.print(t[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * ���Խ������ճ̱���Ƴ���n�к�n-1�еı�
	 * ���е�i�к͵�j�д������i��ѡ���ڵ�j����������ѡ�֡�
	 * @param k k=log2 N��NΪ�����˶�Ա����������Ϊ2��
	 * @param a �����ճ̱�
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
