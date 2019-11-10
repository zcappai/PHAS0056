package exam1test;

import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package for 'Scanner'

public class MidTerm2015 {

	//Member variables w/o unique ID
	int y; //year
	int m; //month
	int d; //day
	int h; //hour
	int min; //minute
	double s; //second
	double lat; //latitude
	double lon; //longitude
	double dep; //depth
	double eh1; //horizontal major axis
	double eh2; //horizontal minor axis
	double az; //azimuth
	double ez; //error in depth
	double mag; //magnitude

	//Constructor with arguments for all earthquake data w/o ID
	public MidTerm2015(int y, int m, int d, int h, int min, double s, double lat, double lon, double dep, double eh1, double eh2, double az, double ez, double mag) {
		this.y = y;
		this.m = m;
		this.d = d;
		this.h = h;
		this.min = min;
		this.s = s;
		this.lat = lat;
		this.lon = lon;
		this.dep = dep;
		this.eh1 = eh1;
		this.eh2 = eh2;
		this.az = az;
		this.ez = ez;
		this.mag = mag;
	}

	/**
	 * IMPORTS DATA FROM URL AND PUTS INTO HASHMAP
	 * @param url of earthquake data
	 * @return
	 * @throws Exception - IF INVALID URL USED, NO INPUT FROM URL OR NO INPUT WHEN READING LINE
	 */
	public static HashMap<Integer, MidTerm2015> mapFromURL(String url) throws Exception{
		HashMap<Integer, MidTerm2015> quake = new HashMap<Integer, MidTerm2015>();

		//Streams data from URL and buffers to memory using 'BufferedReader' object
		URL u = new URL(url);
		InputStream is = u.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);

		String line = ""; //Initialising empty string to store line data

		int counter = 0; //Initialising counter for skipping 1st 2 lines

		/* While line isn't empty, uses 'Scanner' to look for token separated by whitespace.
		 * The data in URL is parsed in the following order: year, month, day, hour, minute, second,
		 * latitude, longitude, depth, 2 different location errors, error in depth, magnitude and
		 * unique ID */
		while ((line = reader.readLine()) != null) {
			if(counter>1) { //Only analyses line after 2nd line
				Scanner sc = new Scanner(line);
				while(sc.hasNext()) {
					//Parsing tokens as integers or doubles
					int ye = Integer.parseInt(sc.next());
					int mo = Integer.parseInt(sc.next());
					int da = Integer.parseInt(sc.next());
					int ho = Integer.parseInt(sc.next());
					int mins = Integer.parseInt(sc.next());
					double se = Double.parseDouble(sc.next());
					double lati = Double.parseDouble(sc.next());
					double longi = Double.parseDouble(sc.next());
					double dept = Double.parseDouble(sc.next());
					double eh1_e = Double.parseDouble(sc.next());
					double eh2_e = Double.parseDouble(sc.next());
					double az_e = Double.parseDouble(sc.next());
					double ez_e = Double.parseDouble(sc.next());
					double magn = Double.parseDouble(sc.next());
					int id = Integer.parseInt(sc.next());
					//Adding parsed data to 'MidTerm2015' object
					MidTerm2015 p = new MidTerm2015(ye, mo, da, ho, mins, se, lati, longi, dept, eh1_e, eh2_e, az_e, ez_e, magn);
					quake.put(id, p); //Putting unique ID and parsed earthquake data in 'HashMap'
				}
				sc.close(); //Closing resource to clean memory
			}
			counter++; //Increases by 1 to skip line until condition above met
		}
		return quake; //Returns 'HashMap' with earthquake data
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT EARTHQUAKE WITH LARGEST MAGNITUDE
	 * Creates empty 'Map.Entry' key-value pair, then iterates over 'x.entrySet' collection-view
	 * of map to find key-value pair that corresponds to earthquake with largest magnitude.
	 * If 'maxMag' key-value pair is empty or 'entry' value is greater than current 'maxMag' value,
	 * 'maxMag' is set to current key-value pair
	 * @param x - 'HashMap' of earthquake data
	 */
	public static void maxMag(HashMap<Integer, MidTerm2015> x) {
		Map.Entry<Integer, MidTerm2015> maxMag = null;
		for(Map.Entry<Integer, MidTerm2015> entry : x.entrySet()) {
			if(maxMag == null || entry.getValue().mag > maxMag.getValue().mag) {
				maxMag = entry;
			}
		}
		//Gets value of key-value pair for largest magnitude
		MidTerm2015 maxValue = maxMag.getValue();
		//Prints data from earthquake with largest magnitude
		System.out.println("\n<Maximum Magnitude Earthquake>");
		System.out.println("Year: "+maxValue.y);
		System.out.println("Month: "+maxValue.m);
		System.out.println("Day: "+maxValue.d);
		System.out.println("Hour: "+maxValue.h);
		System.out.println("Minute: "+maxValue.min);
		System.out.println("Second: "+maxValue.s);
		System.out.println("Latitude: "+maxValue.lat);
		System.out.println("Longitude: "+maxValue.lon);
		System.out.println("Depth: "+maxValue.dep);
		System.out.println("Magnitude: "+maxValue.mag);
	}

	/**
	 * CALCULATES AND PRINTS THE TOTAL NUMBER OF EARTHQUAKES PER MONTH
	 * First creates an array with 12 elements, then for every key-value pair,
	 * increases the counter for the corresponding month within 'perMonth' array by 1
	 * @param x - 'HashMap' of earthquake data
	 * @return
	 */
	public static int[] quakeMonths(HashMap<Integer, MidTerm2015> x) {
		int[] perMonth = new int[12];
		for(Map.Entry<Integer, MidTerm2015> entry : x.entrySet()) {
			perMonth[entry.getValue().m-1] += 1; //'-1' since array index starts from 0
		}
		return perMonth; //Returns array with total number of earthquakes per month
	}

	/**
	 * CALCULATES AND RETURNS UNIQUE ID OF DEEPEST EARTHQUAKE PER MONTH
	 * First creates array with 12 elements, then for every key-value pair, checks if depth
	 * is greater than depth for current key-value pair. If greater, changes unique ID for
	 * corresponding month within 'deepMonth' array to new key.
	 * @param x - 'HashMap' of earthquake data
	 * @return
	 */
	public static int[] deepMonths(HashMap<Integer, MidTerm2015> x) {
		int[] deepMonth = new int[12];
		for(Map.Entry<Integer, MidTerm2015> entry : x.entrySet()) {
			//Key changed only if current key is 0 or if new earthquake has depth greater than current earthquake for given month
			if(deepMonth[entry.getValue().m-1] == 0 || entry.getValue().dep > x.get(deepMonth[entry.getValue().m-1]).dep) {
				deepMonth[entry.getValue().m-1] = entry.getKey(); //Sets new key for corresponding month in 'deepMonth' array
			}
		}
		return deepMonth; //Returns array of unique ID's for earthquakes with greatest depth per month
	}

	/**
	 * CALCULATES AND RETURNS UNIQUE ID OF EARTHQUAKES WITH MOST ACCURATE DEPTH PER MONTH
	 * First creates array with 12 elements, then for every key-value pair, checks if error
	 * on depth is less than error depth for current key-value pair. If less, then changes
	 * unique ID for corresponding month within 'deepAccMonth' array to new key.
	 * @param x - 'HashMap' of earthquake data
	 * @return
	 */
	public static int[] deepAccMonths(HashMap<Integer, MidTerm2015> x) {
		int[] deepAccMonth = new int[12];
		for(Map.Entry<Integer, MidTerm2015> entry : x.entrySet()) {
			//Key changed only if current key is 0 or if new earthquake has error in depth less than current earthquake for given month
			//Also error in depth must be be greater than or equal to 0, to avoid earthquakes with no error data
			if(deepAccMonth[entry.getValue().m-1] == 0 || entry.getValue().ez < x.get(deepAccMonth[entry.getValue().m-1]).ez && entry.getValue().ez >= 0) {
				deepAccMonth[entry.getValue().m-1] = entry.getKey();
			}
		}
		return deepAccMonth; //Returns array of unique ID's for earthquakes with most accurate depth per month
	}

	public static void main(String[] args) {
		//URL of data to be analysed
		String url1 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2015-16/earthquakesCA1989.txt";
		System.out.println("URL of Earthquake Data: "+url1);

		HashMap<Integer, MidTerm2015> x = null; //Empty 'HashMap' initialised for earthquake data

		/** Tries to load earthquake data into 'HashMap', then prints total number of earthquakes in year.
		 * Catches exception if invalid URL used, no input from URL or no input when reading line*/
		try {
			x = mapFromURL(url1);
			System.out.println("\nTotal Number of Earthquakes: "+x.size());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		maxMag(x); //Prints details about earthquake with largest magnitude

		//Prints total number of earthquakes per month
		int[] qpM = quakeMonths(x);
		System.out.println("\n<Earthquakes per Month>");
		for(int i = 0; i<12; i++) {
			System.out.println("For Month "+(i+1)+" there were "+qpM[i]+" earthquakes");
		}

		//Prints details about the deepest earthquake per month, excluding error data
		int[] dM = deepMonths(x);
		System.out.println("\n<Deepest Earthquake per Month>");
		for(int i = 0; i<12; i++) {
			MidTerm2015 z = x.get(dM[i]);
			System.out.println("Month "+z.m+":");
			System.out.println("Year: "+z.y);
			System.out.println("Day: "+z.d);
			System.out.println("Hour: "+z.h);
			System.out.println("Minute: "+z.min);
			System.out.println("Second: "+z.s);
			System.out.println("Latitude: "+z.lat);
			System.out.println("Longitude: "+z.lon);
			System.out.println("Depth: "+z.dep);
			System.out.println("Magnitude: "+z.mag+"\n");
		}

		//Prints details about earthquake with most accurate depth per month (smallest error in depth)
		int[] dAM = deepAccMonths(x);
		System.out.println("\n<Most Accurate Deep Earthquake per Month>");
		for(int i = 0; i<12; i++) {
			MidTerm2015 q = x.get(dAM[i]);
			System.out.println("Month "+q.m+":");
			System.out.println("Year: "+q.y);
			System.out.println("Day: "+q.d);
			System.out.println("Hour: "+q.h);
			System.out.println("Minute: "+q.min);
			System.out.println("Second: "+q.s);
			System.out.println("Latitude: "+q.lat);
			System.out.println("Longitude: "+q.lon);
			System.out.println("Depth: "+q.dep);
			System.out.println("Error in Depth: "+q.ez);
			System.out.println("Magnitude: "+q.mag+"\n");
		}
	}
}