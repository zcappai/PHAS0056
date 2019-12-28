package final2018_19;

import java.util.*;

public class ExamPart3 {

	static final ArrayList<Classification> classList = new ArrayList<>();
	static final ArrayList<Location> locList = new ArrayList<>();
	static final ArrayList<Classification> expList = new ArrayList<>();

	public static void main(String[] args) {
		String URL1 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/classification.txt";
		String URL2 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/locations.txt";
		String URL3 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/expert.txt";
		System.out.println("Classification Data URL: "+URL1);
		System.out.println("Location Data URL: "+URL2);
		System.out.println("Expert Data URL: "+URL3);

		try {
			ExamPart1.readClassificationData(URL1, classList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			ExamPart1.readLocationData(URL2, locList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			ExamPart1.readExpertData(URL3, expList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}

		//All Lions Identified By Experts
		ImageSelector im1 = new GivenSpecies();
		ArrayList<Classification> lion = new ArrayList<>();
		lion = im1.select(expList, "lion");
		ArrayList<Integer> lions = new ArrayList<>();
		for(Classification x : lion) {
			lions.add(x.imgID);
		}

		//All Lion Candidates By Volunteers - 50% Agree on Species
		ImageSelector im2 = new PossibleSpecies();
		ArrayList<Classification> possibleLion = new ArrayList<>();
		possibleLion = im2.select(classList, "lion");
		ArrayList<Integer> possibleLions = new ArrayList<>();
		for(Classification x : possibleLion) {
			possibleLions.add(x.imgID);
		}

		//Total Images
		double K = expList.size();

		//Total Lions
		double L = lion.size();

		//True Positives
		double TP = 0;
		for(Integer key : lions) {
			if(possibleLions.contains(key)) {
				TP++;
			}
		}

		//Positives
		double P = possibleLions.size();

		//Negatives
		double N =  K - P;

		//False Positives
		double FP = P - TP;

		//False Negatives
		double FN = L - TP;

		//True Negatives
		double TN = N - FN;

		double MCC = (double) (TP*TN - FP*FN)/Math.sqrt((TP+FP)*(TP+FN)*(TN+FP)*(TN*FN));
		System.out.println("\nMatthews Correlation Coefficient (MCC) for Lions: "+MCC);

		//All Lion Candidates By Volunteers - 100% Agree on Species
		ImageSelector im3 = new PossibleSpecies2();
		ArrayList<Classification> possibleLion2 = new ArrayList<>();
		possibleLion2 = im3.select(classList, "lion");
		ArrayList<Integer> possibleLions2 = new ArrayList<>();
		for(Classification x : possibleLion2) {
			possibleLions2.add(x.imgID);
		}

		//Total Images
		double K2 = expList.size();

		//Total Lions
		double L2 = lion.size();

		//True Positives
		double TP2 = 0;
		for(Integer key : lions) {
			if(possibleLions2.contains(key)) {
				TP2++;
			}
		}

		//Positives
		double P2 = possibleLions2.size();

		//Negatives
		double N2 =  K2 - P2;

		//False Positives
		double FP2 = P2 - TP2;

		//False Negatives
		double FN2 = L2 - TP2;

		//True Negatives
		double TN2 = N2 - FN2;

		double MCC2 = (double) (TP2*TN2 - FP2*FN2)/Math.sqrt((TP2+FP2)*(TP2+FN2)*(TN2+FP2)*(TN2*FN2));
		System.out.println("\nMatthews Correlation Coefficient (MCC) for Lions: "+MCC2);
	}
}