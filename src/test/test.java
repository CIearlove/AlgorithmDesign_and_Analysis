package test;

public class test {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		int n = 8;
		int[][] array = new int[8][8];
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n-i;j++){
				array[i][j] = 1;
			}
		}
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n-i;j++){
				System.out.print(" "+array[i][j]);
			}
			System.out.println();
		}
	}

}
