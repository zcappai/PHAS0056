package final2018_19;

import java.util.*;

public class Location {

	final int imgID;
	final double lat;
	final double lon;

	public Location(String line) {
		try(Scanner sc = new Scanner(line)) {
			this.imgID = sc.nextInt();
			this.lat = sc.nextDouble();
			this.lon = sc.nextDouble();
		}
	}
}