package final2017_18;

import java.util.*;

public interface FlightFilter {

	public HashMap<Flights, Long> filter(HashMap<Flights, Long> flightList, String origin, String destin, Long duration, Double cost);
}