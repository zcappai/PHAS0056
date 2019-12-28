package final2017_18;

import java.util.*;

public class Airports {

	final String origin_code;
	final String name;
	final String zone;

	public Airports(String line) {
		try(Scanner sc = new Scanner(line)) {
			sc.useDelimiter(", ");
			this.origin_code = sc.next();
			this.name = sc.next();
			this.zone = sc.next();
		}
	}
}