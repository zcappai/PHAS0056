package final2015_16;

import java.util.*;

public class givenThreshold implements ArrivalTime{

	public double calculate(ArrayList<Double> signals, double threshold) {
		double max_amp = 0;
		for(Double y : signals) {
			if(max_amp == 0) {
				max_amp = y;
			}
			else if(max_amp < threshold) {
				max_amp = y;
			}
		}
		int max_index = signals.indexOf(max_amp);
		return max_index;
	}
}