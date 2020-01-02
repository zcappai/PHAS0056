package final2015_16;

import java.util.*;

public class Signals {

	String ID;
	ArrayList<Double> volt = new ArrayList<>();;

	public Signals(String line) {
		try(Scanner sc = new Scanner(line)) {
			this.ID = sc.next();
			while(sc.hasNext()) {
				volt.add(sc.nextDouble());
			}
		}
	}
}