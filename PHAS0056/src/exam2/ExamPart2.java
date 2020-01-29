package exam2;

import java.util.*;

public class ExamPart2 {

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();

		//CALCULATING MEAN VALUE OF TMAX PER YEAR
		Measurement mes1 = new TMAX();
		ArrayList<Readings> TMAX = new ArrayList<Readings>();
		TMAX = mes1.name(one.readingList);

		StatisticCalculator stat1 = new Mean();
		HashMap<Integer, Double> yearTemp = new HashMap<Integer, Double>();
		yearTemp = stat1.calculate(TMAX);

		System.out.println("<Mean Value of TMAX Per Year>");
		for(Map.Entry<Integer, Double> x : yearTemp.entrySet()) {
			System.out.println("Year: "+x.getKey()+", Temperature (Celsius): "+x.getValue());
		}

		HashMap<String, Integer> countries = new HashMap<String, Integer>();
		for(Countries x : one.countryList) {
			for(Readings y : TMAX) {
				if(y.stat_ID.contains(x.reg_ID)) {
					Integer i = countries.get(x.reg_ID);
					if(i == null) {
						countries.put(x.reg_ID, 1);
					}
					else {
						countries.put(x.reg_ID, i+1);
					}
				}
			}
		}
	}
}