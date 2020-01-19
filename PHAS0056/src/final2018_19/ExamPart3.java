package final2018_19;

import java.util.*;

public class ExamPart3 {
	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Classification Data URL: "+one.URL1);
		System.out.println("Location Data URL: "+one.URL2);
		System.out.println("Expert Data URL: "+one.URL3);

		//All Lions Identified By Experts
		ImageSelector im1 = new GivenSpecies();
		ArrayList<Classification> lion = new ArrayList<>();
		lion = im1.select(one.expList, "lion");
		ArrayList<Integer> lions = new ArrayList<>();
		for(Classification x : lion) {
			lions.add(x.imgID);
		}

		//All Lion Candidates By Volunteers - 50% Agree on Species
		ImageSelector im2 = new PossibleSpecies();
		ArrayList<Classification> possibleLion = new ArrayList<>();
		possibleLion = im2.select(one.classList, "lion");
		ArrayList<Integer> possibleLions = new ArrayList<>();
		for(Classification x : possibleLion) {
			possibleLions.add(x.imgID);
		}

		//Total Images
		double K = one.expList.size();

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
		double P = possibleLions.stream().distinct().count();

		//Negatives
		double N =  K - P;

		//False Positives
		double FP = P - TP;

		//False Negatives
		double FN = L - TP;

		//True Negatives
		double TN = N - FN;

		double MCC = (double) (TP*TN - FP*FN)/Math.sqrt((TP+FP)*(TP+FN)*(TN+FP)*(TN*FN));
		System.out.println("\n<For Images Identified As Lion Candidates By More Than 50% of Volunteers>");
		System.out.println("Matthews Correlation Coefficient (MCC) for Lions: "+MCC);

		//All Lion Candidates By Volunteers - 100% Agree on Species
		ImageSelector im3 = new PossibleSpecies2();
		ArrayList<Classification> possibleLion2 = new ArrayList<>();
		possibleLion2 = im3.select(one.classList, "lion");
		ArrayList<Integer> possibleLions2 = new ArrayList<>();
		for(Classification x : possibleLion2) {
			possibleLions2.add(x.imgID);
		}

		//Total Images
		double K2 = one.expList.size();

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
		double P2 = possibleLions2.stream().distinct().count();

		//Negatives
		double N2 =  K2 - P2;

		//False Positives
		double FP2 = P2 - TP2;

		//False Negatives
		double FN2 = L2 - TP2;

		//True Negatives
		double TN2 = N2 - FN2;

		double MCC2 = (double) (TP2*TN2 - FP2*FN2)/Math.sqrt((TP2+FP2)*(TP2+FN2)*(TN2+FP2)*(TN2*FN2));
		System.out.println("\n<For Images Identified As Lion Candidates By 100% of Volunteers>");
		System.out.println("Matthews Correlation Coefficient (MCC) for Lions: "+MCC2);
	}
}