package final2017_18;

import java.util.*;

public class Cost implements FlightFilter{

	public HashMap<Flights, Long> filter(HashMap<Flights, Long> flightList, String origin, String destin, Long duration, Double cost) {
		HashMap<Flights, Long> chosenJourneys = new HashMap<>();
		for(Map.Entry<Flights, Long> x : flightList.entrySet()) {
			double a = x.getKey().cost;
			if(a <= cost) {
				chosenJourneys.put(x.getKey(), x.getValue());
			}
		}
		return chosenJourneys;
	}
}