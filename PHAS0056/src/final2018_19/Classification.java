package final2018_19;

import java.util.*;

public class Classification {

	final int imgID;
	final int volID;
	final String species;

	public Classification(String line) {
		try(Scanner sc = new Scanner(line)) {
			this.imgID = sc.nextInt();
			this.volID = sc.nextInt();
			this.species = sc.next();
		}
	}

	public static Comparator<Classification> IDsort = new Comparator<Classification>() {
		public int compare(Classification s1, Classification s2) {

			int rollno1 = s1.imgID;
			int rollno2 = s2.imgID;

			/*For ascending order*/
			return rollno1-rollno2;

			/*For descending order*/
			//rollno2-rollno1;
		}
	};
}