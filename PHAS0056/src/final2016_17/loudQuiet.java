package final2016_17;

import java.util.*;

public class loudQuiet implements Identity{

	public String classify(Object[] input) {
		String classification = "";
		ArrayList<Long> amps = (ArrayList<Long>) input[3];
		double a_rms = 0;
		for(Long y : amps) {
			a_rms+=(Math.pow(y, 2));
		}
		a_rms = a_rms/amps.size();
		a_rms = Math.sqrt(a_rms);
		RecordInfo info = (RecordInfo) input[2];
		double amplitude = 20*Math.log10(a_rms/info.max_a);
		if(amplitude > -20.0) {
			classification = "loud";
		}
		else if(amplitude < -20.0) {
			classification = "quiet";
		}
		return classification;
	}
}