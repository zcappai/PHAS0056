package final2018_19;

import java.io.*;
import java.net.*;
import java.util.*;

public class ExamPart1 {

	String URL1 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/classification.txt";
	String URL2 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/locations.txt";
	String URL3 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/2018-19/expert.txt";
	ArrayList<Classification> classList = new ArrayList<>();
	ArrayList<Location> locList = new ArrayList<>();
	ArrayList<Classification> expList = new ArrayList<>();

	//ExamPart1 constructor imports data into ArrayLists
	public ExamPart1() {
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
	}

	//TAKES URL AS ARGUMENT AND RETURNS 'BufferedReader' OBJECT
	public static BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	public void readClassificationData(String URL, ArrayList<Classification> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Classification x = new Classification(line);
			list.add(x);
		}
	}

	public void readLocationData(String URL, ArrayList<Location> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Location x = new Location(line);
			list.add(x);
		}
	}

	public void readExpertData(String URL, ArrayList<Classification> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data

		while ((line = br.readLine()) != null) {
			Classification x = new Classification(line);
			list.add(x);
		}
	}

	public static void main(String[] args) {
		ExamPart1 one = new ExamPart1();
		System.out.println("Classification Data URL: "+one.URL1);
		System.out.println("Location Data URL: "+one.URL2);
		System.out.println("Expert Data URL: "+one.URL3);

		//Total number of images taken
		System.out.println("\nTotal No. of Images: "+one.locList.size()+" images");

		//Number of images classified by at least 1 volunteer - USE THE METHOD FOR ATLEAST 10 VOLUNTEERS INSTEAD (MORE EFFICIENT)
		Collections.sort(one.classList, Classification.IDsort);
		ArrayList<Integer> sortedImgID = new ArrayList<Integer>();
		for(Classification x : one.classList) {
			sortedImgID.add(x.imgID);
		}
		System.out.println("\nNumber of Images Classified by At Least 1 Volunteer: "+sortedImgID.stream().distinct().count()+" images");

		//Details of images classified by at least 10 volunteers
		System.out.println("\n<Details of Images Classified By At Least 10 Volunteers>");
		HashMap<Integer, Integer> repeatedDatabase = new HashMap<>();
		for (Classification x : one.classList){
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
				for(Location y : one.locList) {
					if(key.equals(y.imgID)) {
						System.out.println("Latitude: "+y.lat);
						System.out.println("Longitude: "+y.lon);
					}
				}
				for(Classification z : one.expList) {
					if(key.equals(z.imgID)) {
						System.out.println("Species By Expert: "+z.species);
					}
				}
				System.out.println("Species By Volunteers:");
				for(Classification a : one.classList) {
					if(key.equals(a.imgID)) {
						System.out.println(a.species);
					}
				}
			}
		}
	}
}