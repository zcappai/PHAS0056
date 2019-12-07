package module8;

import java.util.*;

public class PrimeNumberTask implements Runnable {

	ArrayList<Integer> primeList;
	ArrayList<Integer> allNums;

	public PrimeNumberTask(ArrayList<Integer> primeList, ArrayList<Integer> allNums) {
		this.primeList = primeList;
		this.allNums = allNums;
	}
	public void run() {
		int num = 0;
		while(num>=0) {
			boolean flag = false;
			for(int i = 2; i <= Math.sqrt(num); ++i) {
				// condition for nonprime number
				if(num % i == 0) {
					flag = true;
					break;
				}
			}
			if(!flag & num != 0 & num != 1) {
//				System.out.println(num + " is a prime number.");
				primeList.add(num);
				allNums.add(num);
			}
			else if(num == 0 || num == 1) {
//	        	System.out.println(num + " is not a prime number.");
	        	allNums.add(num);
	        }
			else {
//				System.out.println(num + " is not a prime number.");
				allNums.add(num);
			}
			num++;
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> List = new ArrayList<Integer>();
		ArrayList<Integer> newList = new ArrayList<Integer>();
		PrimeNumberTask prime = new PrimeNumberTask(List, newList);
		prime.run();
		System.out.println(List);
		System.out.println(newList);

		//		https://www.programiz.com/java-programming/examples/prime-number
	}
}