package final2017_18;

import java.util.*;

public final class Flights {

	final String flight_code;
	final String origin_code;
	final String destin_code;
	final String depart_date;
	final String depart_time;
	final String arrive_date;
	final String arrive_time;
	final double cost;

	public Flights(String line) {
		try(Scanner sc = new Scanner(line)) {
			sc.useDelimiter(", ");
			this.flight_code = sc.next();
			this.origin_code = sc.next();
			this.destin_code = sc.next();
			this.depart_date = sc.next();
			this.depart_time = sc.next();
			this.arrive_date = sc.next();
			this.arrive_time = sc.next();
			this.cost = sc.nextDouble();
		}
	}
}