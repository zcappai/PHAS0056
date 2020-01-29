package exam2;

import java.util.*;

public class Mean implements StatisticCalculator{

	public HashMap<Integer, Double> calculate(ArrayList<Readings> list) {
		HashMap<Integer, Integer> years = new HashMap<Integer, Integer>();
		for(Readings x : list) {
			Integer i = years.get(x.year);
			if(i == null) {
				years.put(x.year, 1);
			}
			else {
				years.put(x.year, i+1);
			}
		}
		HashMap<Integer, Double> yearTemp = new HashMap<Integer, Double>();
		for(Map.Entry<Integer, Integer> x : years.entrySet()) {
			double totalYear = 0;
			double measureCount = 0;
			for(Readings y : list) {
				if(x.getKey().equals(y.year)) {
					for(Integer z : y.readings) {
						if(z != -9999) {
							totalYear += z;
							measureCount++;
						}
					}
				}
			}
			double meanForYear = totalYear/measureCount;
			yearTemp.put(x.getKey(), (meanForYear/10));
		}
		return yearTemp;
	}
}