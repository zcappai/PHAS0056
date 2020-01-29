package exam2;

import java.util.*;

public class Readings {

	final String stat_ID;
	final int year;
	final int month;
	final String type;
	final ArrayList<Integer> readings = new ArrayList<Integer>();


	public Readings(String line) {
		try(Scanner sc = new Scanner(line)) {
			this.stat_ID = sc.next();
			this.year = sc.nextInt();
			this.month = sc.nextInt();
			this.type = sc.next();
			while(sc.hasNext()) {
				readings.add(sc.nextInt());
			}
		}
	}

}