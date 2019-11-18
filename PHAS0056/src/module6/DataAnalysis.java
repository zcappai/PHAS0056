package module6;

import java.util.*;

public class DataAnalysis {

    private static Theory bestTheory(Collection<DataPoint> data,
            Collection<Theory> theories, GoodnessOfFitCalculator gofCalculator) {
        boolean first = true;
        double bestGoodnessOfFit = 0.0;
        Theory bestTheory = null;
        for (Theory theory : theories) {
            double gof = gofCalculator.goodnessOfFit(data, theory);
            if (first) {
                bestTheory = theory;
                bestGoodnessOfFit = gof;
                first = false;
            } else if (gof < bestGoodnessOfFit) {
                bestTheory = theory;
                bestGoodnessOfFit = gof;
            }
        }
        return bestTheory;
    }

	public static void main(String[] args) {
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt";
		Collection<DataPoint> data = null;

		/**
		 * Tries to import data from URL and return 'ArrayList' of 'DataPoint' objects
		 * Catches exception if invalid URL used, no input from URL or no input when
		 * reading line
		 */
		try {
			data = TestDataPoints.dataFromURL(url);
			System.out.println("URL: "+url+"\n");
		}
		catch(Exception e) {
			System.out.println(e);
		}

		PowerLawTheory n1 = new PowerLawTheory(2);
		PowerLawTheory n2 = new PowerLawTheory(2.05);
		QuadraticTheory n3 = new QuadraticTheory(1, 10, 0);
		GoodnessOfFitCalculator gf1 = null;
		GoodnessOfFitCalculator gf2 = null;
		GoodnessOfFitCalculator gf3 = null;

		Collection<Double> theories1 = gf1.powerCollection(data, n1);
		Collection<Double> theories2 = gf2.powerCollection(data, n2);
		Collection<Double> theories3 = gf3.quadCollection(data, n3);
	}
}
