package module8;

import java.util.*; //Importing utilities package

/* Main class to run countdown timer while simultaneously checking as many numbers as possible for prime
numbers before countdown reaches 0. Prime number finding is interrupted when countdown reaches 0 */
public class ThreadsMain {
	public static void main(String[] args) {
		//'ArrayList' of 'Integer' for prime numbers
		ArrayList<Integer> primeList = new ArrayList<Integer>();

		//Setting duration of countdown and prime number list
		CountdownTask timer = new CountdownTask(10);
		PrimeNumberTask prime = new PrimeNumberTask(primeList);

		//Creating separate threads for countdown timer and prime number finder
		Thread timerThread = new Thread(timer);
		Thread primeThread = new Thread(prime);

		//Starts each in its own thread to run simultaneously
		timerThread.start();
		primeThread.start();

		//'join()' waits for countdown timer thread to finish
		try {
			timerThread.join();
		}
		catch(Exception e) {
			System.out.println(e);
		}

		//After countdown timer has finished, prime number finder is interrupted
		primeThread.interrupt();

		//Printing the largest prime number found, the largest integer checked so far and the total number of prime numbers
		System.out.println("\nLargest Prime Number: "+primeList.get(primeList.size()-1));
		System.out.println("Largest Integer: "+prime.counter);
		System.out.println("Total Number of Prime Numbers: "+primeList.size());
	}
}