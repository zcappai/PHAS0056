package final2016_17;

import java.util.*;

public class RecordInfo {

	double freq;
	double n;
	double max_a;

	public RecordInfo(String line) {
		try(Scanner sc = new Scanner(line)) {
				this.freq = sc.nextDouble();
				this.n = sc.nextDouble();
				this.max_a = sc.nextLong();
		}
	}
}