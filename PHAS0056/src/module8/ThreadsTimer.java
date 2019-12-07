package module8;

import java.util.*;
import java.util.concurrent.*;

public class ThreadsTimer {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long nPoints = 10000000L;
		int nThreads = 4;

		long startTime1 = System.currentTimeMillis();
		MonteCarloPiCalculatorTask task1 = new MonteCarloPiCalculatorTask(nPoints);
		double pi1 = task1.call();
		long endTime1 = System.currentTimeMillis();
		long duration1 = endTime1-startTime1;
		System.out.println("Value of PI: "+pi1);
		System.out.println("Duration of Single Thread: "+duration1+" milliseconds");

		long startTime4 = System.currentTimeMillis();
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		List<Future<Double>> futures = new ArrayList<Future<Double>>();
		for (int iThread = 0; iThread < nThreads; ++iThread) {
			MonteCarloPiCalculatorTask task4 = new MonteCarloPiCalculatorTask(nPoints/nThreads);
			Future<Double> future = threadPool.submit(task4);
			futures.add(future);
		}
		double sum = 0.0;
		for (int iThread = 0; iThread < nThreads; ++iThread) {
			double result = futures.get(iThread).get();
			sum += result;
		}
		threadPool.shutdown();
		double pi4 = sum/nThreads;
		long endTime4 = System.currentTimeMillis();
		long duration4 = endTime4-startTime4;
		System.out.println("\nValue of PI: "+pi4);
		System.out.println("Duration of 4 Threads: "+duration4+" milliseconds");

		System.out.println("\n<Comments>");
		System.out.println("Multiple threads is faster than a single thread! The multithread calculation is\n"
				+ "faster than the single thread calculation by "+(duration1-duration4)+" milliseconds. Therefore\n"
				+ "it is clear to see that spreading out operations and calculations over multiple thread, instead\n"
				+ "of just a single thread, can increase the efficiency of the program and get results faster.");
	}
}