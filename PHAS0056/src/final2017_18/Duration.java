package final2017_18;

import java.util.*;

public class Duration implements FlightFilter{

	public HashMap<Flights, Long> filter(HashMap<Flights, Long> flightList, String origin, String destin, Long duration, Double cost) {
		HashMap<Flights, Long> chosenJourneys = new HashMap<>();
		for(Map.Entry<Flights, Long> x : flightList.entrySet()) {
			long flightDura = x.getValue();
			if(flightDura <= duration) {
				chosenJourneys.put(x.getKey(), x.getValue());
			}
		}
		return chosenJourneys;
	}
}