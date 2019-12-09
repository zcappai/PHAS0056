package module8;

import java.util.*; //Importing utilities package
import java.util.concurrent.Callable; //Importing 'Callable' interface for function that returns result

//Implements Callable<Double> interface, with inherited abstract 'call()' function to calculate PI using Monte Carlo method
public class MonteCarloPiCalculatorTask implements Callable<Double> {

	//Member variable
	final long n_points; //Number of points to generate within unit square

	//Constructors for setting number of points to generate in unit square
	public MonteCarloPiCalculatorTask(long nPoints) {
		this.n_points = nPoints;
	}

	/**
	 * Implements 'call()' method to calculate value of PI from
	 * given number of randomly generated points within unit square.
	 * This is done by randomly generating doubles for x and y, which
	 * are between 0 and 1. If the point (x, y) is within unit distance
	 * of origin, 'n_in' is incremented by 1. Given enough points, the
	 * ratio of 'n_in' to 'n_points' becomes PI/4, so ratio is multiplied
	 * by 4 to return PI.
	 */
	public Double call() {
		//Formula used: PI = 4*(points within unit distance of origin/total number of points)
		Random rand = new Random(); //Object used to generate random numbers
		long n_in = 0; //Number of points within unit distance of origin

		/* Generates random x and y doubles between 0 and 1, and increments 'n_in' by 1 if
		 * (x, y) within unit distance of origin. 'For' loop runs for 'n_points' number of times */
		for(long iPoint = 0; iPoint < n_points; ++iPoint) {
			double x = rand.nextDouble();
			double y = rand.nextDouble();
			double r2 = x*x + y*y;
			//Checks whether (x, y) is within unit distance of origin
			if(r2 < 1.0) ++n_in;
		}
		return 4.0 * n_in / n_points; //Returns calculated value of PI
	}
}