package module8;

import java.util.*;

public class ThreadsMain {

	public static void main(String[] args) {
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		ArrayList<Integer> allNums = new ArrayList<Integer>();

		CountdownTask timer = new CountdownTask(10);
		PrimeNumberTask prime = new PrimeNumberTask(primeList, allNums);

		Thread timerThread = new Thread(timer);
		Thread primeThread = new Thread(prime);

		timerThread.start();
		primeThread.start();

		try {
			timerThread.join();
		}
		catch(Exception e) {
			System.out.println(e);
		}

		primeThread.interrupt();

		System.out.println("\nLargest Prime Number: "+primeList.get(primeList.size()-1));
		System.out.println("Largest Integer: "+allNums.get(allNums.size()-1));
		System.out.println("Total Number of Prime Numbers: "+primeList.size());
	}
}