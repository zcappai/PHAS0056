package final2016_17;

public class ExamPart2 {

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Index Data URL: "+one.URL1);
		System.out.println("Recording 1 Data URL: "+one.URL2);
		System.out.println("Recording 2 Data URL: "+one.URL3);
		System.out.println("Recording 3 Data URL: "+one.URL4);
		System.out.println("Recording 4 Data URL: "+one.URL5);

		System.out.println("\n<Classification: Long or Short>");
		Identity classification1 = new longShort();
		for(Object[] x : one.recordings) {
			String longOrShort1 = classification1.classify(x);
			System.out.println("File Name: "+x[0]+", Classification: "+longOrShort1+"\n");
		}

		System.out.println("\n<Classification: Loud or Quiet>");
		Identity classification2 = new loudQuiet();
		for(Object[] x : one.recordings) {
			String longOrShort2 = classification2.classify(x);
			System.out.println("File Name: "+x[0]+", Classification: "+longOrShort2+"\n");
		}
	}
}