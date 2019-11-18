package module6;

import java.io.*;
import java.net.*;
import java.util.*;

public class TestDataPoints {

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
				DataPoint p = null;
				if(sc.hasNext()) {
					String label = sc.next();
					p = new LabelledDataPoint(label, xtoken, ytoken, eytoken);
				}
				else {
					p = new DataPoint(xtoken, ytoken, eytoken);
				}
				data.add(p);
			}
			sc.close(); //Closing resource to clean memory
		}
		return data;
	}

	public static void main(String[] args) {
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt";
		ArrayList<DataPoint> data = null;

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
			System.out.println(e);
		}

		System.out.println("<Data Points>");
		for(DataPoint x : data) {
			System.out.println(x+System.lineSeparator());
		}
	}
}
