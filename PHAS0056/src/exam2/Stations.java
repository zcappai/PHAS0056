package exam2;

import java.util.*;

public class Stations {

	final String stat_ID;
	final String stat_name;

	public Stations(String line) {
		try(Scanner sc = new Scanner(line)) {
			StringBuilder str = new StringBuilder(); //To Construct Station Name
			this.stat_ID = sc.next();
			while(sc.hasNext()) {
				str.append(sc.next());
				str.append(" ");
			}
			this.stat_name = str.toString();
		}
	}
}