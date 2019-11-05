package module5;
import java.io.*; //Importing input/output package
import java.util.*; //Importing utilities package for 'Scanner'
import java.net.*; //Importing networking package for 'URL'

public class DataAnalysis {

	/**
	 * IMPORTS DATA FROM URL AND CONVERTS TO 'ArrayList' OF 'DataPoint' OBJECTS
	 * @param url of experimental data
	 * @return
	 * @throws Exception - IF INVALID URL USED, NO INPUT FROM URL OR NO INPUT WHEN READING LINE
	 */
	public static ArrayList<DataPoint> dataFromURL(String url) throws Exception{
		//Creates empty 'ArrayList' of 'DataPoint' objects to store 'x', 'y' and 'ey'
		ArrayList<DataPoint> data = new ArrayList<DataPoint>();

		//Streams data from URL and buffers to memory using 'BufferedReader' object
		URL u = new URL(url);
		InputStream is = u.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);

		String line = ""; //Initialising empty string to store line data

		/* While line isn't empty, uses 'Scanner' to look for token separated by whitespace.
		 * Creates new 'DataPoint' object using tokens from each line, which is added to
		 * 'ArrayList' object */
		while ((line = reader.readLine()) != null) {
			Scanner sc = new Scanner(line);
			while(sc.hasNext()) {
				double xtoken = Double.parseDouble(sc.next());
				double ytoken = Double.parseDouble(sc.next());
				double eytoken = Double.parseDouble(sc.next());
				DataPoint p = new DataPoint(xtoken, ytoken, eytoken);
				data.add(p);
			}
			sc.close(); //Closing resource to clean memory
		}
		return data;
	}

	/**
	 * CALCULATES CHI-SQUARED STATISTIC BETWEEN FUNCTION AND DATA
	 * @param theo - 'Theory' object that sets n for y=x^n
	 * @param data - 'ArrayList' of 'DataPoint' objects
	 * @return
	 */
	public static double goodnessOfFit(Theory theo, ArrayList<DataPoint> data) {
		//Chi-squared calculates using equation: sum((y_coord-y_theor)^2/ey^2)
		double chi2 = 0; //Initialising chi-squared variable

		/* Calculates chi-squared statistic for each 'DataPoint' object, and adds
		 * to 'chi2' variable */
		for(int i = 0; i<data.size(); i++) {
			DataPoint point = data.get(i); //Gets i^th 'DataPoint' object from 'ArrayList'

			//Gets 'x', 'y', 'ey' and calculates theoretical 'y' for given 'x'
			double x_coord = point.getX();
			double y_coord = point.getY();
			double y_error = point.getEY();
			double y_theor = theo.y(x_coord);

			chi2 += Math.pow((y_coord-y_theor), 2)/(y_error*y_error); //Each contribution added to variable
		}
		return chi2; //Returns chi-squared statistic
	}

	public static void main(String[] args) {

		//Initialising URL as string and creating empty 'ArrayList' of 'DataPoint' objects
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-xy.txt";
		ArrayList<DataPoint> data = null;

		/**
		 * Tries to import data from URL and return 'ArrayList' of 'DataPoint' objects
		 * Catches exception if invalid URL used, no input from URL or no input when
		 * reading line
		 */
		try {
			data = dataFromURL(url);
			System.out.println("URL: "+url);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		//Creates new 'Theory' object to set n = 2, then calculates and prints chi-squared statistic
		Theory x_2 = new Theory(2);
		double x2 = goodnessOfFit(x_2, data);
		System.out.println("\nChi-Squared of y=x^2: "+x2);

		//Creates new 'Theory' object to set n = 4, then calculates and prints chi-squared statistic
		Theory x_4 = new Theory(4);
		double x4 = goodnessOfFit(x_4, data);
		System.out.println("Chi-Squared of y=x^4: "+x4);

		//Prints which function is better based on comparison of chi-squared statistics
		if(x4<x2) {
			System.out.println("\ny=x^4 better describes the experimental data");
		}
		else if(x2<x4) {
			System.out.println("\ny=x^2 better describes the experimental data");
		}

		System.out.println("\nBased on the comparison of the chi-squared statistic for both y=x^2\n"
				+ "and y=x^4, it is clear to see that y=x^2 describes the data far better due to its\n"
				+ "smaller chi squared statistic. This is because the chi-squared statistic calculates\n"
				+ "the residual between the experimental and theoretical y-coordinate. So the smaller\n"
				+ "the chi-squared, the better the function models the data.");
	}

}