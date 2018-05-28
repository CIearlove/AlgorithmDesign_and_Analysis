package Chapter03_DynamicPlanning;

/**
 * ��ˮ��ҵ��������Johnson�㷨��
 * ��1����N1 = {i|A_i<B_i},N2 = {i|A_i>=B_i};
 * ��2����N1����ҵ��A_i�ķǼ������򣻽�N2����ҵ��B_i�ķ���������;
 * ��3��N1����ҵ��N2����ҵ��������Johnson��������ŵ��ȡ�
 * 
 * �����ҵi��j����min{B_i��A_j}>=min{B_j��A_i}�������ҵi��j����Johnson����ʽ��
 * ������������Johnson����ĵ��Ⱦ�����ͬ�ļӹ�ʱ�䡣
 * �Ӷ���������Johnson����ĵ��Ⱦ�Ϊ���ŵ��ȡ�
 * ���ˣ�����ˮ��ҵ��������ת��Ϊ������Johnson����ĵ������⡣
 * @author DuoZhu
 *
 */
public class FlowScheduling {

	public static void main(String[] args) {
		
		  //N
		  int[] a = {5,6,7,2,1,3,9,10};
		  int[] b = {2,3,4,5,6,7,8,9};
		  int[] c = new int[a.length];
		  
		  int Total_Time_Consuming = flowShop(a,b,c);
		  System.out.println("�ӹ���N����ҵ��������ʱ��"+Total_Time_Consuming);
		  System.out.println("��ˮ��ҵ�������У�");
		  for(int i=0;i<c.length;i++){
			  System.out.print(c[i]+1+" ");
		  }
	}

	/**
	 * 
	 * @param a M1�ӹ���ҵi�����ʱ��Ϊa_i
	 * @param b M2�ӹ���ҵi�����ʱ��Ϊb_i
	 * @param c ���ŵ�������
	 * @return k ������ʱ������ֵ
	 */
	public static int flowShop(int[] a,int[] b,int[] c){
		
		int n = a.length;
		Element[] d = new Element[n];
		
		for(int i=0;i<n;i++){
			//ȡa[i]��b[i]�Ľ�Сֵ
			int key = a[i]>b[i] ? b[i]:a[i];
			boolean job = a[i]<=b[i];
			d[i] = new Element(key,i,job);
		}
		
		//����key��������
		MergeSort.mergeSort(d);
		
		int j=0,k=n-1;
		for(int i=0;i<n;i++){
			if(d[i].isJob())
				c[j++] = d[i].getIndex();
			else
				c[k--] = d[i].getIndex();
		}
		
		j = a[c[0]];
		k = j+b[c[0]];
		
		for(int i=1;i<n;i++){
			j+=a[c[i]];
			k = j<k ? k+b[c[i]]:j+b[c[i]];//���ĵ���ʱ��
		}
		return k;
	}
}
