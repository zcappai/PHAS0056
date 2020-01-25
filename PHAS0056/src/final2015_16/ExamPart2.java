package final2015_16;

public class ExamPart2 {

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Detector Data URL: "+one.URL1);
		System.out.println("Signal Data URL: "+one.URL2);

		//Time From Maximum Record Voltage For Each Pulse
		System.out.println("\n<Arrival Time For Each Pulse With Detector ID>");
		ArrivalTime arriv1 = new OriginalTime();
		for(Signals x : one.signalList) {
			double max_volt_time = arriv1.calculate(x.volt, 0.0);
			System.out.println("\nDetector: "+x.ID);
			System.out.println("Arrival Time: "+max_volt_time+" ns");
		}

		//Time Of 1st Voltage Above 1.0V
		System.out.println("\n<Arrival Time For 1st Voltage Per Pulse Above 1.0V With Detector ID>");
		ArrivalTime arriv2 = new givenThreshold();
		for(Detectors x : one.detectorList) {
			double tot_time = 0;
			double count = 0;
			System.out.println("\nDetector: "+x.ID);
			for(Signals y : one.signalList) {
				if(x.ID.equals(y.ID)) {
					double max_volt_time = arriv2.calculate(y.volt, 1.0);
					tot_time += max_volt_time;
					count++;
				}
			}
			double avg_time = tot_time/count;
			double speedThreshold = x.distance/(avg_time*Math.pow(10, -9));
			System.out.println("Mean Arrival Time of Pulses: "+avg_time+" ns");
			System.out.println("Speed of Particles With 1.0V Threshold: "+speedThreshold+" m/s");
		}
	}
}