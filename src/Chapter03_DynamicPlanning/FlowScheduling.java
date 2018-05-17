package Chapter03_DynamicPlanning;

public class FlowScheduling {

	public static void main(String[] args) {
		
	}

	public static int flowShop(int[] a,int[] b,int[] c){
		int n = a.length;
		Element[] d = new Element[n];
		
		for(int i=0;i<n;i++){
			int key = a[i]>b[i] ? b[i]:a[i];
			boolean job = a[i]<=b[i];
			d[i] = new Element(key,i,job);
		}
		
		//MergeSort.mergeSort(d);
		
		int j=0,k=n-1;
		for(int i=0;i<n;i++){
			if(d[i].job)
				c[j++] = d[i].index;
			else
				c[k--] = d[i].index;
		}
		
		j = a[c[0]];
		k = j+b[c[0]];
		
		for(int i=1;i<n;i++){
			j+=a[c[i]];
			k = j<k ? k+b[c[i]]:j+b[c[i]];
		}
		return k;
	}
}
