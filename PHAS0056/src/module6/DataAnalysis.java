package module6;

import java.util.*; //Importing utilities package

//Creates method to calculate which theoretical function best fits imported data
public class DataAnalysis {

	/**
	 * CALCULATES AND RETURNS WHICH THEORETICAL FUNCTION BEST FITS IMPORTED DATA
	 * Initially calculates chi-squared value of 1st theoretical, then compares
	 * subsequently calculated chi-squared values to best chi-squared value.
	 * @param data - 'Collection' of 'DataPoint' objects from imported data
	 * @param theories - 'Collection' of 'Theory's for each theoretical function tested
	 * @param gofCalculator - 'GoodnessOfFitCalculator' to calculate goodness of fit
	 * @return
	 */
	private static Theory bestTheory(Collection<DataPoint> data,
			Collection<Theory> theories, GoodnessOfFitCalculator gofCalculator) {
		//Initialising variables
		boolean first = true; //'true' only for first entry of 'Collection' of 'Theory's
		double bestGoodnessOfFit = 0.0; //Chi-squared statistic 
		Theory bestTheory = null; //'Theory' for best theoretical function

		/* For each theoretical function, calculates goodness of fit and compares to
		 * goodness of fit of other theoretical functions. 'bestTheory' set to
		 * 'Theory' with lowest goodness of fit (chi-squared statistic) */
		for(Theory theory : theories) {
			//Calculates goodness of fit of current theoretical function
			double gof = gofCalculator.goodnessOfFit(data, theory);

			//Sets initial 'bestGoodnessOfFit' and 'bestTheory' variables
			if(first) {
				bestTheory = theory;
				bestGoodnessOfFit = gof;
				first = false;
			}
			//Compares subsequent functions to current best function
			else if (gof < bestGoodnessOfFit) {
				bestTheory = theory;
				bestGoodnessOfFit = gof;
			}
		}
		return bestTheory; //Returns 'Theory' corresponding to best theoretical function
	}

	public static void main(String[] args) {
		//Initialising URL as string and creating empty 'Collection' of 'DataPoint' objects
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt";
		Collection<DataPoint> data = null; //Empty 'Collection' of 'DataPoint' objects

		/**
		 * Tries to import data from URL and return 'Collection' of 'DataPoint' objects
		 * Catches exception if invalid URL used, no input from URL or no input when
		 * reading line
		 */
		try {
			data = TestDataPoints.dataFromURL(url);
			System.out.println("URL: "+url+"\n");
		}
		catch(Exception e) {
			System.out.println(e+"\nPlease enter a valid URL!");
		}

		//Initialises 'Theory's and sets parameters for each function through argument
		PowerLawTheory n1 = new PowerLawTheory(2);
		PowerLawTheory n2 = new PowerLawTheory(2.05);
		QuadraticTheory n3 = new QuadraticTheory(1, 10, 0);

		//Prints each theoretical function to be tested
		System.out.println("<Functions To Be Tested>");
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);

		//'GoodnessOfFitCalculator' created as new 'ChiSquared' object
		GoodnessOfFitCalculator gf1 = new ChiSquared();

		//'Collection' created as new 'ArrayList' of 'Theory'
		Collection<Theory> theory = new ArrayList<Theory>();

		//Adds each 'Theory' representing theoretical function to 'Collection'
		theory.add(n1);
		theory.add(n2);
		theory.add(n3);

		Theory bestFunction = bestTheory(data, theory, gf1); //Calculates best theoretical function

		//Prints function that fits data the best
		System.out.println("\n<Best Function To Fit Data>");
		System.out.println(bestFunction);
	}
}