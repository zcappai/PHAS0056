package final2017_18;

import java.util.*;

public class ExamPart2 {
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

		//LHR to ATH for no more than £200
		FlightFilter filter1 = new OriginDestin();
		HashMap<Flights, Long> LHRtoATH200 = new HashMap<>();
		String origin = "LHR";
		String destin = "ATH";
		double cost = 200.0;
		LHRtoATH200 = filter1.filter(one.flightDuration, origin, destin, null, null);
		FlightFilter filter2 = new Cost();
		LHRtoATH200 = filter2.filter(LHRtoATH200, null, null, null, cost);
		System.out.println("\n<All Flights From LHR to ATH That Cost No More Than £200>");
		for(Map.Entry<Flights, Long> x : LHRtoATH200.entrySet()) {
			System.out.println("\nFlight Code: "+x.getKey().flight_code+", Origin Airport: "+x.getKey().origin_code+", Destination Airport: "+x.getKey().destin_code);
			System.out.println("Departure:- Date: "+x.getKey().depart_date+", Time: "+x.getKey().depart_time);
			System.out.println("Arrival:- Date: "+x.getKey().arrive_date+", Time: "+x.getKey().arrive_time);
			System.out.println("Duration: "+x.getValue()+" minutes");
			System.out.println("Cost: £"+x.getKey().cost);
		}

		//LHR to ATH for no longer than 4 hours
		FlightFilter filter3 = new Duration();
		HashMap<Flights, Long> LHRtoATH4Hours = new HashMap<>();
		long duration = 4*60;
		LHRtoATH4Hours = filter3.filter(one.flightDuration, null, null, duration, null);
		FlightFilter filter4 = new OriginDestin();
		LHRtoATH4Hours = filter4.filter(LHRtoATH4Hours, origin, destin, null, null);
		System.out.println("\n<All Flights From LHR to ATH That Last No Longer Than 4 Hours>");
		for(Map.Entry<Flights, Long> x : LHRtoATH4Hours.entrySet()) {
			System.out.println("\nFlight Code: "+x.getKey().flight_code+", Origin Airport: "+x.getKey().origin_code+", Destination Airport: "+x.getKey().destin_code);
			System.out.println("Departure:- Date: "+x.getKey().depart_date+", Time: "+x.getKey().depart_time);
			System.out.println("Arrival:- Date: "+x.getKey().arrive_date+", Time: "+x.getKey().arrive_time);
			System.out.println("Duration: "+x.getValue()+" minutes");
			System.out.println("Cost: £"+x.getKey().cost);
		}

		//Quickest from LHR to ATH for no more than £200
		Flights quickest = null;
		long quickestDuration = 0;
		for(Map.Entry<Flights, Long> x : LHRtoATH200.entrySet()) {
			if(quickestDuration == 0) {
				quickestDuration = x.getValue();
				quickest = x.getKey();
			}
			else if(quickestDuration > x.getValue()) {
				quickestDuration = x.getValue();
				quickest = x.getKey();
			}
		}
		System.out.println("\n<Quickest Flight From LHR to ATH That Cost No More Than £200>");
		System.out.println("\nFlight Code: "+quickest.flight_code+", Origin Airport: "+quickest.origin_code+", Destination Airport: "+quickest.destin_code);
		System.out.println("Departure:- Date: "+quickest.depart_date+", Time: "+quickest.depart_time);
		System.out.println("Arrival:- Date:	 "+quickest.arrive_date+", Time: "+quickest.arrive_time);
		System.out.println("Duration: "+quickestDuration+" minutes");
		System.out.println("Cost: £"+quickest.cost);
	}
}