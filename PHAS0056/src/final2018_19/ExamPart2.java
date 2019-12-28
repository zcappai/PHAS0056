package final2018_19;

import java.util.*;

public class ExamPart2 {

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
		System.out.println("\n<Images Identified To Contain Lions By Experts>");
		for(Classification x : lion) {
			Integer key = x.imgID;
			for(Location y : locList) {
				if(key.equals(y.imgID)) {
					System.out.println("Image Identifier: "+x.imgID+", Species: "+x.species+", Latitude: "+y.lat+", Longitude: "+y.lon);
				}
			}
		}

		//All Lion Candidates By Volunteers
		ImageSelector im2 = new PossibleSpecies();
		ArrayList<Classification> possibleLion = new ArrayList<>();
		possibleLion = im2.select(classList, "lion");
		HashMap<Integer, Location> candidateLion = new HashMap<>();
		System.out.println("\n<Images Classified As Lion Candidates>");
		for(Classification x : possibleLion) {
			Integer key = x.imgID;
			for(Location y : locList) {
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
