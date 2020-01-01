package final2016_17;

public class longShort implements Identity{

	public String classify(Object[] input) {
		String classification = "";
		RecordInfo info = (RecordInfo) input[2];
		double n = (double) info.n;
		double freq = (double) info.freq;
		double duration = n/freq;
		if(duration > 1.0) {
			classification = "long";
		}
		else if(!(duration > 1.0)) {
			classification = "short";
		}
		return classification;
	}
}
