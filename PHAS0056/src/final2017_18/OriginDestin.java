package final2017_18;

import java.util.*;

public class OriginDestin implements FlightFilter{

	public HashMap<Flights, Long> filter(HashMap<Flights, Long> flightList, String origin, String destin, Long duration, Double cost) {
		HashMap<Flights, Long> chosenJourneys = new HashMap<>();
		for(Map.Entry<Flights, Long> x : flightList.entrySet()) {
			String a = x.getKey().origin_code;
			String b = x.getKey().destin_code;
			if(origin.equals(a) && destin.equals(b)) {
				chosenJourneys.put(x.getKey(), x.getValue());
			}
		}
		return chosenJourneys;
	}
}