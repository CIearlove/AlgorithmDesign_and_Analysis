package Chapter02_RecursiveAndDivideStrategy;

public class MergeSort {
  /** The method for sorting the numbers 
 * @param <E>*/
  @SuppressWarnings("unchecked")
public static <E extends Comparable<E>> void mergeSort(E[] list) {
    if (list.length > 1) {
      // Merge sort the first half
      E[] firstHalf = (E[])new Comparable[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      E[] secondHalf = (E[])new Comparable[secondHalfLength];
      System.arraycopy(list, list.length / 2,secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf);

      // Merge firstHalf with secondHalf
      E[] temp = merge(firstHalf, secondHalf);
      System.arraycopy(temp, 0, list, 0, temp.length);
    }
  }

  /** Merge two sorted lists */
  private static <E extends Comparable> E[] merge(E[] list1, E[] list2) {
	E[] temp = (E[])new Comparable[list1.length + list2.length];

    int current1 = 0; // Current index in list1
    int current2 = 0; // Current index in list2
    int current3 = 0; // Current index in temp

    //将list1与list2的数组排序
    while (current1 < list1.length && current2 < list2.length) {
      if (list1[current1].compareTo(list2[current2]) < 0)
        temp[current3++] = list1[current1++];
      else
        temp[current3++] = list2[current2++];
    }

    //list2已经比较完
    while (current1 < list1.length)
      temp[current3++] = list1[current1++];

    //list1已经比较完
    while (current2 < list2.length)
      temp[current3++] = list2[current2++];

    return temp;
  }

  /** A test method */
  public static void main(String[] args) {
/*
 *   //N
	int[] a = {5,6,7,2,1,3,9,10};
	int[] b = {2,3,4,5,6,7,8,9};
	int[] c = new int[a.length];	
	int n = a.length;
	Element[] list = new Element[n];
	for(int i=0;i<n;i++){
	//取a[i]与b[i]的较小值
	  int key = a[i]>b[i] ? b[i]:a[i];
	  boolean job = a[i]<=b[i];
	  list[i] = new Element(key,i,job);
	}
 */
	String[] list = {"ee","cc","aa","ww","ff"};
    mergeSort(list);
    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + " ");
  }
}
