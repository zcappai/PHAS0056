package exam1test;

import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package for 'Scanner'

public class MidTerm2016 {

	//Member variables
	String name; //name
	String team; //team
	String pos; //position
	double G; //number of games played
	double AB; //total number of times batting
	double R; //total runs scored
	double H; //total number of times ball hit and 1st base
	double twoB; //total number of times ball hit and 2nd base
	double threeB; //total number of times ball hit and 3rd base
	double HR; //total number of home runs
	double RBI; //runs batted in
	double AVG; //batting average
	double OBP; //on-base percentage

	//Constructor with arguments for all baseball player data
	public MidTerm2016(String name, String team, String pos, double G, double AB, double R, double H, double twoB, double threeB, double HR, double RBI, double AVG, double OBP) {
		this.name = name;
		this.team = team;
		this.pos = pos;
		this.G = G;
		this.AB = AB;
		this.R = R;
		this.H = H;
		this.twoB = twoB;
		this.threeB = threeB;
		this.HR = HR;
		this.RBI = RBI;
		this.AVG = AVG;
		this.OBP = OBP;
	}

	/**
	 * IMPORTS DATA FROM URL AND PUTS INTO HASHMAP
	 * @param url of baseball player data
	 * @return
	 * @throws Exception - IF INVALID URL USED, NO INPUT FROM URL OR NO INPUT WHEN READING LINE
	 */
	public static HashMap<Integer, MidTerm2016> mapFromURL(String url) throws Exception{
		HashMap<Integer, MidTerm2016> base = new HashMap<Integer, MidTerm2016>();

		//Streams data from URL and buffers to memory using 'BufferedReader' object
		URL u = new URL(url);
		InputStream is = u.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);

		String line = ""; //Initialising empty string to store line data

		int counter = 0; //Initialising counter for skipping 1st 2 lines
		int key = 1; //Unique key assigned to each player

		/* While line isn't empty, uses 'Scanner' to look for token separated by tab.
		 * The data in URL is parsed in the following order: name, team, position, games played,
		 * number of times batting, runs scored, hit and 1st base, hit and 2nd base, hit and 3rd base,
		 * home run, runs due to batters' actions, batting average and on-base percentage.
		 * Unique key for 'HashMap' created, iterated and added separately*/
		while ((line = reader.readLine()) != null) {
			if(counter>1) { //Only analyses line after 2nd line
				Scanner sc = new Scanner(line).useDelimiter("\t");
				while(sc.hasNext()) {
					//Parsing tokens as integers, doubles or strings
					String name = sc.next();
					String team = sc.next();
					String pos = sc.next();
					double G = Double.parseDouble(sc.next());
					double AB = Double.parseDouble(sc.next());
					double R = Double.parseDouble(sc.next());
					double H = Double.parseDouble(sc.next());
					double twoB = Double.parseDouble(sc.next());
					double threeB = Double.parseDouble(sc.next());
					double HR = Double.parseDouble(sc.next());
					double RBI = Double.parseDouble(sc.next());
					double AVG = Double.parseDouble(sc.next());
					double OBP = Double.parseDouble(sc.next());
					//Adding parsed data to 'MidTerm2016' object
					MidTerm2016 p = new MidTerm2016(name, team, pos, G, AB, R, H, twoB, threeB, HR, RBI, AVG, OBP);
					base.put(key, p); //Putting key and parsed baseball player data in 'HashMap'
					key++;
				}
				sc.close(); //Closing resource to clean memory
			}
			counter++; //Increases by 1 to skip line until condition above met
		}
		return base; //Returns 'HashMap' with earthquake data
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT PLAYER WITH MOST HOME RUNS
	 * Creates empty 'Map.Entry' key-value pair, then iterates over 'x.entrySet' collection-view
	 * of map to find key-value pair that corresponds to player with most home runs.
	 * If 'mostHR' key-value pair is empty or 'entry' value is greater than current 'mostHR' value,
	 * 'mostHR' is set to current key-value pair
	 * @param x - 'HashMap' of baseball player data
	 */
	public static void mostHR(HashMap<Integer, MidTerm2016> x) {
		Map.Entry<Integer, MidTerm2016> mostHR = null;
		for(Map.Entry<Integer, MidTerm2016> entry : x.entrySet()) {
			if(mostHR == null || entry.getValue().HR > mostHR.getValue().HR) {
				mostHR = entry;
			}
		}
		//Gets value of key-value pair for most home runs
		MidTerm2016 maxValue = mostHR.getValue();
		//Prints data from player with most home runs
		System.out.println("\n<Most Home Runs>");
		System.out.println("Name: "+maxValue.name);
		System.out.println("Team: "+maxValue.team);
		System.out.println("Position: "+maxValue.pos);
		System.out.println("Total Games Played: "+maxValue.G);
		System.out.println("Total Times Batting: "+maxValue.AB);
		System.out.println("Total Runs Scored: "+maxValue.R);
		System.out.println("Hit + 1st Base: "+maxValue.H);
		System.out.println("Hit + 2nd Base: "+maxValue.twoB);
		System.out.println("Hit + 3rd Base: "+maxValue.threeB);
		System.out.println("Total Home Runs: "+maxValue.HR);
		System.out.println("Runs Batted In: "+maxValue.RBI);
		System.out.println("Batting Average: "+maxValue.AVG);
		System.out.println("On-Base Percentage: "+maxValue.OBP);
	}

	/* CALCULATES AND PRINTS THE NUMBER OF PLAYERS WITH 10 OR MORE AT-BATS
	 * First creates 'TreeMap' to store sorted and counted data. For each key-value pair,
	 * if At-Bats is >= 10, gets which team the player is with, then checks integer of corresponding
	 * team within 'TreeMap'. If null, count for team changed to 1, otherwise increases count by 1.
	 * @param x - 'HashMap' of baseball player data
	 * @return
	 */
	public static Map<String, Integer> atbatTeam(HashMap<Integer, MidTerm2016> x) {
		//'TreeMap' created to store <'Team', 'Number of Players with 10 or more At-Bats'>
		Map<String, Integer> perTeam = new TreeMap<String, Integer>();

		for(Map.Entry<Integer, MidTerm2016> entry : x.entrySet()) {

			//Only deals with key-value pairs with at least 10 At-Bats
			if(entry.getValue().AB >= 10) {
				String val = entry.getValue().team;
				Integer count = perTeam.get(val);

				//Counts number of players with at least 10 At-Bats
				if(count == null) {
					perTeam.put(val, 1);
				}
				else {
					perTeam.put(val, count+1);
				}
			}
		}
		//Prints the number of players per team with At-Bats of at least 10
		System.out.println("\n<Number of Players Per Team with At Least 10 At-Bats>");
		for(int i = 0; i<30; i++) {
			System.out.println("For team "+perTeam.keySet().toArray()[i]+", there are "+perTeam.get(perTeam.keySet().toArray()[i])+" players");
		}
		return perTeam; //Returns 'TreeMap' with total players per team with At-Bats greater or equal to 10
	}

	/* OF PLAYERS WITH 10 OR MORE AT-BATS PER TEAM, PRINTS PLAYER WITH HIGHEST SLUGGING PERCENTAGE
	 * First creates 'TreeMap' to store sorted and counted data. For each key-value pair,
	 * if At-Bats is >= 10, gets which team the player is with, then calculates slugging percentage.
	 * If 'TreeMap' element for given team is empty, key of new player assigned to team. But if not empty,
	 * calculates slugging percentage of previous player and replaces with new player if new slugging percentage
	 * is bigger.
	 * @param x - 'HashMap' of baseball player data
	 * @return
	 */
	public static Map<String, Integer> SLGTeam(HashMap<Integer, MidTerm2016> x) {
		//Uses following equation: SLG = (H + 2*2B + 3*3B + 4*HR)/AB
		//'TreeMap' created to store <'Team', 'Player with 10 or more At-Bats with Highest SLG'>
		Map<String, Integer> perTeam = new TreeMap<String, Integer>();

		for(Map.Entry<Integer, MidTerm2016> entry : x.entrySet()) {

			//Only deals with key-value pairs with at least 10 At-Bats
			if(entry.getValue().AB >= 10) {
				String val = entry.getValue().team;
				Integer key = entry.getKey();
				Double slg = (entry.getValue().H + 2*entry.getValue().twoB + 3*entry.getValue().threeB + 4*entry.getValue().HR)/(entry.getValue().AB);

				//Sets 'TreeMap' value to player with highest SLG per team
				if(perTeam.get(val) == null) {
					perTeam.put(val, key);
				}
				else {
					Integer count = perTeam.get(val);
					Double c_slg = (x.get(count).H + 2*x.get(count).twoB + 3*x.get(count).threeB + 4*x.get(count).HR)/x.get(count).AB;
					if(c_slg<slg) {
						perTeam.put(val, key);
					}
				}
			}
		}
		//Prints the player per team with At-Bats of at least 10, with highest slugging percentage
		System.out.println("\n<Highest Slugging Percentage For Players Per Team with At Least 10 At-Bats>");
		for(int i = 0; i<30; i++) {
			System.out.println("Team: "+perTeam.keySet().toArray()[i]);
			System.out.println("Name: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).name);
			System.out.println("Position: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).pos);
			System.out.println("Total Games Played: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).G);
			System.out.println("Total Times Batting: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).AB);
			System.out.println("Total Runs Scored: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).R);
			System.out.println("Hit + 1st Base: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).H);
			System.out.println("Hit + 2nd Base: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).twoB);
			System.out.println("Hit + 3rd Base: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).threeB);
			System.out.println("Total Home Runs: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).HR);
			System.out.println("Runs Batted In: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).RBI);
			System.out.println("Batting Average: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).AVG);
			System.out.println("On-Base Percentage: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).OBP+"\n");
		}
		return perTeam; //Returns 'TreeMap' with player per team with highest SLG
	}

	/* OF PLAYERS WITH 10 OR MORE AT-BATS PER TEAM, PRINTS PLAYER WITH HIGHEST ON-BASE PLUS SLUGGING PERCENTAGE
	 * First creates 'TreeMap' to store sorted and counted data. For each key-value pair,
	 * if At-Bats is >= 10, gets which team the player is with, then calculates on-base plus slugging percentage.
	 * If 'TreeMap' element for given team is empty, key of new player assigned to team. But if not empty,
	 * calculates on-base plus slugging percentage of previous player and replaces with new player if new
	 * on-base plus slugging percentage is bigger.
	 * @param x - 'HashMap' of baseball player data
	 * @return
	 */
	public static Map<String, Integer> OPS(HashMap<Integer, MidTerm2016> x) {
		//Uses following equation: SLG = (H + 2*2B + 3*3B + 4*HR)/AB + OBP
		//'TreeMap' created to store <'Team', 'Players with highest SLG + on-base'>
		Map<String, Integer> perTeam = new TreeMap<String, Integer>();

		for(Map.Entry<Integer, MidTerm2016> entry : x.entrySet()) {

			//Only deals with key-value pairs with at least 10 At-Bats
			if(entry.getValue().AB >= 10) {
				String val = entry.getValue().team;
				Integer key = entry.getKey();
				Double ops = (entry.getValue().H + 2*entry.getValue().twoB + 3*entry.getValue().threeB + 4*entry.getValue().HR)/(entry.getValue().AB) + entry.getValue().OBP;

				//Sets 'TreeMap' value to player with highest SLG + on-base per team
				if(perTeam.get(val) == null) {
					perTeam.put(val, key);
				}
				else {
					Integer count = perTeam.get(val);
					Double c_ops = (x.get(count).H + 2*x.get(count).twoB + 3*x.get(count).threeB + 4*x.get(count).HR)/x.get(count).AB + x.get(count).OBP;
					if(c_ops<ops) {
						perTeam.put(val, key);
					}
				}
			}
		}
		//Prints the player per team with At-Bats of at least 10, with highest slugging percentage + on-base
		System.out.println("\n<Highest On-Base + Slugging Percentage For Players Per Team with At Least 10 At-Bats>");
		for(int i = 0; i<30; i++) {
			System.out.println("Team: "+perTeam.keySet().toArray()[i]);
			System.out.println("Name: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).name);
			System.out.println("Position: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).pos);
			System.out.println("Total Games Played: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).G);
			System.out.println("Total Times Batting: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).AB);
			System.out.println("Total Runs Scored: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).R);
			System.out.println("Hit + 1st Base: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).H);
			System.out.println("Hit + 2nd Base: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).twoB);
			System.out.println("Hit + 3rd Base: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).threeB);
			System.out.println("Total Home Runs: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).HR);
			System.out.println("Runs Batted In: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).RBI);
			System.out.println("Batting Average: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).AVG);
			System.out.println("On-Base Percentage: "+x.get(perTeam.get(perTeam.keySet().toArray()[i])).OBP+"\n");
		}
		return perTeam; //Returns 'TreeMap' with player per team with highest on-base + SLG
	}

	public static void main(String[] args) {
		//URL of data to be analysed
		String url1 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17/MLB2001Hitting.txt";
		System.out.println("URL of Baseball Player Data: "+url1);

		HashMap<Integer, MidTerm2016> x = null; //Empty 'HashMap' initialised for baseball player data

		/** Tries to load baseball player data into 'HashMap', then prints total number of players.
		 * Catches exception if invalid URL used, no input from URL or no input when reading line*/
		try {
			x = mapFromURL(url1);
			System.out.println("\nTotal Number of Players: "+x.size());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		mostHR(x); //Prints details about player with most home runs

		//Prints number of players per team with at least 10 At-Bats
		Map<String, Integer> atBat = atbatTeam(x);

		//Prints player per team with at least 10 At-Bats and highest slugging percentage
		Map<String, Integer> SLG = SLGTeam(x);

		//Prints player per team with at least 10 At-Bats and highest on-base + slugging percentage
		Map<String, Integer> OPS = OPS(x);
	}

}