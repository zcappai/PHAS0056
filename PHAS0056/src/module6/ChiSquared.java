package module6;

import java.util.*; //Importing utilities package

/* Implements 'GoodnessOfFitCalculator' interface to create method
 * for calculating goodness of fit of data to theoretical function */ 
public class ChiSquared implements GoodnessOfFitCalculator {

	/**
	 * CALCULATES CHI-SQUARED STATISTIC BETWEEN DATA AND THEORETICAL FUNCTION
	 */
	public double goodnessOfFit(Collection<DataPoint> data, Theory theo) {
		//Chi-squared calculated using equation: sum((y_coord-y_theor)^2/ey^2)
		double chi2 = 0; //Initialising chi-squared variable

		/* Calculates chi-squared statistic for each 'DataPoint' object, and adds
		 * to 'chi2' variable */
		Iterator<DataPoint> dataIterator = data.iterator(); //Creates iterator over 'Collection' of 'DataPoint' objects
		while(dataIterator.hasNext()) {
			DataPoint point = dataIterator.next(); //Gets next 'DataPoint' object from 'Collection'

			//Gets 'x', 'y', 'ey' and calculates theoretical 'y' for given 'x'
			double x_coord = point.getX();
			double y_coord = point.getY();
			double y_error = point.getEY();
			double y_theor = theo.y(x_coord);

			chi2 += Math.pow((y_coord-y_theor), 2)/(y_error*y_error); //Each contribution added to 'chi2' variable
		}
		return chi2; //Returns chi-squared statistic
	}
}