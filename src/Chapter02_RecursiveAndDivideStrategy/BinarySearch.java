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
	 * 在a[0]<=a[1]<=...<=a[n-1]中搜索x
	 * 在找到x时返回其在数组中的位置，否则返回-1
	 * @param a 已经排好序的n个元素a[0：n-1]
	 * @param x 需要找的特定元素x
	 * @param n 元素的个数
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
		
		return -1;//未找到x
	}
}
