package Chapter02_RecursiveAndDivideStrategy;

import java.util.Scanner;

public class TowerOfHanoi {
	private static int count = 0;
	

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number of disks: ");
		int n = input.nextInt();
		//Find the solution recursively
		System.out.println("The moves are:");
		moveDisks(n,'A','B','C');
	}
	
	/**
	 * 
	 * @param n ����A�ϵ�Բ����
	 * @param fromTower ���ƶ�������A
	 * @param toTower �ƶ���������B
	 * @param auxTower ��������C
	 */
	public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
		
		if(n == 1){//���������������Ϊ1��Բ�̴ӿ�ʼ����ֱ���Ƶ�Ŀ������
			count++;
			System.out.println(count+" Move disk "+n+" from "+fromTower+" to "+toTower);
			
		}
		else{
			//��n-1����СԲ�������ƶ��������A�ƶ�����C
			moveDisks(n-1,fromTower,auxTower,toTower);
			count++;
			//��ʣ�µ����Բ������A��������B
			System.out.println(count+" Move disk "+n+" from "+fromTower+" to "+toTower);
			//��n-1����С��Բ�����չ��������C��������B
			moveDisks(n-1,auxTower,toTower,fromTower);
		}
	}
	
	
}
