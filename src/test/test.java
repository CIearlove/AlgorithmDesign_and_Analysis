package test;
/**
 * 测试专用类
 * @author DuoZhu
 *
 */
import java.util.Scanner;

public class test {
    /**
     * 
     * @param n 图像灰度数组的大小
     * @param p 图像灰度数组
     * @param s s[i]表示从0到i压缩为一共占多少存储空间
     * @param l l代表length
     * @param b b代表bits
     */
    public void Compress(int n, int[] p, int[] s, int[] l, int[] b) {
        int Lmax = 256;
        int header = 11;
        s[0] = 0;
        for (int i = 1; i <= n; i++) {
            b[i] = length(p[i]);// 计算像素点p需要的存储位数
            int bmax = b[i];
            s[i] = s[i - 1] + bmax;
            l[i] = 1;
            for (int j = 2; j <= i && j <= Lmax; j++) {
                if (bmax < b[i - j + 1]) {
                    bmax = b[i - j + 1];
                }
                if (s[i] > s[i - j] + j * bmax) {
                    s[i] = s[i - j] + j * bmax;
                    l[i] = j;
                }
            }
            s[i] += header;
        }
    }

    /*
     * 计算整数的二进制的长度 return 长度；
     */
    public int length(int i) {
        int k = 1;
        i = i / 2;
        while (i > 0) {
            k++;
            i = i / 2;
        }
        return k;
    }

    /*
     * 构造最优解函数1
     */
    public int Traceback(int n, int i, int[] s, int[] l) {
        if (n == 0)
            return i;
        i = Traceback(n - l[n], i, s, l);
        s[i++] = n - l[n];// 重新为s[]数组赋值，用来存储分段位置
        return i;
    }

    /*
     * 构造最优解函数2
     */
    public void Output(int[] s, int[] l, int[] b, int n) {
        // 在输出s[n]存储位数后，s[]数组则被重新赋值，用来存储分段的位置
        System.out.println("图像压缩后的最小空间为： " + s[n]);
        int m = 0;
        m = Traceback(n, m, s, l);
        s[m] = n;
        System.out.println("将原灰度序列分成  " + m + " 段序列段");
        for (int j = 1; j <= m; j++) {
            l[j] = l[s[j]];
            b[j] = Maxb(s, l, b, j);
        }
        for (int j = 1; j <= m; j++) {
            System.out.println("段长度：" + l[j] + ",所需存储位数：" + b[j]);
        }
    }

    /*
     * 计算第j段中bmax
     */
    public int Maxb(int[] s, int[] l, int[] b, int j) {
        int bmax = 0;
        if (j == 1) {
            bmax = b[1];
            for (int i = 2; i <= s[j]; i++) {
                if (bmax < b[i])
                    bmax = b[i];
            }
        } else {
            bmax = b[s[j - 1] + 1];
            for (int i = s[j - 1] + 2; i <= s[j]; i++) {
                if (bmax < b[i]) {
                    bmax = b[i];
                }
            }
        }
        return bmax;
    }
    /**
     * 图片压缩
     */
    public static void image_compressions(Scanner sc)
            throws NumberFormatException {

        String c = sc.next();
        String[] o = c.split(",");
        int[] p = new int[o.length + 1]; // 图像灰度数组 下标从1开始计数
        for (int i = 1; i < p.length; i++) {
            p[i] = Integer.parseInt(o[i - 1]);
        }

        int N = p.length;
        System.out.println();
        // int N=7;
        // int[] p={0,10,12,15,255,1,2};//图像灰度数组 下标从1开始计数
        int[] s = new int[N];
        int[] l = new int[N];
        int[] b = new int[N];
        System.out.println("图像的灰度值序列为：");
        for (int i = 1; i < N; i++) {
            System.out.print(p[i] + " ");
        }
        System.out.println();

        test ic = new test();
        ic.Compress(N - 1, p, s, l, b);
        ic.Output(s, l, b, N - 1);
    }

    public static void main(String[] args) {
        boolean flag = true;
        boolean bflag = true;
        Scanner sc = new Scanner(System.in);
        a: while (flag) {
            System.out.println("请输入图像的灰度序列(整数，以逗号为分隔符)：");
            try {
                image_compressions(sc);
            } catch (Exception e) {
                System.out.println("输入不符合规范，请重新输入！");
                System.out
                        .println("---------------------------------------------------------------------------------------");
                System.out.println();
                continue;
            }
            System.out.println();
            System.out
                    .println("---------------------------------------------------------------------------------------");
            System.out.println();
            b: while (bflag) {
                System.out.println("选择接下来的操作： 1--继续   2--退出");
                int t = sc.nextInt();
                switch (t) {
                case 1:
                    System.out
                            .println("---------------------------------------------------------------------------------------");
                    System.out.println();
                    break b;
                case 2:
                    System.out.println("已退出！");
                    break a;
                default:
                    System.out.println("没有可执行的操作，请重新输入！");
                    System.out
                            .println("---------------------------------------------------------------------------------------");
                    System.out.println();
                    break;
                }
            }
        }
    }
}
