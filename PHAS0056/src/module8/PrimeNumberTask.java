package module8;

import java.util.*; //Importing utilities package

//Implements Runnable interface, with inherited abstract 'run()' function that will check if each integer is prime number
public class PrimeNumberTask implements Runnable {

	//Member variables
	final ArrayList<Integer> primeList; //'ArrayList' for prime numbers
	final ArrayList<Integer> allNums; //'ArrayList' for all numbers

	//Constructor to set 'ArrayList's for prime numbers and all numbers
	public PrimeNumberTask(ArrayList<Integer> primeList, ArrayList<Integer> allNums) {
		//Assigns member variables to corresponding arguments
		this.primeList = primeList;
		this.allNums = allNums;
	}

	/**
	 * Implements 'run()' method as prime number checker
	 * Starting from an initial value of 0, each integer is checked to see
	 * if it is a prime by looping from 2 to the square root of the current
	 * integer, and checking if the current integer is divisible by a smaller
	 * integer. If not divisible, current integer is prime, so added to both 'allNums'
	 * and 'primeList'. If divisible or it is equal to 0 or 1, then current
	 * integer is not prime, so added to only 'primeList'.
	 * If thread is interrupted while within loop, 'run()' method returns
	 */
	public void run() {
		int num = 0; //Setting initial integer
		while(num>=0) {
			if (Thread.currentThread().isInterrupted()) return;
			boolean flag = false; //Boolean to check if number is prime (true = not prime)

			//Loops over all integers smaller or equal to square root of current integer
			for(int i = 2; i <= Math.sqrt(num); ++i) {
				//Condition for number to not be prime
				if(num % i == 0) {
					flag = true; //Boolean variable changed since number is not prime
					break; //Exits from 'for' loop
				}
			}
			//If integer is prime, adds to 'primeList' and 'allNums'
			if(!flag & num != 0 & num != 1) {
//				System.out.println(num + " is a prime number.");
				primeList.add(num);
				allNums.add(num);
			}
			//If integer is 0 or 1, adds to 'allNums' since they are not prime
			else if(num == 0 || num == 1) {
//	        	System.out.println(num + " is not a prime number.");
	        	allNums.add(num);
	        }
			//Numbers not fitting above condition are not prime, so added to 'allNums' 
			else {
//				System.out.println(num + " is not a prime number.");
				allNums.add(num);
			}
			num++; //Incrementally increasing current integer by 1 to check if prime
		}
	}
}