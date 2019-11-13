package MidTerm2017;

import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package for 'Scanner'

public class MidTerm2017 {

	/**
	 * IMPORTS DATA FROM URL AND PUTS INTO TreeMap
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

	public static TreeMap<Integer, SeaIce> dataToTreeMap(BufferedReader reader) throws Exception{
		TreeMap<Integer, SeaIce> seaice = new TreeMap<Integer, SeaIce>();
		String line = ""; //Initialising empty string to store line data

		int counter = 0; //Initialising counter for skipping 1st line
		int key = 1; //Unique key assigned to each player

		/* While line isn't empty, uses 'Scanner' to look for token separated by comma plus whitespace.
		 * The data in URL is parsed in the following order: year, month, data-type, region, extent, area
		 * Unique key for 'TreeMap' created, iterated and added separately*/
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
					seaice.put(key, p); //Putting key and parsed sea ice measurement data in 'TreeMap'
					key++;
				}
				sc.close(); //Closing resource to clean memory
			}
			counter++; //Increases by 1 to skip line until condition above met
		}
		return seaice; //Returns 'TreeMap' with sea ice data
	}

	/**
	 * FINDS AND PRINTS DETAILS ABOUT MEASUREMENT WITH LOWEST TOTAL ICE EXTENT
	 * Creates empty 'Map.Entry' key-value pair, then iterates over 'x.entrySet' collection-view
	 * of map to find key-value pair that corresponds to measurement with lowest total ice extent.
	 * If 'smallExtent' key-value pair is empty or 'entry' value is smaller than current 'smallExtent' value,
	 * and 'entry' value is greater than or equal to 0 (to eliminate missing data being counted)
	 * 'smallExtent' is set to current key-value pair
	 * @param x - 'TreeMap' of sea ice data
	 */
	public static void lowestExtent(TreeMap<Integer, SeaIce> x) {
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
	 * @param x - 'TreeMap' of sea ice data
	 */
	public static void lowestArea(TreeMap<Integer, SeaIce> x) {
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

	/**
	 * SEPARATES INITIAL DATA ARRAY INTO INDIVIDUAL MONTHS
	 * Creates empty 'TreeMap', then iterates over 'x.entrySet' collection-view
	 * of map to find key-value pairs with month that corresponds to the argument month.
	 * Adds measurement to new 'TreeMap' with newly created key.
	 * @param x - 'TreeMap' of sea ice data
	 * @param month - Month of data to be separated
	 */
	public static TreeMap<Integer, SeaIce> month(TreeMap<Integer, SeaIce> x, int month) {
		TreeMap<Integer, SeaIce> sepMonth = new TreeMap<Integer, SeaIce>();
		int key = 1;
		for(Map.Entry<Integer, SeaIce> entry : x.entrySet()) {
			if(entry.getValue().mo == month) {
				sepMonth.put(key, entry.getValue());
				key++;
			}
		}
		return sepMonth;
	}

	/**
	 * CALCULATES AND RETURNS UNIQUE ID OF YEAR WITH LOWEST ICE AREA PER MONTH
	 * First creates array with 12 elements, then for every key-value pair, checks if total ice area
	 * is lower than total ice area for current key-value pair. If lower, changes unique ID for
	 * corresponding month within 'lowiceMonth' array to new key.
	 * @param x - 'TreeMap' of sea ice data
	 * @return
	 */
	public static int lowiceMonth(TreeMap<Integer, SeaIce> x) {
		int lowiceMonth = 0;
		for(Map.Entry<Integer, SeaIce> entry : x.entrySet()) {
			//Key changed only if current key is 0 or if new measurement has ice area lower than current measurement for given month
			if(lowiceMonth == 0 || entry.getValue().area < x.get(lowiceMonth).area & entry.getValue().area >= 0) {
				lowiceMonth = entry.getKey(); //Sets new key for corresponding month in 'deepMonth' array
			}
		}
		int year = x.get(lowiceMonth).year;
		return year; //Returns array of unique ID's for earthquakes with greatest depth per month
	}

	/**
	 * CALCULATES AND RETURNS DIFFERENCE IN LOWEST ICE AREA PER MONTH BETWEEN EACH YEAR AND PREVIOUS YEAR
	 * First initialises counter to skip first entry, then for every key-value pair, checks if total ice area
	 * is greater than or equal to 0, then prints difference between ice area of current year and previous year,
	 * excluding the first entry for each month.
	 * @param x - 'TreeMap' of sea ice data
	 * @return
	 */
	public static void year2yearIce(TreeMap<Integer, SeaIce> x) {
		TreeMap<Integer, Double> dec = new TreeMap<Integer, Double>();
		int counter = 0; //Counter to skip first entry of 'TreeMap'
		for(Map.Entry<Integer, SeaIce> entry : x.entrySet()) {
			//Key changed only if current key is 0 or if new measurement has ice area lower than current measurement for given month
			if(counter > 0) {
				if(entry.getValue().area >= 0 & x.get(x.lowerKey(entry.getKey())).area>=0) {
					dec.put(entry.getValue().year, entry.getValue().area - x.get(x.lowerKey(entry.getKey())).area);
				}
			}
			counter++;
		}
		System.out.println("\nMonth: "+x.get(1).mo);
		for(Map.Entry<Integer, Double> entry : dec.entrySet()) {
			System.out.println(entry.getKey()+"-> Difference: "+entry.getValue()+" million km^2");
		}
	}

//	/**
//	 * CALCULATES AND RETURNS LIST OF 5 YEARS WITH BIGGEST DROPS IN ICE AREA PER MONTH
//	 * First initialises counter to skip first entry, then for every key-value pair, checks if total ice area
//	 * is greater than or equal to 0, then prints difference between ice area of current year and previous year,
//	 * excluding the first entry for each month.
//	 * @param x - 'TreeMap' of sea ice data
//	 * @return
//	 */
//	public static double[][] fiveBiggestdrops(TreeMap<Integer, SeaIce> x) {
//		int counter = 0; //Counter to skip first entry of 'TreeMap'
//		ArrayList<Integer> years  = new ArrayList<Integer>();
//		for(Map.Entry<Integer, SeaIce> entry : x.entrySet()) {
//			//Key changed only if current key is 0 or if new measurement has ice area lower than current measurement for given month
//			if(entry.getValue().area >= 0 & counter > 0) {
//				if(entry.getValue().year > x.get(x.lowerKey(entry.getKey())).year) {
//					five[][]
//							System.out.println("For "+entry.getValue().year+" - "+x.get(x.lowerKey(entry.getKey())).year+" in month "+entry.getValue().mo+": ");
//					System.out.println("Difference in Ice Sea Area: "+(entry.getValue().area - x.get(x.lowerKey(entry.getKey())).area)+"\n");
//				}
//			}
//			counter++;
//		}
//	}

	/**
	 * CALCULATES AND RETURNS AVERAGE ICE AREA ACROSS ALL YEAR PER MONTH
	 * First creates array with 12 elements, then for every key-value pair, checks if total ice area
	 * is lower than total ice area for current key-value pair. If lower, changes unique ID for
	 * corresponding month within 'lowiceMonth' array to new key.
	 * @param x - 'TreeMap' of sea ice data
	 * @return
	 */
	public static double[] avgAreaMonth(TreeMap<Integer, SeaIce> x) {
		double[] areaMonth = new double[12];
		int[] perMonth = new int[12];
		for(Map.Entry<Integer, SeaIce> entry : x.entrySet()) {
			if(entry.getValue().area >= 0) {
				areaMonth[entry.getValue().mo-1] = areaMonth[entry.getValue().mo-1] + entry.getValue().area; //Sets new key for corresponding month in 'deepMonth' array
				perMonth[entry.getValue().mo-1] = perMonth[entry.getValue().mo-1] + 1; //Sets new key for corresponding month in 'deepMonth' array
			}
		}
		double[] avgAreaMonth = new double[12];
		for(int i = 0; i<areaMonth.length; i++) {
			avgAreaMonth[i] = areaMonth[i]/perMonth[i];
		}
		return avgAreaMonth; //Returns array of unique ID's for earthquakes with greatest depth per month
	}

	/**
	 * CALCULATES AND RETURNS YEAR IN WHICH THERE WILL BE NO ICE IN THE ARCTIC
	 * First creates array with 12 elements, then for every key-value pair, checks if total ice area
	 * is lower than total ice area for current key-value pair. If lower, changes unique ID for
	 * corresponding month within 'lowiceMonth' array to new key.
	 * @param x - 'TreeMap' of sea ice data
	 * @return
	 */
	public static int noIce(double avgChange, TreeMap<Integer, SeaIce> x) {
		double finalArea = x.lastEntry().getValue().area;
		int finalYear = x.lastEntry().getValue().year;
		int counter = 0;
		double newArea = finalArea;
		while(newArea > 0) {
			newArea = newArea + avgChange;
			counter++;
		}
		return finalYear+counter;
	}

	public static void main(String[] args) {
		//URL of data to be analysed
		String url1 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2017-18/N_extent_v3.0.csv";
		System.out.println("URL of Sea Ice Data: "+url1);

		BufferedReader bf = null;
		TreeMap<Integer, SeaIce> data = null;

		/** Tries to load sea ice data into 'TreeMap', then prints total number of measurements of sea ice.
		 * Catches exception if invalid URL used, no input from URL or no input when reading line*/
		try {
			bf = brFromURL(url1);
			data = dataToTreeMap(bf);
			System.out.println("\nTotal Number of Measurements of Sea Ice: "+data.size());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		lowestExtent(data);
		lowestArea(data);

		TreeMap<Integer, SeaIce> jan = month(data, 1);
		TreeMap<Integer, SeaIce> feb = month(data, 2);
		TreeMap<Integer, SeaIce> mar = month(data, 3);
		TreeMap<Integer, SeaIce> apr = month(data, 4);
		TreeMap<Integer, SeaIce> may = month(data, 5);
		TreeMap<Integer, SeaIce> jun = month(data, 6);
		TreeMap<Integer, SeaIce> jul = month(data, 7);
		TreeMap<Integer, SeaIce> aug = month(data, 8);
		TreeMap<Integer, SeaIce> sep = month(data, 9);
		TreeMap<Integer, SeaIce> oct = month(data, 10);
		TreeMap<Integer, SeaIce> nov = month(data, 11);
		TreeMap<Integer, SeaIce> dec = month(data, 12);

		//Prints details about the lowest sea ice area per month, excluding empty data
		System.out.println("\n<Lowest Sea Ice Area per Month>");
		System.out.println("January: "+lowiceMonth(jan));
		System.out.println("February"+lowiceMonth(feb));
		System.out.println("March: "+lowiceMonth(mar));
		System.out.println("April: "+lowiceMonth(apr));
		System.out.println("May: "+lowiceMonth(may));
		System.out.println("June: "+lowiceMonth(jun));
		System.out.println("July: "+lowiceMonth(jul));
		System.out.println("August: "+lowiceMonth(aug));
		System.out.println("September: "+lowiceMonth(sep));
		System.out.println("October: "+lowiceMonth(oct));
		System.out.println("November: "+lowiceMonth(nov));
		System.out.println("December: "+lowiceMonth(dec));

		//Prints difference in ice area between each year and previous year per month
		System.out.println("\n<Year to Year Difference in Sea Ice Area per Month>");
		year2yearIce(jan);
		year2yearIce(feb);
		year2yearIce(mar);
		year2yearIce(apr);
		year2yearIce(may);
		year2yearIce(jun);
		year2yearIce(jul);
		year2yearIce(aug);
		year2yearIce(sep);
		year2yearIce(oct);
		year2yearIce(nov);
		year2yearIce(dec);

		//Prints average ice area per month across all years
		double[] avg = avgAreaMonth(data);
		System.out.println("\n<Average Sea Ice Area Per Month Across All Years>");
		for(int i = 0; i<12; i++) {
			System.out.println("Month "+(i+1)+":");
			System.out.println("Average Sea Ice Area: "+avg[i]+" million km^2\n");
		}

		System.out.println("\n<Month with Lowest Average Sea Ice Area>");
		double lowestAvg = 0;
		int lowestMonth = 0;
		for(int i = 0; i<12; i++) {
			if(lowestAvg == 0 & lowestMonth == 0|| avg[i] < lowestAvg) {
				lowestAvg = avg[i];
				lowestMonth = i+1;
			}
		}
		System.out.println("Month "+lowestMonth+" with "+lowestAvg+" million km^2");

		System.out.println("\n<Month with Highest Average Sea Ice Area>");
		double highestAvg = 0;
		int highestMonth = 0;
		for(int i = 0; i<12; i++) {
			if(highestAvg == 0 & highestMonth == 0|| avg[i] > highestAvg) {
				highestAvg = avg[i];
				highestMonth = i+1;
			}
		}
		System.out.println("Month "+highestMonth+" with "+highestAvg+" million km^2");
		System.out.println();

		double lowDiff = 0;
		int countLow = 0;
		for(Map.Entry<Integer, SeaIce> entry : data.entrySet()) {
			if(entry.getValue().mo == lowestMonth) {
				if(countLow > 0) {
					lowDiff += (entry.getValue().area - data.get(data.lowerKey(entry.getKey())).area);
				}
				countLow++;
			}
		}
		double avgChange = lowDiff/countLow;
		System.out.println("Average Change In Ice Area In Month With Lowest Average Area: "+avgChange+" million km^2");

		System.out.println("\nNo Ice By The Year: "+noIce(avgChange, sep));
	}
}