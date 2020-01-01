package final2016_17;

import java.util.*;

public class Index {

	final String filename;
	final String instrument;

	public Index(String line) {
		try(Scanner sc = new Scanner(line)) {
			this.filename = sc.next();
			this.instrument = sc.next();
		}
	}
}