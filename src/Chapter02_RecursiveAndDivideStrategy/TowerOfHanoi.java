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
	 * @param n 塔座A上的圆盘数
	 * @param fromTower 被移动的塔座A
	 * @param toTower 移动到的塔座B
	 * @param auxTower 辅助塔座C
	 */
	public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
		
		if(n == 1){//结束条件，将编号为1的圆盘从开始塔座直接移到目的塔座
			count++;
			System.out.println(count+" Move disk "+n+" from "+fromTower+" to "+toTower);
			
		}
		else{
			//将n-1个较小圆盘依照移动规则从塔A移动到塔C
			moveDisks(n-1,fromTower,auxTower,toTower);
			count++;
			//将剩下的最大圆盘塔座A移至塔座B
			System.out.println(count+" Move disk "+n+" from "+fromTower+" to "+toTower);
			//将n-1个较小的圆盘依照规则从塔座C移至塔座B
			moveDisks(n-1,auxTower,toTower,fromTower);
		}
	}
	
	
}
