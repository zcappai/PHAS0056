package exam1_redone;

import java.io.*;
import java.net.*;
import java.util.*;

public class MidTermExam {

	String URL1 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/constituencies.csv";
	String URL2 = "http://www.hep.ucl.ac.uk/undergrad/0056/exam-data/results.csv";
	ArrayList<Constituencies> constitList = new ArrayList<>();
	ArrayList<Results> resList = new ArrayList<>();

	//MidTermExam constructor imports data into ArrayLists
	public MidTermExam() {
		try {
			readConstituencyData(URL1, constitList);
		}
		catch(Exception e) {
			System.out.println("Invalid URL entered! Please enter valid URL!");
		}
		try {
			readResultsData(URL2, resList);
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

	//READ DATA FROM FILE CONTAINING CONSTITUENCY DATA
	public void readConstituencyData(String URL, ArrayList<Constituencies> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data
		int count = 0;

		while ((line = br.readLine()) != null) {
			if(count == 0) {
				count++;
			}
			else {
				Constituencies x = new Constituencies(line);
				list.add(x);
			}
		}
	}

	//READ DATA FROM FILE CONTAINING RESULTS DATA
	public void readResultsData(String URL, ArrayList<Results> list)  throws Exception{
		BufferedReader br = brFromURL(URL);
		String line = ""; //Initialising empty string to store line data
		int count = 0;

		while ((line = br.readLine()) != null) {
			if(count == 0) {
				count++;
			}
			else {
				Results x = new Results(line);
				list.add(x);
			}
		}
	}

	//IDENTIFIES AND RETURNS CONSTITUENCY WITH LOWEST TURNOUT
	public Map.Entry<String, Double> lowestTurnout(HashMap<String, Double> list) {
		Map.Entry<String, Double> lowest = null;
		for(Map.Entry<String, Double> x : list.entrySet()) {
			if(lowest == null) {
				lowest = x;
			}
			else if (lowest.getValue() > x.getValue()) {
				lowest = x;
			}
		}
		list.remove(lowest.getKey(), lowest.getValue());
		return lowest;
	}

	//IDENTIFIES AND RETURNS CONSTITUENCY WITH HIGHEST TURNOUT
	public Map.Entry<String, Double> highestTurnout(HashMap<String, Double> list) {
		Map.Entry<String, Double> highest = null;
		for(Map.Entry<String, Double> x : list.entrySet()) {
			if(highest == null) {
				highest = x;
			}
			else if (highest.getValue() < x.getValue()) {
				highest = x;
			}
		}
		list.remove(highest.getKey(), highest.getValue());
		return highest;
	}

	//IDENTIFIES AND RETURNS CONSTITUENCY WITH SMALLEST DIFFERENCE BETWEEN 1ST AND 2ND PLACE
	public Object[] closestRace(HashMap<String, Results> MPs, HashMap<String, Results> secondPlace) {
		double smallestDiff = 0;
		Map.Entry<String, Results> MP = null;
		Map.Entry<String, Results> second = null;
		for(Map.Entry<String, Results> x : MPs.entrySet()) {
			for(Map.Entry<String, Results> y : secondPlace.entrySet()) {
				if(x.getKey().equals(y.getKey())) {
					double diff_votes = x.getValue().votes - y.getValue().votes;
					if(smallestDiff == 0 || smallestDiff > diff_votes) {
						smallestDiff = diff_votes;
						MP = x;
						second = y;
					}
				}
			}
		}
		Object[] closest = new Object[] {MP.getValue(), second.getValue()};
		MPs.remove(MP.getKey(), MP.getValue());
		secondPlace.remove(second.getKey(), second.getValue());
		return closest;
	}

	public static void main(String[] args) {
		MidTermExam one = new MidTermExam();
		System.out.println("Constituency Data URL: "+one.URL1);
		System.out.println("Results Data URL: "+one.URL2);

		System.out.println("\nTotal Number of Candidates: "+one.resList.size()+" candidates");

		//OVERALL TURNOUT
		double total_votes = 0;
		double reg_voters = 0;
		for(Results x : one.resList) {
			total_votes += x.votes;
		}
		for(Constituencies x : one.constitList) {
			reg_voters += x.electorate;
		}
		double turnout = (total_votes/reg_voters)*100;
		System.out.println("\nTurnout: "+turnout+" %");

		//NUMBER OF CANDIDATES WHO LOST THEIR DEPOSITS
		ArrayList<Results> lost_dep = new ArrayList<>();
		for(Results x : one.resList) {
			for(Constituencies y : one.constitList) {
				if(x.ID.equals(y.ID)) {
					double percent = x.votes/y.electorate;
					if(percent < 0.05) {
						lost_dep.add(x);
					}
				}
			}
		}
		System.out.println("\nNumber of Candidates Who Lost Their Deposit: "+lost_dep.size()+" candidates");

		//CANDIDATE WITH MOST VOTES
		Results most_votes = null;
		for(Results x : one.resList) {
			if(most_votes == null || most_votes.votes < x.votes) {
				most_votes = x;
			}
		}
		System.out.println("\n<Candidate With Most Votes>");
		System.out.println(most_votes);

		//CANDIDATE WHO BECAME MP WITH THE LOWEST NUMBER OF VOTES
		HashMap<String, Results> MPs = new HashMap<>();
		for(Results x : one.resList) {
			String constitID = x.ID;
			Results candid = MPs.get(constitID);
			if(candid == null) {
				MPs.put(constitID, x);
			}
			else if(x.votes > candid.votes) {
				MPs.put(constitID, x);
			}
		}
		Results leastvoteMP = null; 
		for(Map.Entry<String, Results> x : MPs.entrySet()) {
			if(leastvoteMP == null) {
				leastvoteMP = x.getValue();
			}
			else if(leastvoteMP.votes > x.getValue().votes) {
				leastvoteMP = x.getValue();
			}
		}
		System.out.println("\n<MP With Least Votes>");
		System.out.println(leastvoteMP);

		//CANDIDATE WITH MOST VOTES WHO DID NOT BECOME AN MP
		HashMap<String, Results> secondPlace = new HashMap<>();
		for(Results x : one.resList) {
			String constitID = x.ID;
			Results candid = secondPlace.get(constitID);
			if(!MPs.containsValue(x)) {
				if(candid == null || x.votes > candid.votes) {
					secondPlace.put(constitID, x);
				}
			}
		}
		Results mostvotesNonMP = null; 
		for(Map.Entry<String, Results> x : secondPlace.entrySet()) {
			if(mostvotesNonMP == null) {
				mostvotesNonMP = x.getValue();
			}
			else if(mostvotesNonMP.votes < x.getValue().votes) {
				mostvotesNonMP = x.getValue();
			}
		}
		System.out.println("\n<Most Votes But Not MP>");
		System.out.println(mostvotesNonMP);

		//TURNOUT PER CONSTITUENCY
		HashMap<String, Double> turnouts = new HashMap<>();
		for(Constituencies x : one.constitList) {
			double electorate = x.electorate;
			double votes = 0;
			for(Results y : one.resList) {
				if(x.ID.equals(y.ID)) {
					votes += y.votes;
				}
			}
			double constit_turnout = votes/electorate;
			turnouts.put(x.ID, constit_turnout);
		}

		//5 CONSTITUENCIES WITH LOWEST OVERALL TURNOUT
		HashMap<String, Double> lowestTurnout = new HashMap<>();
		int count = 0;
		while (count < 5) {
			Map.Entry<String, Double> low = one.lowestTurnout(turnouts);
			lowestTurnout.put(low.getKey(), low.getValue());
			count++;
		}
		System.out.println("\n<Constituencies With Lowest Turnout>");
		for(Map.Entry<String, Double> x : lowestTurnout.entrySet()) {
			String constituencyID = x.getKey();
			for(Constituencies y : one.constitList) {
				if(y.ID.equals(constituencyID)) {
					System.out.println("\nConstituency: "+y.constit);
				}
			}
			for(Results y : one.resList) {
				if(constituencyID.equals(y.ID)) {
					System.out.println("Name: "+y.firstname+" "+y.surname+", Votes: "+y.votes);
				}
			}
			System.out.println("Percentage Voted: "+x.getValue()*100+" %");
			System.out.println("Percentage Did Not Vote: "+(1-x.getValue())*100+" %");
		}

		//5 CONSTITUENCIES WITH HIGHEST OVERALL TURNOUT
		HashMap<String, Double> highestTurnout = new HashMap<>();
		count = 0;
		while (count < 5) {
			Map.Entry<String, Double> high = one.highestTurnout(turnouts);
			highestTurnout.put(high.getKey(), high.getValue());
			count++;
		}
		System.out.println("\n<Constituencies With Highest Turnout>");
		for(Map.Entry<String, Double> x : highestTurnout.entrySet()) {
			String constituencyID = x.getKey();
			for(Constituencies y : one.constitList) {
				if(y.ID.equals(constituencyID)) {
					System.out.println("\nConstituency: "+y.constit);
				}
			}
			for(Results y : one.resList) {
				if(constituencyID.equals(y.ID)) {
					System.out.println("Name: "+y.firstname+" "+y.surname+", Votes: "+y.votes);
				}
			}
			System.out.println("Percentage Voted: "+x.getValue()*100+" %");
			System.out.println("Percentage Did Not Vote: "+(1-x.getValue())*100+" %");
		}

		//10 CLOSEST CONSTITUENCY RACES
		count = 0;
		System.out.println("\n<Constituencies With Closest Races>");
		while (count < 10) {
			Object[] closeRace = one.closestRace(MPs, secondPlace);
			Results first = (Results) closeRace[0];
			Results second = (Results) closeRace[1];
			double electorate = 0;
			double totalvotes = 0;
			for(Constituencies x : one.constitList) {
				if(first.ID.equals(x.ID) && second.ID.equals(x.ID)) {
					System.out.println("\nConstituency: "+x.constit+", Region: "+x.region);
					electorate = x.electorate;
				}
			}
			for(Results x : one.resList) {
				if(first.ID.equals(x.ID) && second.ID.equals(x.ID)) {
					totalvotes += x.votes;
				}
			}
			System.out.println("<1st Place> Name: "+first.firstname+" "+first.surname+", Votes: "+first.votes+", % Votes: "+(first.votes/electorate)*100+" %");
			System.out.println("<2nd Place> Name: "+second.firstname+" "+second.surname+", Votes: "+second.votes+", % Votes: "+(second.votes/electorate)*100+" %");
			System.out.println("Vote Difference: "+(first.votes-second.votes));
			double totalnonvotes = electorate - totalvotes;
			double percentnonvotes = (totalnonvotes/electorate)*100;
			System.out.println("Non-Voters: "+totalnonvotes+", % Non-Voters: "+percentnonvotes+" %");
			count++;
		}
	}
}