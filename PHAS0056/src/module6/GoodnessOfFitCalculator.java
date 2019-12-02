package module6;

import java.util.*; //Importing utilities package

//Interface defines abstract method to calculate goodness of fit of data to theoretical function
public interface GoodnessOfFitCalculator {

	/**
	 * DEFINES FUNCTION TO CALCULATE GOODNESS OF FIT OF IMPORTED DATA TO THEORETICAL FUNCTION
	 * @param data
	 * @param theo
	 * @return
	 */
	public double goodnessOfFit(Collection<DataPoint> data, Theory theo);
}