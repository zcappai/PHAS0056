package module6;

import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package

//Creates method to import data from URL to 'ArrayList' and prints each data point as String
public class TestDataPoints {

	/**
	 * IMPORTS DATA FROM URL AND CONVERTS TO 'ArrayList' OF 'DataPoint' OBJECTS
	 * @param url of x, y and ey data
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
				double xtoken = sc.nextDouble();
				double ytoken = sc.nextDouble();
				double eytoken = sc.nextDouble();
				DataPoint p = null; //Creating empty 'DataPoint' object

				//If there is a next token (label), the data is put into 'LabelledDataPoint' object
				if(sc.hasNext()) {
					String label = sc.next();
					p = new LabelledDataPoint(label, xtoken, ytoken, eytoken);
				}
				//If there is no next token (label), the data is put into 'DataPoint' object
				else {
					p = new DataPoint(xtoken, ytoken, eytoken);
				}
				data.add(p); //'DataPoint' or 'LabelledDataPoint' added to 'ArrayList' object
			}
			sc.close(); //Closing resource to clean memory
		}
		return data; //Returns 'ArrayList' of 'DataPoint' objects
	}

	public static void main(String[] args) {
		//Initialising URL as string and creating empty 'ArrayList' of 'DataPoint' objects
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt";
		ArrayList<DataPoint> data = null; //Empty 'ArrayList' of 'DataPoint' objects

		/**
		 * Tries to import data from URL and return 'ArrayList' of 'DataPoint' objects
		 * Catches exception if invalid URL used, no input from URL or no input when
		 * reading line
		 */
		try {
			data = dataFromURL(url);
			System.out.println("URL: "+url+"\n");
		}
		catch(Exception e) {
			System.out.println(e+"\nPlease enter a valid URL!");
		}
		System.out.println("There are "+data.size()+" data points in the URL\n");

		//Only prints 'DataPoint' objects if data imported correctly
		if(data != null) {
			System.out.println("<Data Points From URL>");
			//Prints each 'DataPoint' object from 'ArrayList' as String
			for(DataPoint x : data) {
				System.out.println(x);
			}
		}
	}
}