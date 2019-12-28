package final2018_19;

import java.io.*;
import java.net.*;
import java.util.*;

public class ExamPart1 {

	static final ArrayList<Classification> classList = new ArrayList<>();
	static final ArrayList<Location> locList = new ArrayList<>();
	static final ArrayList<Classification> expList = new ArrayList<>();

	//TAKES URL AS ARGUMENT AND RETURNS 'BufferedReader' OBJECT
	public static BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	public static void readClassificationData(String URL, ArrayList<Classification> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Classification x = new Classification(line);
			list.add(x);
		}
	}

	public static void readLocationData(String URL, ArrayList<Location> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Location x = new Location(line);
			list.add(x);
		}
	}

	public static void readExpertData(String URL, ArrayList<Classification> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Classification x = new Classification(line);
			list.add(x);
		}
	}

	public static void main(String[] args) {
		String URL1 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/classification.txt";
		String URL2 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/locations.txt";
		String URL3 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/expert.txt";
		System.out.println("Classification Data URL: "+URL1);
		System.out.println("Location Data URL: "+URL2);
		System.out.println("Expert Data URL: "+URL3);

		try {
			readClassificationData(URL1, classList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readLocationData(URL2, locList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readExpertData(URL3, expList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}

		//Total number of images taken
		System.out.println("\nTotal No. of Images: "+locList.size()+" images");

		//Number of images classified by at least 1 volunteer
		Collections.sort(classList, Classification.IDsort);
		ArrayList<Integer> sortedImgID = new ArrayList<Integer>();
		for(Classification x : classList) {
			sortedImgID.add(x.imgID);
		}
		System.out.println("\nNumber of Images Classified by At Least 1 Volunteer: "+sortedImgID.stream().distinct().count()+" images");

		//Details of images classified by at least 10 volunteers
		HashMap<Integer, Integer> repeatedDatabase = new HashMap<>();
		for (Classification x : classList){
			int s = x.imgID;
			Integer i = repeatedDatabase.get(s);
			if (i == null){
				repeatedDatabase.put(s, 1);
			}
			else {
				repeatedDatabase.put(s, i+1);
			}
		}
		for(Map.Entry<Integer, Integer> x : repeatedDatabase.entrySet()) {
			if(x.getValue() >= 10) {
				Integer key = x.getKey();
				System.out.println("\nImage ID: "+key);
				for(Location y : locList) {
					if(key.equals(y.imgID)) {
						System.out.println("Latitude: "+y.lat);
						System.out.println("Longitude: "+y.lon);
					}
				}
				for(Classification z : expList) {
					if(key.equals(z.imgID)) {
						System.out.println("Species By Expert: "+z.species);
					}
				}
				System.out.println("Species By Volunteers:");
				for(Classification a : classList) {
					if(key.equals(a.imgID)) {
						System.out.println(a.species);
					}
				}
			}
		}
	}
}