package final2015_16;

import java.io.*;
import java.net.*;
import java.util.*;

public class ExamPart1 {

	protected String URL1 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2015-16/detectors.txt";
	protected String URL2 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2015-16/signals.txt";
	protected ArrayList<Detectors> detectorList = new ArrayList<>();
	protected ArrayList<Signals> signalList = new ArrayList<>();

	//TAKES URL AS ARGUMENT AND RETURNS 'BufferedReader' OBJECT
	public BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	public void readDetectorData(String URL)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Detectors x = new Detectors(line);
			detectorList.add(x);
		}
	}

	public void readSignalData(String URL)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Signals x = new Signals(line);
			signalList.add(x);
		}
	}

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Detector Data URL: "+one.URL1);
		System.out.println("Signal Data URL: "+one.URL2);

		try {
			one.readDetectorData(one.URL1);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			one.readSignalData(one.URL2);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}

		System.out.println("\nTotal Number of Pulses: "+one.signalList.size());

		//Printing - Part 1
		double total_amp = 0;
		for(Signals x : one.signalList) {
			double max_amp = 0;
			for(Double y : x.volt) {
				if(max_amp == 0) {
					max_amp = y;
				}
				else if(max_amp < y) {
					max_amp = y;
				}
			}
			total_amp += max_amp;
		}
		double mean_amp = total_amp/one.signalList.size();
		System.out.println("\nMean Amplitude of Pulses: "+mean_amp+" V");

		//Printing - Part 2
		for(Detectors x : one.detectorList) {
			ArrayList<ArrayList<Double>> volts = new ArrayList<>();
			for(Signals y : one.signalList) {
				if(x.ID.equals(y.ID)) {
					volts.add(y.volt);
				}
			}
			int n_signals = 0;
			for(ArrayList<Double> volt : volts) {
				n_signals+=volt.size();
			}
			System.out.println("\nDetector: "+x.ID);
			System.out.println("Number of Signals: "+n_signals);
			double tot_amp = 0;
			int tot_arrival_time = 0;
			for(ArrayList<Double> y : volts) {
				double max_amp = 0;
				for(Double z : y) {
					if(max_amp == 0) {
						max_amp = z;
					}
					else if(max_amp < z) {
						max_amp = z;
					}
				}
				int max_index = y.indexOf(max_amp);
				tot_amp += max_amp;
				tot_arrival_time += max_index;
			}
			double avg_amp_det = tot_amp/volts.size();
			double avg_arriv_det = tot_arrival_time/volts.size();
			System.out.println("Mean Amplitude of Pulses: "+avg_amp_det+" V");
			System.out.println("Mean Arrival Time of Pulses: "+avg_arriv_det+" ns");
			double speed = x.distance/(avg_arriv_det*Math.pow(10, -9));
			System.out.println("Speed of Particles: "+speed+" m/s");
		}
	}
}