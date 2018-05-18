package Chapter02_RecursiveAndDivideStrategy;

/**
 * 循环赛日程表：有n = 2^k个运动员要进行网球循环赛
 * 赛程表满足：
 * 每个选手必须与其他n-1个选手各赛一次
 * 每个选手一天只能参赛一次
 * 循环赛在n-1天内结束
 * 
 * 解题思路：
 * 将比赛日程表设计成一个n行和n-1列的表，第i行，第j列分别填入第i个选手在第j天所遇到的选手
 * 栗子：
 * 4个选手
 * ---------
 * |1|2|3|4|
 * ---------
 * |2|1|4|3|
 * ---------
 * |3|4|1|2|
 * ---------
 * |4|3|2|1|
 * ---------
 * 分治思想：将所有区域看成四块，区域1：(0,i) 区域2：(0,r+i) 区域3：(r,i) 区域4：(r,r+i)
 *  递归执行的是区域1拷贝到区域4，区域2拷贝到区域3
 * ---------
 * | 1 | 2 |
 * ---------
 * | 3 | 4 |
 * ---------
 *  * @author 焦含寒
 *
 */
public class CycleSchedule2 {
	
    public static int[][] table(int k){
    	//移位运算，value=1，向左移动k位
        int n = 1<<k;
        int[][] a = new int[n][n];
        //构造赛程表第一行数据
        for(int i = 0; i<n;i++)
            a[0][i] = i+1;
        //采用分治算法，构造整个赛程表
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
     * @param a 比赛日程表
     * @param tox
     * @param toy
     * @param fromx
     * @param fromy
     * @param r 有r*r的矩阵的值得到复制
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
