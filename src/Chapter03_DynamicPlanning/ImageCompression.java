package Chapter03_DynamicPlanning;

public class ImageCompression {
	
	static final int lmax = 256;//每个分段的长度不超过256个
	static final int header = 11;//需要用3位表示b[i]，需要用8位表示l[i]
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
	 * @param p 图像的像素点灰度值序列{p_1,p_2,...,p_n}
	 * @param s s[i],1<=i<=n是像素序列{p_1,p_2,...,p_i}的最优分段所需的存储位数
	 * @param l 像素点灰度值序列{p_1,p_2,...,p_n}分割成m个连续段S_1,S_2,...,S_m.
	 *          第i个像素段S_i中(1<=i<=m),有l[i]个像素.
	 * @param b 接上面,且该段中每个像素都只用b[i]位表示.
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
	 * @param i 十进制数
	 * @return 表示该十进制数的二进制数的位数
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
