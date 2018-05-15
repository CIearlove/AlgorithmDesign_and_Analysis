package test;
/**
 * ����ר����
 * @author DuoZhu
 *
 */
import java.util.Scanner;

public class test {
    /**
     * 
     * @param n ͼ��Ҷ�����Ĵ�С
     * @param p ͼ��Ҷ�����
     * @param s s[i]��ʾ��0��iѹ��Ϊһ��ռ���ٴ洢�ռ�
     * @param l l����length
     * @param b b����bits
     */
    public void Compress(int n, int[] p, int[] s, int[] l, int[] b) {
        int Lmax = 256;
        int header = 11;
        s[0] = 0;
        for (int i = 1; i <= n; i++) {
            b[i] = length(p[i]);// �������ص�p��Ҫ�Ĵ洢λ��
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
     * ���������Ķ����Ƶĳ��� return ���ȣ�
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
     * �������Ž⺯��1
     */
    public int Traceback(int n, int i, int[] s, int[] l) {
        if (n == 0)
            return i;
        i = Traceback(n - l[n], i, s, l);
        s[i++] = n - l[n];// ����Ϊs[]���鸳ֵ�������洢�ֶ�λ��
        return i;
    }

    /*
     * �������Ž⺯��2
     */
    public void Output(int[] s, int[] l, int[] b, int n) {
        // �����s[n]�洢λ����s[]���������¸�ֵ�������洢�ֶε�λ��
        System.out.println("ͼ��ѹ�������С�ռ�Ϊ�� " + s[n]);
        int m = 0;
        m = Traceback(n, m, s, l);
        s[m] = n;
        System.out.println("��ԭ�Ҷ����зֳ�  " + m + " �����ж�");
        for (int j = 1; j <= m; j++) {
            l[j] = l[s[j]];
            b[j] = Maxb(s, l, b, j);
        }
        for (int j = 1; j <= m; j++) {
            System.out.println("�γ��ȣ�" + l[j] + ",����洢λ����" + b[j]);
        }
    }

    /*
     * �����j����bmax
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
     * ͼƬѹ��
     */
    public static void image_compressions(Scanner sc)
            throws NumberFormatException {

        String c = sc.next();
        String[] o = c.split(",");
        int[] p = new int[o.length + 1]; // ͼ��Ҷ����� �±��1��ʼ����
        for (int i = 1; i < p.length; i++) {
            p[i] = Integer.parseInt(o[i - 1]);
        }

        int N = p.length;
        System.out.println();
        // int N=7;
        // int[] p={0,10,12,15,255,1,2};//ͼ��Ҷ����� �±��1��ʼ����
        int[] s = new int[N];
        int[] l = new int[N];
        int[] b = new int[N];
        System.out.println("ͼ��ĻҶ�ֵ����Ϊ��");
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
            System.out.println("������ͼ��ĻҶ�����(�������Զ���Ϊ�ָ���)��");
            try {
                image_compressions(sc);
            } catch (Exception e) {
                System.out.println("���벻���Ϲ淶�����������룡");
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
                System.out.println("ѡ��������Ĳ����� 1--����   2--�˳�");
                int t = sc.nextInt();
                switch (t) {
                case 1:
                    System.out
                            .println("---------------------------------------------------------------------------------------");
                    System.out.println();
                    break b;
                case 2:
                    System.out.println("���˳���");
                    break a;
                default:
                    System.out.println("û�п�ִ�еĲ��������������룡");
                    System.out
                            .println("---------------------------------------------------------------------------------------");
                    System.out.println();
                    break;
                }
            }
        }
    }
}
