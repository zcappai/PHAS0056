package final2018_19;

import java.util.*;

public class ExamPart2 {
	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Classification Data URL: "+one.URL1);
		System.out.println("Location Data URL: "+one.URL2);
		System.out.println("Expert Data URL: "+one.URL3);

		//All Lions Identified By Experts
		ImageSelector im1 = new GivenSpecies();
		ArrayList<Classification> lion = new ArrayList<>();
		lion = im1.select(one.expList, "lion");
		System.out.println("\n<Images Identified To Contain Lions By Experts>");
		for(Classification x : lion) {
			Integer key = x.imgID;
			for(Location y : one.locList) {
				if(key.equals(y.imgID)) {
					System.out.println("Image Identifier: "+x.imgID+", Species: "+x.species+", Latitude: "+y.lat+", Longitude: "+y.lon);
				}
			}
		}

		//All Lion Candidates By Volunteers
		ImageSelector im2 = new PossibleSpecies();
		ArrayList<Classification> possibleLion = new ArrayList<>();
		possibleLion = im2.select(one.classList, "lion");
		HashMap<Integer, Location> candidateLion = new HashMap<>();
		System.out.println("\n<Images Classified As Lion Candidates>");
		for(Classification x : possibleLion) {
			Integer key = x.imgID;
			for(Location y : one.locList) {
				if(key.equals(y.imgID)) {
					candidateLion.put(key, y);
				}
			}
		}
		for(Map.Entry<Integer, Location> x : candidateLion.entrySet()) {
			Location cand = x.getValue();
			System.out.println("Image Identifier: "+cand.imgID+", Latitude: "+cand.lat+", Longitude: "+cand.lon);
		}
	}
}
