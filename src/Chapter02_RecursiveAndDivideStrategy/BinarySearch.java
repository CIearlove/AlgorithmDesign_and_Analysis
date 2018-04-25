package Chapter02_RecursiveAndDivideStrategy;

public class BinarySearch {

	public static void main(String[] args) {
		
		int a[] = {1,2,3,4,5,6,7,8,9,10};
		if(binarySearch(a,1,10) != -1){
			System.out.println("Found it.");
		}
		else
			System.out.println("Not Found.");
	}

	/**
	 * ��a[0]<=a[1]<=...<=a[n-1]������x
	 * ���ҵ�xʱ�������������е�λ�ã����򷵻�-1
	 * @param a �Ѿ��ź����n��Ԫ��a[0��n-1]
	 * @param x ��Ҫ�ҵ��ض�Ԫ��x
	 * @param n Ԫ�صĸ���
	 * @return
	 */
	public static int binarySearch(int[] a,int x,int n){
		int left = 0;
		int right = n-1;
		
		while(left<=right){
			int middle = (left+right)/2;
			if(x == a[middle])
				return middle;
			if(x > a[middle])
				left = middle+1;
			else
				right = middle-1;
		}
		
		return -1;//δ�ҵ�x
	}
}
