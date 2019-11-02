package module5;
import java.io.*;
import java.util.*;
import java.net.*;

public class DataAnalysis {

	public static ArrayList<DataPoint> dataFromURL(String url) throws Exception{
		ArrayList<DataPoint> data = new ArrayList<DataPoint>();
		URL u = new URL(url);
		InputStream is = u.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);
		String line = "";
		while ((line = reader.readLine()) != null) {
			Scanner sc = new Scanner(line);
			while(sc.hasNext()) {
				double xtoken = Double.parseDouble(sc.next());
				double ytoken = Double.parseDouble(sc.next());
				double eytoken = Double.parseDouble(sc.next());
				DataPoint p = new DataPoint(xtoken, ytoken, eytoken);
				data.add(p);
			}
			sc.close();
		}
		return data;
	}

	public static double goodnessOfFit(Theory theo, ArrayList<DataPoint> data) {
		double chi2 = 0;
		for(int i = 0; i<data.size(); i++) {
			DataPoint point = data.get(i);
			double x_coord = point.getX();
			double y_coord = point.getY();
			double y_error = point.getEY();
			double y_theo = theo.y(x_coord);
			chi2 += Math.pow((y_coord-y_theo), 2)/(y_error*y_error);
		}
		return chi2;
	}

	public static void main(String[] args) {
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-xy.txt";
		ArrayList<DataPoint> data = null;
		try {
			data = dataFromURL(url);
			System.out.println("URL: "+url);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		Theory x_2 = new Theory(2);
		double x2 = goodnessOfFit(x_2, data);
		System.out.println("\nChi-Squared of y=x^2: "+x2);

		Theory x_4 = new Theory(4);
		double x4 = goodnessOfFit(x_4, data);
		System.out.println("Chi-Squared of y=x^4: "+x4);
		
		System.out.println("\nEXPLANATION!");
	}

}