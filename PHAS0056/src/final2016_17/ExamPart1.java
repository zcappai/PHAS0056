package final2016_17;

import java.io.*;
import java.net.*;
import java.util.*;

public class ExamPart1 {

	protected String URL1 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/index.txt";
	protected String URL2 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/recording01.txt";
	protected String URL3 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/recording02.txt";
	protected String URL4 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/recording03.txt";
	protected String URL5 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/recording04.txt";
	protected String URL6 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/genA.txt";
	protected String URL7 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/genB.txt";
	protected String URL8 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/genC.txt";
	protected ArrayList<Index> indexList = new ArrayList<>();
	protected ArrayList<Object[]> recordings = new ArrayList<>();

	public ExamPart1() {
		try {
			readIndexData(URL1);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readRecordingData(URL2);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readRecordingData(URL3);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readRecordingData(URL4);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readRecordingData(URL5);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readRecordingData(URL6);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readRecordingData(URL7);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readRecordingData(URL8);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
	}

	//TAKES URL AS ARGUMENT AND RETURNS 'BufferedReader' OBJECT
	public BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	public void readIndexData(String URL)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Index x = new Index(line);
			indexList.add(x);
		}
	}

	public void readRecordingData(String URL)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		Object[] recording = new Object[4];

		for(Index x : indexList) {
			if(URL.contains(x.filename)) {
				recording[0] = x.filename;
				recording[1] = x.instrument;
			}
		}
		ArrayList<Long> amps = new ArrayList<>();
		
		while ((line = br.readLine()) != null) {
			StringTokenizer s = new StringTokenizer(line);
			if(s.countTokens() > 1) {
				RecordInfo x = new RecordInfo(line);
				recording[2] = x;
			}
			else if(s.countTokens() == 1) {
				Scanner sc = new Scanner(line);
				amps.add(sc.nextLong());
				sc.close();
			}
		}
		recording[3] = amps;
		recordings.add(recording);
	}

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Index Data URL: "+one.URL1);
		System.out.println("Recording 1 Data URL: "+one.URL2);
		System.out.println("Recording 2 Data URL: "+one.URL3);
		System.out.println("Recording 3 Data URL: "+one.URL4);
		System.out.println("Recording 4 Data URL: "+one.URL5);
		System.out.println("Gen A Data URL: "+one.URL6);
		System.out.println("Gen B Data URL: "+one.URL7);
		System.out.println("Gen C Data URL: "+one.URL8);

		System.out.println("\n<Information Printing>");
		for(Object[] x : one.recordings) {
			RecordInfo recInfo = (RecordInfo) x[2];

			ArrayList<Long> amps = (ArrayList<Long>) x[3];
			double a_rms = 0;
			for(Long y : amps) {
				a_rms+=(Math.pow(y, 2));
			}
			a_rms = a_rms/amps.size();
			a_rms = Math.sqrt(a_rms);

			System.out.println("File Name: "+x[0]);
			System.out.println("Duration: "+(recInfo.n/recInfo.freq)+" seconds");
			System.out.println("Amplitude: "+(20*Math.log10(a_rms/recInfo.max_a)));
			System.out.println("Instrument: "+x[1]+"\n");
		}
	}
}