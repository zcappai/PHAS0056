package final2017_18;

import java.util.*;

public class ExamPart3 {
	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Airports Data URL: "+one.URL1);
		System.out.println("Flights Data URL: "+one.URL2);

		one.print();

		//Direct Flights from LHR to CPT that last no more than 24 hours
		HashMap<Flights, Long> LHRtoCPTdirect = new HashMap<>();
		String origin = "LHR";
		String destin = "CPT";
		long maxTime = 24*60;
		for(Map.Entry<Flights, Long> x : one.flightDuration.entrySet()) {
			if(x.getKey().origin_code.equals(origin) && x.getKey().destin_code.equals(destin) && x.getValue() < maxTime) {
				LHRtoCPTdirect.put(x.getKey(), x.getValue());
			}
		}
		System.out.println("\n<All Direct Flights From LHR to CPT That Last No Longer Than 24 Hours>");
		for(Map.Entry<Flights, Long> x : LHRtoCPTdirect.entrySet()) {
			System.out.println("\nFlight Code: "+x.getKey().flight_code+", Origin Airport: "+x.getKey().origin_code+", Destination Airport: "+x.getKey().destin_code);
			System.out.println("Departure:- Date: "+x.getKey().depart_date+", Time: "+x.getKey().depart_time);
			System.out.println("Arrival:- Date: "+x.getKey().arrive_date+", Time: "+x.getKey().arrive_time);
			System.out.println("Duration: "+x.getValue()+" minutes");
			System.out.println("Cost: £"+x.getKey().cost);
		}

		//Two-Flight Journeys from LHR to CPT that last no more than 24 hours
		HashMap<Flights, Long> LHRtoAnon = new HashMap<>();
		HashMap<Flights, Long> AnontoCPT = new HashMap<>();
		for(Map.Entry<Flights, Long> x : one.flightDuration.entrySet()) {
			if(x.getKey().origin_code.equals("LHR") && !x.getKey().destin_code.equals("CPT")) {
				LHRtoAnon.put(x.getKey(), x.getValue());
			}
			if(!x.getKey().origin_code.equals("LHR") && x.getKey().destin_code.equals("CPT")) {
				AnontoCPT.put(x.getKey(), x.getValue());
			}
		}
		Flights firstFlight = null;
		Flights secondFlight = null;
		double totalCost = 0;
		for(Map.Entry<Flights, Long> x : LHRtoAnon.entrySet()) {
			for(Map.Entry<Flights, Long> y : AnontoCPT.entrySet()) {
				if(x.getKey().destin_code.equals(y.getKey().origin_code)) {
					if((x.getValue()+y.getValue())<maxTime && totalCost == 0 || (x.getKey().cost+y.getKey().cost)<totalCost) {
						firstFlight = x.getKey();
						secondFlight = y.getKey();
						totalCost = x.getKey().cost+x.getKey().cost;
					}
				}
			}
		}
		System.out.println("\n<Cheapest Flight from LHR to CPT for less than 24 Hours>");
		System.out.println("\nFrom: "+firstFlight.origin_code+" to "+firstFlight.destin_code+" to "+secondFlight.destin_code);
		System.out.println("1st Flight Code: "+firstFlight.flight_code);
		System.out.println("2nd Flight Code: "+secondFlight.flight_code);
		System.out.println("Total Cost: £"+totalCost);
	}
}