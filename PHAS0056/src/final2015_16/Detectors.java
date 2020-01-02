package final2015_16;

import java.util.*;

public class Detectors {

	String ID;
	double distance;

	public Detectors(String line) {
		try(Scanner sc = new Scanner(line)) {
			this.ID = sc.next();
			this.distance = sc.nextDouble();
		}
	}
}