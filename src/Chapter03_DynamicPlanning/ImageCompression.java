package Chapter03_DynamicPlanning;

public class ImageCompression {
	
	static final int lmax = 256;//ÿ���ֶεĳ��Ȳ�����256��
	static final int header = 11;//��Ҫ��3λ��ʾb[i]����Ҫ��8λ��ʾl[i]
	static int m;
	
	public static void main(String[] args) {
		
		int[] p = new int[16];
		for(int i=0;i<p.length;i++){
			p[i] = (int)(Math.random()*255);
			System.out.print(p[i]+" ");
		}
		System.out.println();
		
		int[] s = new int[p.length];
		int[] l = new int[p.length];
		int[] b = new int[p.length];
		
		compress(p,s,l,b);
	}

	/**
	 * 
	 * @param p ͼ������ص�Ҷ�ֵ����{p_1,p_2,...,p_n}
	 * @param s s[i],1<=i<=n����������{p_1,p_2,...,p_i}�����ŷֶ�����Ĵ洢λ��
	 * @param l ���ص�Ҷ�ֵ����{p_1,p_2,...,p_n}�ָ��m��������S_1,S_2,...,S_m.
	 *          ��i�����ض�S_i��(1<=i<=m),��l[i]������.
	 * @param b ������,�Ҹö���ÿ�����ض�ֻ��b[i]λ��ʾ.
	 */
	public static void compress(int p[],int s[],int l[],int b[]){
		int n = p.length-1;
		s[0]=0;
		for(int i=1;i<=n;i++){
			b[i] = length(p[i]);
			int bmax = b[i];
			s[i] = s[i-1]+bmax;
			l[i] = 1;
			
			for(int j=2;j<=i && j<= lmax;j++){
				if(bmax<b[i-j+1])
					bmax = b[i-j+1];
				if(s[i]>s[i-j]+j*bmax){
					s[i] = s[i-j]+j*bmax;
					l[i] = j;
				}
			}
			s[i] += header;
		}
	}

	/**
	 * 
	 * @param i ʮ������
	 * @return ��ʾ��ʮ�������Ķ���������λ��
	 */
	public static int length(int i) {
		int k=1;
		i = i/2;
		while(i>0){
			k++;
			i = i/2;
		}
		return k;
	}
	
	private static void traceback(int n,int s[],int l[]){
		if(n==0)
			return;
		traceback(n-l[n],s,l);
		s[m++] = n - l[n];
	}
	
	public static void output(int s[],int l[],int b[]){
		int n = s.length-1;
		System.out.println("The optimal value is: "+s[n]);
		m=0;
		traceback(n,s,l);
		s[m] = n;
		System.out.println("Decomposed into "+m+"segments");
		for(int j=1;j<=m;j++){
			l[j] = l[s[j]];
			b[j] = b[s[j]];
		}
		for(int j=1;j<=m;j++)
			System.out.println(l[j]+","+b[j]);
	}
}
