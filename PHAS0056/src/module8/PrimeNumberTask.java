package module8;

import java.util.*; //Importing utilities package

//Implements Runnable interface, with inherited abstract 'run()' function that will check if each integer is prime number
public class PrimeNumberTask implements Runnable {

	//Member variables
	final ArrayList<Integer> primeList; //'ArrayList' for prime numbers
	int counter; //Counter for largest integer checked

	//Constructor to set 'ArrayList's for prime numbers and initialises counter
	public PrimeNumberTask(ArrayList<Integer> primeList) {
		//Assigns member variables to corresponding arguments
		this.primeList = primeList;
		this.counter = 0;
	}

	/**
	 * Implements 'run()' method as prime number checker
	 * Starting from an initial value of 0, each integer is checked to see
	 * if it is a prime by looping from 2 to the square root of the current
	 * integer, and checking if the current integer is divisible by a smaller
	 * integer. If not divisible, current integer is prime, so 'counter'
	 * increased by 1 and integer added to 'primeList'. If divisible or it is
	 * equal to 0 or 1, then current integer is not prime, so added only to
	 * 'primeList'. If thread is interrupted while within loop, 'run()' method
	 * returns.
	 */
	public void run() {
		int num = 1; //Setting initial integer
		while(num>=0) {
			if (Thread.currentThread().isInterrupted()) return;
			boolean flag = false; //Boolean to check if number is prime (true = not prime)

			//Loops over all integers smaller or equal to square root of current integer
			for(int i = 2; i <= Math.sqrt(num); ++i) {
				//Condition for number to not be prime
				if(num % i == 0) {
					flag = true; //Boolean variable changed if number not prime
					break; //Exits from 'for' loop
				}
			}
			//If integer is prime, adds to 'primeList' and 'counter' increased by 1
			if(!flag & num != 0 & num != 1) {
//				System.out.println(num + " is a prime number.");
				primeList.add(num);
				counter++;
			}
			//If integer is 0 or 1, 'counter' increased by 1 since they are not prime
			else if(num == 0 || num == 1) {
//	        	System.out.println(num + " is not a prime number.");
	        	counter++;
	        }
			//Numbers not fitting above condition are not prime, so 'counter' increased by 1
			else {
//				System.out.println(num + " is not a prime number.");
				counter++;
			}
			num++; //Incrementally increasing current integer by 1 to check if prime
		}
	}
}