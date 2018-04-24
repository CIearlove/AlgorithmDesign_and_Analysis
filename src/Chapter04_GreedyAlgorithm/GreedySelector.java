package Chapter04_GreedyAlgorithm;

public class GreedySelector {

	public static void main(String[] args) {
		
		//��ʼʱ��
		int s[] = {1,3,0,5,3,5,6,8,8,2,12};
		//����ʱ��
		int f[] = {4,5,6,7,8,9,10,11,12,13,14};
		//�i�ڼ���A�У����ҽ���A[i]��ֵΪtrue
		boolean a[] = new boolean[11]; 
		
		greedySelector(s,f,a);
		System.out.print("̰���㷨��ѡ��Ļ:");
		for(int i=0;i<a.length;i++){
			if(a[i]){
				System.out.print(" "+i);
			}
		}
	}
	/**
	 * 
	 * @param s �������ʼʱ��洢������s
	 * @param f ����Ľ���ʱ��洢������f���Ұ�����ʱ��ķǼ���f_1<=f_2<=...<=f_n����
	 * @param a ����A�洢��ѡ��Ļ���i�ڼ���A�У����ҽ���A[i]��ֵΪtrue��
	 * @return  ��ѡ��Ļ����
	 */
	public static int greedySelector(int[] s,int[] f,boolean[] a){
		
		int n = s.length-1;
		a[0] = true;
		int j = 0;//��¼���һ�μ���A�Ļ����һ�μ�����ǻ1
		int count = 1;
		for(int i=1;i<=n;i++){
			/*
			 * ����f_j���ǵ�ǰ����A�����л��������ʱ�䣬
			 * �ʻi�뵱ǰ����A�����л���ݵĳ�ֱ�Ҫ�������俪ʼʱ��s_i��С��������뼯��A�Ļj�Ľ���ʱ��f_j��
			 * ��s_i>=f_j��
			 */
			if(s[i] >= f[j]){
				a[i] = true;
				//���i��A���ݣ���i��Ϊ������뼯��A�еĻ����ȡ���j��λ�á�
				j=i;
				count++;
			}
			else
				a[i] = false;
		}
		return count;
		
	}
}
