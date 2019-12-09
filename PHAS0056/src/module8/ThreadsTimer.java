package module8;

import java.util.*; //Importing utilities package
import java.util.concurrent.*; //Importing 'concurrent' package

/* Main class to calculate 2 estimates of PI. 1st from single thread Monte Carlo method and 2nd from
 * 4 different threads that calculate PI using 1/4 of points each simultaneously and value of PI is averaged
 * from all 4 values. Each method is timed by finding difference between their respective start and
 * end times */
public class ThreadsTimer {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long nPoints = 10000000L; //Number of points to randomly generate
		int nThreads = 4; //Number of threads for 2nd estimate

		//METHOD 1
		/* Calculates value of PI for given number of randomly generated points.
		 * Duration 'duration1' also calculated from end time and start time
		 * '(endTime1-startTime1)' */
		long startTime1 = System.currentTimeMillis();
		MonteCarloPiCalculatorTask task1 = new MonteCarloPiCalculatorTask(nPoints); //Setting number of points to generate
		double pi1 = task1.call(); //Calculating value of PI
		long endTime1 = System.currentTimeMillis();
		long duration1 = endTime1-startTime1;
		System.out.println("<Single Thread>");
		System.out.println("Value of PI: "+pi1);
		System.out.println("Duration of Single Thread: "+duration1+" milliseconds");

		//METHOD 2
		/* Calculates PI by dividing number of points across 4 threads, then averaging value
		 * of PI from each thread. Once again calculates duration 'duration4' of method from
		 * end time and start time '(endTime4-startTime4)'. For running multiple parallel programs,
		 * a thread pool with 'nThreads' number of active processing threads is created by creating
		 * an 'ExecutorService' object, which returns a 'Future' of 'Double'. The 'Future' represents
		 * the result of each parallel calculation. */
		long startTime4 = System.currentTimeMillis();
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		List<Future<Double>> futures = new ArrayList<Future<Double>>(); //'ArrayList' of results for parallel calculations

		/* Calculates value of PI for each thread, by submitting 'Callable' task using 'submit()' function
		 * to the 'ExecutorService' thread pool, and then returning a result of type 'Future'. The 'Future'
		 * is then added to 'ArrayList' above */
		for (int iThread = 0; iThread < nThreads; ++iThread) {
			MonteCarloPiCalculatorTask task4 = new MonteCarloPiCalculatorTask(nPoints/nThreads);
			Future<Double> future = threadPool.submit(task4); //Submitting 'Callable' task to thread pool
			futures.add(future);
		}
		double sum = 0.0;
		/* Summing together all calculated values of PI, from 'Double' within 'Future' within 'ArrayList',
		 * hence why there are 2x 'get()' functions used */
		for (int iThread = 0; iThread < nThreads; ++iThread) {
			double result = futures.get(iThread).get();
			sum += result;
		}
		threadPool.shutdown(); //Previously submitted tasks execute before terminating, new tasks can't be submitted
		double pi4 = sum/nThreads; //Sum of all PI divided by number of results used
		long endTime4 = System.currentTimeMillis();
		long duration4 = endTime4-startTime4;
		System.out.println("\n<Multiple Threads>");
		System.out.println("Value of PI: "+pi4);
		System.out.println("Duration of 4 Threads: "+duration4+" milliseconds");

		System.out.println("\n<Comments>");
		System.out.println("Multiple threads is faster than a single thread calculation by "+(duration1-duration4)+"\n"
				+ "milliseconds. Therefore it is clear to see that spreading out operations and calculations over multiple\n"
				+ "threads, instead of just a single thread, can increase the efficiency of the program and get results faster.");
		System.out.println("\nThe true value of PI is "+Math.PI+". The difference between this and the value from the single\n"
				+ "thread is "+(Math.PI-pi1)+", and the difference with the multiple thread calculation is "+(Math.PI-pi4)+".\n"
				+ "Therefore it is clear to see that both methods yield very accurate values of PI. However, the method in which\n"
				+ "operations have been divided amongst 4 separate threads is clearly much more efficient than a single thread, as\n"
				+ "both achieve equally accurate results but the multi-thread calculation is significantly faster. So rather than\n"
				+ nPoints+" points run consecutively, "+(nPoints/nThreads)+" points is run simultaneously in 4 threads. However this\n"
				+ "only works to separate tasks into multiple threads that can be done independently, such as in this case.");
	}
}