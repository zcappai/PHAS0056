package module5;
import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package for 'Scanner'

public class Minerals {

	/**
	 * IMPORTS DATA FROM URL WITH 'INTEGER|DOUBLE' FORMAT INTO 'HashMap<Integer, Double>' OBJECT
	 * THEN RETURNS 'HashMap<Integer, Double>' OBJECT
	 * @param url1 - URL for sample code number and mass data
	 * @param url2 - URL for sample code number and location data
	 * @throws Exception - IF INVALID URL USED, NO INPUT FROM URL OR NO INPUT WHEN READING LINE
	 */
	public static HashMap<Integer, Double> dataFromURL1(String url1) throws Exception{
		//Creating 1 'HashMap' with 'Integer' keys and 'Double' values
		HashMap<Integer, Double> mass = new HashMap<Integer, Double>();

		//Streams data from 1st URL and buffers to memory using 'BufferedReader' object
		URL u1 = new URL(url1);
		InputStream is1 = u1.openStream();
		InputStreamReader isr1 = new InputStreamReader(is1);
		BufferedReader reader1 = new BufferedReader(isr1);

		String line = ""; //Initialising empty string to store line data

		/* While line isn't empty, uses 'Scanner' to look for token separated by whitespace.
		 * Due to order of data in 1st URL, 1st token of line is parsed as 'Integer' and 2nd
		 * token is parsed as 'Double'. Both tokens are put into 'HashMap' as key-value pair.
		 * 'Integer' = sample code number, 'Double' = sample mass(g) */
		while ((line = reader1.readLine()) != null) {
			Scanner sc = new Scanner(line);
			while(sc.hasNext()) {
				Integer sample1 = Integer.parseInt(sc.next());
				Double mass1 = Double.parseDouble(sc.next());
				mass.put(sample1, mass1);
			}
			sc.close(); //Closing resource to clean memory
		}
		return mass;
	}

	/**
	 * IMPORTS DATA FROM URL WITH 'STRING|INTEGER' FORMAT INTO 'HashMap' OBJECT
	 * THEN RETURNS 'HashMap' OBJECT
	 * @param url1 - URL for sample code number and mass data
	 * @param url2 - URL for sample code number and location data
	 * @throws Exception - IF INVALID URL USED, NO INPUT FROM URL OR NO INPUT WHEN READING LINE
	 */
	public static HashMap<Integer, String> dataFromURL2(String url2) throws Exception{
		//Creating 1c'HashMap' with 'Integer' keys and 'String' values
		HashMap<Integer, String> loc = new HashMap<Integer, String>();

		//Streams data from 2nd URL and buffers to memory using 'BufferedReader' object
		URL u2 = new URL(url2);
		InputStream is2 = u2.openStream();
		InputStreamReader isr2 = new InputStreamReader(is2);
		BufferedReader reader2 = new BufferedReader(isr2);

		String line = ""; //Initialising empty string to store line data

		/* While line isn't empty, uses 'Scanner' to look for token separated by whitespace.
		 * Due to order of data in 2nd URL, 1st token of line is parsed as 'String' and 2nd
		 * token is parsed as 'Integer'. Both tokens are put into 'HashMap' as key-value pair.
		 * 'Integer' = sample code number, 'String' = sample location */
		while ((line = reader2.readLine()) != null) {
			Scanner sc = new Scanner(line);
			while(sc.hasNext()) {
				String loc2 = sc.next();
				Integer sample2 = Integer.parseInt(sc.next());
				loc.put(sample2, loc2);
			}
			sc.close(); //Closing resource to clean memory
		}
		return loc;
	}

	/**
	 * FINDS THE SAMPLE WITH THE LARGEST MASS
	 * @param mass - 'HashMap' with sample code number and mass data
	 */
	public static Map.Entry<Integer, Double> maxMass(HashMap<Integer, Double> mass) {
		/* Creates empty 'Map.Entry' key-value pair, then iterates over 'mass.entrySet' collection-view
		 * of map to find key-value pair that corresponds to sample with largest mass.
		 * If 'maxMass' key-value pair is empty or 'entry' value is greater than current 'maxMass' value,
		 * 'maxMass' is set to current key-value pair */
		Map.Entry<Integer, Double> maxMass = null;
		for(Map.Entry<Integer, Double> entry : mass.entrySet()) {
			if(maxMass == null || entry.getValue() > maxMass.getValue()) {
				maxMass = entry;
			}
		}
		return maxMass;
	}

	/**
	 * FINDS THE SAMPLE WITH THE SMALLEST MASS
	 * @param mass - 'HashMap' with sample code number and mass data
	 */
	public static Map.Entry<Integer, Double> minMass(HashMap<Integer, Double> mass) {
		/* Creates empty 'Map.Entry' key-value pair, then iterates over 'mass.entrySet' collection-view
		 * of map to find key-value pair that corresponds to sample with smallest mass.
		 * If 'minMass' key-value pair is empty or 'entry' value is less than current 'minMass' value,
		 * 'minMass' is set to current key-value pair */
		Map.Entry<Integer, Double> minMass = null;
		for(Map.Entry<Integer, Double> entry : mass.entrySet()) {
			if(minMass == null || entry.getValue() < minMass.getValue()) {
				minMass = entry;
			}
		}
		return minMass;
	}

	public static void main(String[] args) {

		//Initialising 2 URLs as strings
		String url1 = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-samples.txt";
		String url2 = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-locations.txt";
		System.out.println("URL 1 (Sample Code Number/Mass): "+url1);
		System.out.println("URL 2 (Sample Code Number/Location): "+url2);

		//Initialising 2 'HashMap' objects for mass and location data
		HashMap<Integer, Double> mass = null;
		HashMap<Integer, String> loc = null;

		/**
		 * Tries to import data from 2 URLs into 2 'HashMap' objects
		 * Catches exception if invalid URL used, no input from URL or no input when reading line
		 */
		try {
			mass = dataFromURL1(url1);
			loc = dataFromURL2(url2);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		//Finds samples with largest and smallest mass
		Map.Entry<Integer, Double> maxMass = maxMass(mass);
		Map.Entry<Integer, Double> minMass = minMass(mass);

		/* Gets key (sample code number), value (sample mass) and value from 2nd 'HashMap' (location of sample)
		 * corresponding to key from 1st 'HashMap' for largest and smallest mass */
		Integer maxKey = maxMass.getKey();
		Double maxValue = maxMass.getValue();
		String maxLoc = loc.get(maxKey);
		Integer minKey = minMass.getKey();
		Double minValue = minMass.getValue();
		String minLoc = loc.get(minKey);

		//Printing required results for largest and smallest mass samples
		System.out.println("\n<<Sample with Largest Mass>>");
		System.out.println("Sample Code Number: "+maxKey);
		System.out.println("Sample Mass: "+maxValue+"g");
		System.out.println("Sample Location: "+maxLoc);
		System.out.println("\n<<Sample with Smallest Mass>>");
		System.out.println("Sample Code Number: "+minKey);
		System.out.println("Sample Mass: "+minValue+"g");
		System.out.println("Sample Location: "+minLoc);
	}

}