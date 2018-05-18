package Chapter02_RecursiveAndDivideStrategy;

/**
 * ѭ�����ճ̱���n = 2^k���˶�ԱҪ��������ѭ����
 * ���̱����㣺
 * ÿ��ѡ�ֱ���������n-1��ѡ�ָ���һ��
 * ÿ��ѡ��һ��ֻ�ܲ���һ��
 * ѭ������n-1���ڽ���
 * 
 * ����˼·��
 * �������ճ̱���Ƴ�һ��n�к�n-1�еı���i�У���j�зֱ������i��ѡ���ڵ�j����������ѡ��
 * ���ӣ�
 * 4��ѡ��
 * ---------
 * |1|2|3|4|
 * ---------
 * |2|1|4|3|
 * ---------
 * |3|4|1|2|
 * ---------
 * |4|3|2|1|
 * ---------
 * ����˼�룺���������򿴳��Ŀ飬����1��(0,i) ����2��(0,r+i) ����3��(r,i) ����4��(r,r+i)
 *  �ݹ�ִ�е�������1����������4������2����������3
 * ---------
 * | 1 | 2 |
 * ---------
 * | 3 | 4 |
 * ---------
 *  * @author ������
 *
 */
public class CycleSchedule2 {
	
    public static int[][] table(int k){
    	//��λ���㣬value=1�������ƶ�kλ
        int n = 1<<k;
        int[][] a = new int[n][n];
        //�������̱��һ������
        for(int i = 0; i<n;i++)
            a[0][i] = i+1;
        //���÷����㷨�������������̱�
        for(int r = 1;r<n;r<<=1){
            for(int i =0;i<n;i += 2*r){
                copy(a,r,r+i,0,i,r);
                copy(a,r,i,0,r+i,r);
            }
        }
        return a;
    }
    
    /**
     * 
     * @param a �����ճ̱�
     * @param tox
     * @param toy
     * @param fromx
     * @param fromy
     * @param r ��r*r�ľ����ֵ�õ�����
     */
    private static void copy(int[][] a, int tox, int toy, 
            int fromx, int fromy, int r){
        for(int i =0;i<r;i++){
            for(int j = 0;j<r;j++){
                a[tox+i][toy+j] = a[fromx+i][fromy+j];
            }
        }
    }
        
    
    
    public static void main(String[] args) {

        int[][] a = table(2);
        for(int i=0;i<a.length;i++){
            for(int j = 0;j<a[0].length;j++){
                System.out.print(a[i][j] + "ss ");
            }
            System.out.println();
        }

    }

}
