package final2016_17;

public class ExamPart3 {
	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Index Data URL: "+one.URL1);
		System.out.println("Recording 1 Data URL: "+one.URL2);
		System.out.println("Recording 2 Data URL: "+one.URL3);
		System.out.println("Recording 3 Data URL: "+one.URL4);
		System.out.println("Recording 4 Data URL: "+one.URL5);

		System.out.println("\n<Classification: High, Medium or Low>");
		Identity classification1 = new LMH();
		for(Object[] x : one.recordings) {
			String LMH = classification1.classify(x);
			System.out.println("File Name: "+x[0]+", Classification: "+LMH+"\n");
		}
	}
}