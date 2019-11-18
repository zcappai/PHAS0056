package module6;

import java.util.*;	

public class ChiSquared implements GoodnessOfFitCalculator {

	/**
	 * CALCULATES CHI-SQUARED STATISTIC BETWEEN FUNCTION AND DATA
	 * @param theo - 'Theory' object that sets n for y=x^n
	 * @param data - 'ArrayList' of 'DataPoint' objects
	 * @return
	 */
	public double goodnessOfFit(Collection<DataPoint> data, Theory theo) {
		//Chi-squared calculates using equation: sum((y_coord-y_theor)^2/ey^2)
		double chi2 = 0; //Initialising chi-squared variable

		/* Calculates chi-squared statistic for each 'DataPoint' object, and adds
		 * to 'chi2' variable */
		for(int i = 0; i<data.size(); i++) {
			DataPoint point = data.iterator().next(); //Gets i^th 'DataPoint' object from 'ArrayList'

			//Gets 'x', 'y', 'ey' and calculates theoretical 'y' for given 'x'
			double x_coord = point.getX();
			double y_coord = point.getY();
			double y_error = point.getEY();
			double y_theor = theo.y(x_coord);

			chi2 += Math.pow((y_coord-y_theor), 2)/(y_error*y_error); //Each contribution added to variable
		}
		return chi2; //Returns chi-squared statistic
	}

	public Collection<Double> quadCollection(Collection<DataPoint> data, QuadraticTheory n) {
		Collection<Double> theoArray = null;
		for(DataPoint point : data) {
			double xcoord = point.getX();
			Double y = n.y(xcoord);
			theoArray.add(y);
		}
		return theoArray;
	}

	public Collection<Double> powerCollection(Collection<DataPoint> data, PowerLawTheory n) {
		Collection<Double> theoArray = null;
		for(DataPoint point : data) {
			double xcoord = point.getX();
			Double y = n.y(xcoord);
			theoArray.add(y);
		}
		return theoArray;
	}
}
