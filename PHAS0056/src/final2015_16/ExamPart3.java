package final2015_16;

import java.util.*;

public class ExamPart3 {

	protected HashMap<String, Double> method1 = new HashMap<>();
	protected HashMap<String, Double> method2 = new HashMap<>();

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		ExamPart3 three = new ExamPart3();
		System.out.println("Detector Data URL: "+one.URL1);
		System.out.println("Signal Data URL: "+one.URL2);

		try {
			one.readDetectorData(one.URL1);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			one.readSignalData(one.URL2);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}

		//Method 1
		for(Detectors x : one.detectorList) {
			ArrayList<ArrayList<Double>> volts = new ArrayList<>();
			for(Signals y : one.signalList) {
				if(x.ID.equals(y.ID)) {
					volts.add(y.volt);
				}
			}
			int tot_arrival_time = 0;
			for(ArrayList<Double> y : volts) {
				double max_amp = 0;
				for(Double z : y) {
					if(max_amp == 0) {
						max_amp = z;
					}
					else if(max_amp < z) {
						max_amp = z;
					}
				}
				int max_index = y.indexOf(max_amp);
				tot_arrival_time += max_index;
			}
			double avg_arriv_det = tot_arrival_time/volts.size();
			three.method1.put(x.ID, avg_arriv_det);
		}

		//Method 2
		ArrivalTime arriv2 = new givenThreshold();
		for(Detectors x : one.detectorList) {
			double tot_time = 0;
			double count = 0;
			for(Signals y : one.signalList) {
				if(x.ID.equals(y.ID)) {
					double max_volt_time = arriv2.calculate(y.volt, 1.0);
					tot_time += max_volt_time;
					count++;
				}
			}
			double avg_time = tot_time/count;
			three.method2.put(x.ID, avg_time);
		}

		String detector = "";
		double difference = 0;
		for(Map.Entry<String, Double> x : three.method1.entrySet()) {
			for(Map.Entry<String, Double> y : three.method2.entrySet()) {
				if(y.getKey().equals(x.getKey())) {
					if(difference == 0) {
						detector = x.getKey();
						difference = Math.abs(x.getValue()-y.getValue());
					}
					else if(difference < Math.abs(x.getValue()-y.getValue())) {
						detector = x.getKey();
						difference = Math.abs(x.getValue()-y.getValue());
					}
				}
			}
		}
		System.out.println("\n<Detector With Greatest Difference In Arrival Times From 2 Methods>");
		System.out.println("Detector: "+detector);
		System.out.println("Absolute Difference in Arrival Time: "+difference+" ns");
	}
}