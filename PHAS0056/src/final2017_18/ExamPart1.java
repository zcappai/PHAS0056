package final2017_18;

import java.io.*;
import java.net.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class ExamPart1 {

	protected String URL1 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2017-18/airports.txt";
	protected String URL2 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2017-18/flights.txt";
	protected ArrayList<Airports> airportList = new ArrayList<>();
	protected ArrayList<Flights> flightList = new ArrayList<>();
	protected HashMap<Flights, Long> flightDuration = new HashMap<>();

	//TAKES URL AS ARGUMENT AND RETURNS 'BufferedReader' OBJECT
	public BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	public void readAirportData(String URL)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Airports x = new Airports(line);
			airportList.add(x);
		}
	}

	public void readFlightData(String URL)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Flights x = new Flights(line);
			flightList.add(x);
		}
	}

	//Printing Details On Each Flight
	public void print() {
		for(Flights x : flightList) {
			String origin = x.origin_code;
			String destin = x.destin_code;
			String originZone = null;
			String destinZone = null;
			for(Airports y : airportList) {
				if(origin.equals(y.origin_code)) {
					originZone = y.zone;
				}
				if(destin.equals(y.origin_code)) {
					destinZone = y.zone;
				}
			}
			LocalDateTime lt1 = LocalDateTime.parse(x.depart_date+"T"+x.depart_time);
			ZoneId z1 = ZoneId.of(originZone);
			ZonedDateTime zt1 = ZonedDateTime.of(lt1, z1);

			LocalDateTime lt2 = LocalDateTime.parse(x.arrive_date+"T"+x.arrive_time);
			ZoneId z2 = ZoneId.of(destinZone);
			ZonedDateTime zt2 = ZonedDateTime.of(lt2, z2);
			long duration = zt1.until(zt2, ChronoUnit.MINUTES);
			flightDuration.put(x, duration);
		}
	}

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Airports Data URL: "+one.URL1);
		System.out.println("Flights Data URL: "+one.URL2);

		try {
			one.readAirportData(one.URL1);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			one.readFlightData(one.URL2);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}

		one.print();
		for(Map.Entry<Flights, Long> x : one.flightDuration.entrySet()) {
			System.out.println("\nFlight Code: "+x.getKey().flight_code);
			System.out.println("Departure:- Date: "+x.getKey().depart_date+", Time: "+x.getKey().depart_time);
			System.out.println("Arrival:- Date:	 "+x.getKey().arrive_date+", Time: "+x.getKey().arrive_time);
			System.out.println("Duration: "+x.getValue()+" minutes, Cost: £"+x.getKey().cost);
			String fullOrigin = null;
			String fullDestin = null;
			for(Airports y : one.airportList) {
				if(x.getKey().origin_code.equals(y.origin_code)) {
					fullOrigin = y.name;
				}
				if(x.getKey().destin_code.equals(y.origin_code)) {
					fullDestin = y.name;
				}
			}
			System.out.println("Origin Airport: "+fullOrigin+", Time: "+fullDestin);
		}
	}
}