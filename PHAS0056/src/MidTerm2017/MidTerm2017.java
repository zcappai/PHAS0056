package MidTerm2017;

import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package for 'Scanner'

import exam1test.MidTerm2015;

public class MidTerm2017 {

	/**
	 * IMPORTS DATA FROM URL AND PUTS INTO HASHMAP
	 * @param url of sea ice data
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

	public static HashMap<Integer, SeaIce> dataToHashMap(BufferedReader reader) throws Exception{
		HashMap<Integer, SeaIce> seaice = new HashMap<Integer, SeaIce>();
		String line = ""; //Initialising empty string to store line data

		int counter = 0; //Initialising counter for skipping 1st line
		int key = 1; //Unique key assigned to each player

		/* While line isn't empty, uses 'Scanner' to look for token separated by comma plus whitespace.
		 * The data in URL is parsed in the following order: year, month, data-type, region, extent, area
		 * Unique key for 'HashMap' created, iterated and added separately*/
		while ((line = reader.readLine()) != null) {
			if(counter>0) { //Only analyses line after 1st line
				Scanner sc = new Scanner(line).useDelimiter(",\\s*");
				while(sc.hasNext()) {
					//Parsing tokens as integers, doubles or strings
					int year = Integer.parseInt(sc.next());
					int mo = Integer.parseInt(sc.next());
					String data_type = sc.next();
					String hemi = sc.next();
					double extent = Double.parseDouble(sc.next());
					double area = Double.parseDouble(sc.next());
					//Adding parsed data to 'SeaIce' object
					SeaIce p = new SeaIce(year, mo, data_type, hemi, extent, area);
					seaice.put(key, p); //Putting key and parsed sea ice measurement data in 'HashMap'
					key++;
				}
				sc.close(); //Closing resource to clean memory
			}
			counter++; //Increases by 1 to skip line until condition above met
		}
		return seaice; //Returns 'HashMap' with sea ice data
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT MEASUREMENT WITH LOWEST TOTAL ICE EXTENT
	 * Creates empty 'Map.Entry' key-value pair, then iterates over 'x.entrySet' collection-view
	 * of map to find key-value pair that corresponds to measurement with lowest total ice extent.
	 * If 'smallExtent' key-value pair is empty or 'entry' value is smaller than current 'smallExtent' value,
	 * and 'entry' value is greater than or equal to 0 (to eliminate missing data being counted)
	 * 'smallExtent' is set to current key-value pair
	 * @param x - 'HashMap' of sea ice data
	 */
	public static void lowestExtent(HashMap<Integer, SeaIce> x) {
		Map.Entry<Integer, SeaIce> smallExtent = null;
		for(Map.Entry<Integer, SeaIce> entry : x.entrySet()) {
			if(smallExtent == null || entry.getValue().extent < smallExtent.getValue().extent & entry.getValue().extent >= 0) {
				smallExtent = entry;
			}
		}
		//Gets value of key-value pair for lowest sea ice extent
		SeaIce minValue = smallExtent.getValue();
		//Prints data from measurement with lowest sea ice extent
		System.out.println("\n<Lowest Total Sea Ice Extent>");
		System.out.println("Year: "+minValue.year);
		System.out.println("Month: "+minValue.mo);
		System.out.println("Data-Type: "+minValue.data_type);
		System.out.println("Region: "+minValue.hemi);
		System.out.println("Total Sea Ice Extent: "+minValue.extent);
		System.out.println("Total Sea Ice Area: "+minValue.area);
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT MEASUREMENT WITH LOWEST TOTAL ICE AREA
	 * Creates empty 'Map.Entry' key-value pair, then iterates over 'x.entrySet' collection-view
	 * of map to find key-value pair that corresponds to measurement with lowest total ice area.
	 * If 'smallArea' key-value pair is empty or 'entry' value is smaller than current 'smallArea' value,
	 * and 'entry' value is greater than or equal to 0 (to eliminate missing data being counted)
	 * 'smallExtent' is set to current key-value pair
	 * @param x - 'HashMap' of sea ice data
	 */
	public static void lowestArea(HashMap<Integer, SeaIce> x) {
		Map.Entry<Integer, SeaIce> smallArea = null;
		for(Map.Entry<Integer, SeaIce> entry : x.entrySet()) {
			if(smallArea == null || entry.getValue().area < smallArea.getValue().area & entry.getValue().area >= 0) {
				smallArea = entry;
			}
		}
		//Gets value of key-value pair for lowest sea ice area
		SeaIce minValue = smallArea.getValue();
		//Prints data from measurement with lowest sea ice area
		System.out.println("\n<Lowest Total Sea Ice Area>");
		System.out.println("Year: "+minValue.year);
		System.out.println("Month: "+minValue.mo);
		System.out.println("Data-Type: "+minValue.data_type);
		System.out.println("Region: "+minValue.hemi);
		System.out.println("Total Sea Ice Extent: "+minValue.extent);
		System.out.println("Total Sea Ice Area: "+minValue.area);
	}

	public static void main(String[] args) {
		//URL of data to be analysed
		String url1 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2017-18/N_extent_v3.0.csv";
		System.out.println("URL of Sea Ice Data: "+url1);

		BufferedReader bf = null;
		HashMap<Integer, SeaIce> data = null;

		/** Tries to load sea ice data into 'HashMap', then prints total number of measurements of sea ice.
		 * Catches exception if invalid URL used, no input from URL or no input when reading line*/
		try {
			bf = brFromURL(url1);
			data = dataToHashMap(bf);
			System.out.println("\nTotal Number of Measurements of Sea Ice: "+data.size());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		lowestExtent(data);
		lowestArea(data);
	}
}