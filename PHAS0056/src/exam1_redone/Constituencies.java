package exam1_redone;

import java.util.*;

public class Constituencies {

	final String ID;
	final String constit;
	final String region;
	final double electorate;

	public Constituencies(String line) {
		try(Scanner sc = new Scanner(line)) {
			sc.useDelimiter(",");
			this.ID = sc.next();
			this.constit = sc.next();
			this.region = sc.next();
			this.electorate = sc.nextDouble();
		}
	}
}