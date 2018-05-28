package Chapter03_DynamicPlanning;

import Chapter03_DynamicPlanning.Element;

public class MergeSort {

	public static void main(String[] args) {
		
		//N
		int[] a = {5,6,7,2,1,3,9,10};
		int[] b = {2,3,4,5,6,7,8,9};
		int[] c = new int[a.length];
		
		int n = a.length;
        Element[] d = new Element[n];
		for(int i=0;i<n;i++){
			//取a[i]与b[i]的较小值
			int key = a[i]>b[i] ? b[i]:a[i];
			boolean job = a[i]<=b[i];
			d[i] = new Element(key,i,job);
		}
		mergeSort(d);
		for(int i=0;i<d.length;i++){
			System.out.print(d[i].getKey());
		}
	}

	public static void mergeSort(Comparable[] a){
		Comparable[] b = new Comparable[a.length];
		int s = 1;
		while(s<a.length){
			mergePass(a,b,s);//合并到数组b
			s+=s;
			mergePass(b,a,s);//合并到数组a
			s+=s;
		}
	}

	public static void mergePass(Comparable[] x, Comparable[] y, int s) {
		//合并大小为s的相邻数组
		int i = 0;
		while(i <= x.length-2*s){
			//合并大小为s的相邻2段子数组
			merge(x,y,i,i+s-1,i+2*s-1);
			i=i+2*s;
		}
		//剩下的元素个数少于2s
		if(i+s<x.length)
			merge(x,y,i,i+s-1,x.length-1);
		else
			//复制到y
			for(int j=i;j<x.length;j++)
				y[j] = x[j];
	}

	public static void merge(Comparable[] c, Comparable[] d, int l, int m, int r) {
		//合并c[1：m]和c[m+1:r]到d[1:r]
		int i=l,j=m+1,k=l;
		while((i<=m)&&(j<=r))
			if(c[i].compareTo(c[j])<=0)
				d[k++] = c[i++];
			else
				d[k++] = c[j++];
		if(i>m)
			for(int q=j;q<=r;q++)
				d[k++] = c[q];
		else
			for(int q=i;q<=m;q++)
				d[k++] = c[q];
	}
}
