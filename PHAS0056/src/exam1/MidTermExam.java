package exam1;

import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package for 'Scanner'

public class MidTermExam {

	/**
	 * IMPORTS DATA FROM URL AND PUTS INTO HashMap
	 * @param url of election data
	 * @return
	 * @throws Exception - IF INVALID URL USED, NO INPUT FROM URL OR NO INPUT WHEN READING LINE
	 */
	//TAKES URL AS ARGUMENT AND RETURNS 'BufferedReader' OBJECT
	public static BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	//IMPORTS ELECTION DATA INTO 'HashMap' OBJECT
	public static HashMap<Integer, MP> MPdataToHashMap(BufferedReader reader) throws Exception{
		HashMap<Integer, MP> mp = new HashMap<Integer, MP>();
		String line = ""; //Initialising empty string to store line data

		int counter = 0; //Initialising counter for skipping 1st line
		int key = 1; //Unique key assigned to each candidate

		/* While line isn't empty, uses 'Scanner' to look for token separated by comma.
		 * Unique key for 'HashMap' created, iterated and added separately*/
		while ((line = reader.readLine()) != null) {
			if(counter>0) { //Only analyses line after 1st line
				Scanner sc = new Scanner(line).useDelimiter(",");
				while(sc.hasNext()) {
					//Parsing tokens as integers or strings
					String ONS = sc.next();
					int PANO = Integer.parseInt(sc.next());
					String constit = sc.next();
					String lname = sc.next();
					String fname = sc.next();
					String party = sc.next();
					String partyID = sc.next();
					int votes = Integer.parseInt(sc.next());
					//Adding parsed data to 'MP' object
					MP p = new MP(ONS, PANO, constit, lname, fname, party, partyID, votes);
					mp.put(key, p); //Putting key and parsed election data data in 'HashMap'
					key++;
				}
				sc.close(); //Closing resource to clean memory
			}
			counter++; //Increases by 1 to skip line until condition above met
		}
		return mp; //Returns 'HashMap' with election data
	}

	//IMPORTS ELECTORATE DATA INTO 'HashMap' OBJECT
	public static HashMap<String, electorate> consdataToHashMap(BufferedReader reader) throws Exception{
		HashMap<String, electorate> cons = new HashMap<String, electorate>();
		String line = ""; //Initialising empty string to store line data

		int counter = 0; //Initialising counter for skipping 1st line

		/* While line isn't empty, uses 'Scanner' to look for token separated by comma.
		 * Unique key for 'HashMap' created, iterated and added separately*/
		while ((line = reader.readLine()) != null) {
			if(counter>0) { //Only analyses line after 1st line
				Scanner sc = new Scanner(line).useDelimiter(",");
				while(sc.hasNext()) {
					//Parsing tokens as integers or strings
					String constitID = sc.next();
					String constit = sc.next();
					String region = sc.next();
					Integer electorate = Integer.parseInt(sc.next());
					//Adding parsed data to 'electorate' object
					electorate p = new electorate(constit, region, electorate);
					cons.put(constitID, p); //Putting key and parsed electorate data in 'HashMap'
				}
				sc.close(); //Closing resource to clean memory
			}
			counter++; //Increases by 1 to skip line until condition above met
		}
		return cons; //Returns 'HashMap' with electorate data
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT TURNOUT FOR ELECTIONS
	 * @param x - 'HashMap' of election data
	 * @param y - 'HashMap' of electorate data
	 */
	public static double turnout(HashMap<Integer, MP> x, HashMap<String, electorate> y) {
		double totalvotes = 0; //Running total of votes
		double totalelectorate = 0;  //Running total of electorate

		//For each entry in election data, adds votes of each candidate to 'totalvotes'
		for(Map.Entry<Integer, MP> entry : x.entrySet()) {
			totalvotes += entry.getValue().votes;
		}

		//For each entry in electorate data, adds electorate of each constituency to 'totalelectorate'
		for(Map.Entry<String, electorate> entry : y.entrySet()) {
			totalelectorate += entry.getValue().electorate;
		}
		//Calculates total turnout
		double turnout = totalvotes/totalelectorate;
		return turnout*100; //Returns turnout as %
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT NUMBER OF MPs WHO LOST THEIR DEPOSITS
	 * @param x - 'HashMap' of election data
	 * @param Y - 'HashMap' of electorate data
	 */
	public static int lostDeposit(HashMap<Integer, MP> x, HashMap<String, electorate> y) {
		int total = 0;
		for(Map.Entry<String, electorate> entry2 : y.entrySet()) {
			for(Map.Entry<Integer, MP> entry1 : x.entrySet()) {
				if(entry1.getValue().ONS == entry2.getKey() & (entry1.getValue().votes/entry2.getValue().electorate) < 0.05) {
					total += 1;
				}
			}
		}
		return total;
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT MP WITH MOST VOTES
	 * @param x - 'HashMap' of election data
	 */
	public static void mostVotes(HashMap<Integer, MP> x) {
		Map.Entry<Integer, MP> mostV = null;
		for(Map.Entry<Integer, MP> entry : x.entrySet()) {
			if(mostV == null || entry.getValue().votes > mostV.getValue().votes) {
				mostV = entry;
			}
		}
		System.out.println("\n<Candidate With Most Votes>");
		System.out.println("Name: "+mostV.getValue().fname+mostV.getValue().lname);
		System.out.println("Party: "+mostV.getValue().party);
		System.out.println("Party Identified: "+mostV.getValue().partyID);
		System.out.println("Number of Votes: "+mostV.getValue().votes);
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT MP WITH LOWEST VOTES
	 * @param x - 'HashMap' of election data
	 */
	public static ArrayList<Integer> MPwithleastVotes(HashMap<Integer, MP> x, HashMap<String, electorate> y) {
		ArrayList<Integer> key = new ArrayList<Integer>();
		for(Map.Entry<String, electorate> entry1 : y.entrySet()) {
			for(Map.Entry<Integer, MP> entry2 : x.entrySet()) {
				while(entry1.getKey() == entry2.getValue().ONS & entry2.getValue().votes < x.get(key).votes) {
					key.add(entry2.getKey());
				}
			}
		}
		return key;
	}

	/**
	 * FINDS AND PRINTS DETAILS CONSTITUENCIES WITH LOWEST TURNOUTS
	 * @param x - 'HashMap' of sea ice data
	 * @param Y - 'HashMap' of electorate data
	 */
	public static void lowestTurnout(HashMap<Integer, MP> x, HashMap<String, electorate> y) {
		ArrayList<String> constits = new ArrayList<String>();
		ArrayList<Double> turnout = new ArrayList<Double>();
		double totalvotes;
		double totalelectorate;
		for(Map.Entry<String, electorate> entry2 : y.entrySet()) {
			totalelectorate = entry2.getValue().electorate;
			constits.add(entry2.getKey());
			totalvotes = 0;
			for(Map.Entry<Integer, MP> entry1 : x.entrySet()) {
				while(entry1.getValue().ONS.equalsIgnoreCase(entry2.getValue().constit)) {
					totalvotes += entry1.getValue().votes;
				}
				turnout.add(totalvotes/totalelectorate);
			}
		}
	}

	public static void main(String[] args) {
		//URL of election data to be analysed
		String url1 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/results.csv";
		System.out.println("URL of Election Data: "+url1);

		//URL of electorate data to be analysed
		String url2 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/constituencies.csv";
		System.out.println("URL of Constituency Data: "+url2);

		//Initalising 'BufferedReader' objects to buffer URL data
		BufferedReader bf1 = null;
		BufferedReader bf2 = null;

		//Creating empty 'HashMap's to be filled with election and electorate data respectively
		HashMap<Integer, MP> MPdata = null;
		HashMap<String, electorate> Constitdata = null;

		/** Tries to load election data into 'HashMap', then prints total number of candidates in election.
		 * Catches exception if invalid URL used, no input from URL or no input when reading line*/
		try {
			bf1 = brFromURL(url1);
			MPdata = MPdataToHashMap(bf1);
			System.out.println("\nTotal Number of Candidates: "+MPdata.size());
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/** Tries to load electorate data into 'HashMap'
		 * Catches exception if invalid URL used, no input from URL or no input when reading line*/
		try {
			bf2 = brFromURL(url2);
			Constitdata = consdataToHashMap(bf2);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		//Calculates and prints the voter turnout for election
		double voter_turnout = turnout(MPdata, Constitdata);
		System.out.println("Voter Turnout: "+voter_turnout+" %");

		//Calculates and prints the number of MPs who lost their deposit
		int lostDeposits = lostDeposit(MPdata, Constitdata);
		System.out.println("Number of MPs who lost their deposits: "+lostDeposits);

		//Calculates and prints details of MP with most votes
		mostVotes(MPdata);

		lowestTurnout(MPdata, Constitdata);
		System.out.println(MPwithleastVotes(MPdata, Constitdata));
	}

}
