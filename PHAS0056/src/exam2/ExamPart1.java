package exam2;

import java.io.*;
import java.net.*;
import java.util.*;

public class ExamPart1 {

	String URL1 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2019-20/stations.txt";
	String URL2 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2019-20/countries.txt";
	String URL3 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2019-20/readings.txt";
	ArrayList<Stations> stationList = new ArrayList<Stations>();
	ArrayList<Countries> countryList = new ArrayList<Countries>();
	ArrayList<Readings> readingList = new ArrayList<Readings>();

	//ExamPart1 constructor imports data into ArrayLists
	public ExamPart1() {
		try {
			readStationsData(URL1, stationList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readCountriesData(URL2, countryList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readReadingsData(URL3, readingList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
	}

	//TAKES URL AS ARGUMENT AND RETURNS 'BufferedReader' OBJECT
	public static BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	public void readStationsData(String URL, ArrayList<Stations> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Stations x = new Stations(line);
			list.add(x);
		}
	}

	public void readCountriesData(String URL, ArrayList<Countries> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Countries x = new Countries(line);
			list.add(x);
		}
	}

	public void readReadingsData(String URL, ArrayList<Readings> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Readings x = new Readings(line);
			list.add(x);
		}
	}

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Stations Data URL: "+one.URL1);
		System.out.println("Countries Data URL: "+one.URL2);
		System.out.println("Readings Data URL: "+one.URL3);

		System.out.println("\nTotal Number of Weather Stations: "+one.readingList.size());

		int countTMAX = 0;
		for(Readings x : one.readingList) {
			if(x.type.equals("TMAX")) {
				for(Integer y : x.readings) {
					if(y != -9999) {
						countTMAX++;
					}
				}
			}
		}
		System.out.println("\nTotal Number of Valid TMAX Measurements: "+countTMAX);

		int countTMIN = 0;
		for(Readings x : one.readingList) {
			if(x.type.equals("TMIN")) {
				for(Integer y : x.readings) {
					if(y != -9999) {
						countTMIN++;
					}
				}
			}
		}
		System.out.println("\nTotal Number of Valid TMIN Measurements: "+countTMIN);

		int countPRCP = 0;
		for(Readings x : one.readingList) {
			if(x.type.equals("PRCP")) {
				for(Integer y : x.readings) {
					if(y != -9999) {
						countPRCP++;
					}
				}
			}
		}
		System.out.println("\nTotal Number of Valid PRCP Measurements: "+countPRCP);

		int countSNWD = 0;
		for(Readings x : one.readingList) {
			if(x.type.equals("SNWD")) {
				for(Integer y : x.readings) {
					if(y != -9999) {
						countSNWD++;
					}
				}
			}
		}
		System.out.println("\nTotal Number of Valid SNWD Measurements: "+countSNWD);

		ArrayList<Readings> TMAX = new ArrayList<Readings>();
		for(Readings x : one.readingList) {
			if(x.type.equals("TMAX")) {
				TMAX.add(x);
			}
		}
		ArrayList<Readings> TMIN = new ArrayList<Readings>();
		for(Readings x : one.readingList) {
			if(x.type.equals("TMIN")) {
				TMIN.add(x);
			}
		}
		int late_year = 0;
		int late_month = 0;
		for(Readings x : TMAX) {
			for(Integer y : x.readings) {
				if(y != -9999) {
					late_year = x.year;
					late_month = x.month;
					break;
				}
			}
		}
		System.out.println("\nLatest Date With Atleast 1 Valid TMAX Measurement: "+late_month+"/"+late_year);

		//		int early_year = 0;
		//		int early_month = 0;
		//		for(Readings x : TMAX) {
		//			for(Integer y : x.readings) {
		//				if(y != -9999) {
		//					early_year = x.year;
		//					early_month = x.month;
		//					break;
		//				}
		//			}
		//		}

		//HIGHEST TMAX VALUE
		double highestTMAX = 0;
		String highestStationTMAX = "";
		int highestYearTMAX = 0;
		int highestMonthTMAX = 0;
		String highestRegionTMAX = "";
		String highestStationIDTMAX = "";
		for(Readings x : TMAX) {
			for(Integer y : x.readings) {
				if(highestTMAX < y && y != -9999) {
					highestTMAX = y;
					highestYearTMAX = x.year;
					highestMonthTMAX = x.month;
					highestStationIDTMAX = x.stat_ID;
				}
			}
		}
		for(Countries x : one.countryList) {
			if(highestStationIDTMAX.contains(x.reg_ID)) {
				highestRegionTMAX = x.reg_name;
			}
		}
		for(Stations x : one.stationList) {
			if(highestStationIDTMAX.equals(x.stat_ID)) {
				highestStationTMAX = x.stat_name;
			}
		}
		System.out.println("\n<Highest TMAX Value>");
		System.out.println("Station ID: "+highestStationIDTMAX);
		System.out.println("Station Name: "+highestStationTMAX+", Region Name: "+highestRegionTMAX+", Date: "+highestMonthTMAX+"/"+highestYearTMAX+", TMAX Value: "+highestTMAX+", Temperature (Celsius): "+(highestTMAX/10));

		//LOWEST TMAX VALUE
		double lowestTMAX = 0;
		String lowestStationTMAX = "";
		int lowestYearTMAX = 0;
		int lowestMonthTMAX = 0;
		String lowestRegionTMAX = "";
		String lowestStationIDTMAX = "";
		for(Readings x : TMAX) {
			for(Integer y : x.readings) {
				if(lowestTMAX > y && y != -9999) {
					lowestTMAX = y;
					lowestYearTMAX = x.year;
					lowestMonthTMAX = x.month;
					lowestStationIDTMAX = x.stat_ID;
				}
			}
		}
		for(Countries x : one.countryList) {
			if(lowestStationIDTMAX.contains(x.reg_ID)) {
				lowestRegionTMAX = x.reg_name;
			}
		}
		for(Stations x : one.stationList) {
			if(lowestStationIDTMAX.equals(x.stat_ID)) {
				lowestStationTMAX = x.stat_name;
			}
		}
		System.out.println("\n<Lowest TMAX Value>");
		System.out.println("Station ID: "+lowestStationIDTMAX);
		System.out.println("Station Name: "+lowestStationTMAX+", Region Name: "+lowestRegionTMAX+", Date: "+lowestMonthTMAX+"/"+lowestYearTMAX+", TMIN Value: "+lowestTMAX+", Temperature (Celsius): "+(lowestTMAX/10));

		//HIGHEST TMIN VALUE
		double highestTMIN = 0;
		String highestStationTMIN = "";
		int highestYearTMIN = 0;
		int highestMonthTMIN = 0;
		String highestRegionTMIN = "";
		String highestStationIDTMIN = "";
		for(Readings x : TMIN) {
			for(Integer y : x.readings) {
				if(highestTMIN < y && y != -9999) {
					highestTMIN = y;
					highestYearTMIN = x.year;
					highestMonthTMIN = x.month;
					highestStationIDTMIN = x.stat_ID;
				}
			}
		}
		for(Countries x : one.countryList) {
			if(highestStationIDTMIN.contains(x.reg_ID)) {
				highestRegionTMIN = x.reg_name;
			}
		}
		for(Stations x : one.stationList) {
			if(highestStationIDTMIN.equals(x.stat_ID)) {
				highestStationTMIN = x.stat_name;
			}
		}
		System.out.println("\n<Highest TMIN Value>");
		System.out.println("Station ID: "+highestStationIDTMIN);
		System.out.println("Station Name: "+highestStationTMIN+", Region Name: "+highestRegionTMIN+", Date: "+highestMonthTMIN+"/"+highestYearTMIN+", TMIN Value: "+highestTMIN+", Temperature (Celsius): "+(highestTMIN/10));

		//LOWEST TMIN VALUE
		double lowestTMIN = 0;
		String lowestStationTMIN = "";
		int lowestYearTMIN = 0;
		int lowestMonthTMIN = 0;
		String lowestRegionTMIN = "";
		String lowestStationIDTMIN = "";
		for(Readings x : TMIN) {
			for(Integer y : x.readings) {
				if(lowestTMIN > y && y != -9999) {
					lowestTMIN = y;
					lowestYearTMIN = x.year;
					lowestMonthTMIN = x.month;
					lowestStationIDTMIN = x.stat_ID;
				}
			}
		}
		for(Countries x : one.countryList) {
			if(lowestStationIDTMIN.contains(x.reg_ID)) {
				lowestRegionTMIN = x.reg_name;
			}
		}
		for(Stations x : one.stationList) {
			if(lowestStationIDTMIN.equals(x.stat_ID)) {
				lowestStationTMIN = x.stat_name;
			}
		}
		System.out.println("\n<Lowest TMIN Value>");
		System.out.println("Station ID: "+lowestStationIDTMIN);
		System.out.println("Station Name: "+lowestStationTMIN+", Region Name: "+lowestRegionTMIN+", Date: "+lowestMonthTMIN+"/"+lowestYearTMIN+", TMIN Value: "+lowestTMIN+", Temperature (Celsius): "+(lowestTMIN/10));
	}
}