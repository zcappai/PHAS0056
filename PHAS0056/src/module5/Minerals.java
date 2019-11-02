package module5;

import java.io.*;
import java.net.*;
import java.util.*;

public class Minerals {

	public static void maxminmass(String url1, String url2) throws Exception{
		HashMap<Integer, Double> mass = new HashMap<Integer, Double>();
		HashMap<Integer, String> loc = new HashMap<Integer, String>();
		URL u1 = new URL(url1);
		InputStream is1 = u1.openStream();
		InputStreamReader isr1 = new InputStreamReader(is1);
		BufferedReader reader1 = new BufferedReader(isr1);
		URL u2 = new URL(url2);
		InputStream is2 = u2.openStream();
		InputStreamReader isr2 = new InputStreamReader(is2);
		BufferedReader reader2 = new BufferedReader(isr2);
		String line = "";
		while ((line = reader1.readLine()) != null) {
			Scanner sc = new Scanner(line);
			while(sc.hasNext()) {
				Integer sample1 = Integer.parseInt(sc.next());
				Double mass1 = Double.parseDouble(sc.next());
				mass.put(sample1, mass1);
			}
			sc.close();
		}
		while ((line = reader2.readLine()) != null) {
			Scanner sc = new Scanner(line);
			while(sc.hasNext()) {
				String loc2 = sc.next();
				Integer sample2 = Integer.parseInt(sc.next());
				loc.put(sample2, loc2);
			}
			sc.close();
		}

		Map.Entry<Integer, Double> maxMass = null;
		for(Map.Entry<Integer, Double> entry : mass.entrySet()) {
			if(maxMass == null || entry.getValue() > maxMass.getValue()) {
				maxMass = entry;
			}
		}

		Map.Entry<Integer, Double> minMass = null;
		for(Map.Entry<Integer, Double> entry : mass.entrySet()) {
			if(minMass == null || entry.getValue() < minMass.getValue()) {
				minMass = entry;
			}
		}
		Integer maxKey = maxMass.getKey();
		Double maxValue = maxMass.getValue();
		String maxLoc = loc.get(maxKey);
		Integer minKey = minMass.getKey();
		Double minValue = minMass.getValue();
		String minLoc = loc.get(minKey);
		System.out.println("<<Sample with Largest Mass>>");
		System.out.println("Sample Code Number: "+maxKey);
		System.out.println("Sample Mass: "+maxValue);
		System.out.println("Sample Location: "+maxLoc);
		System.out.println("\n<<Sample with Smallest Mass>>");
		System.out.println("Sample Code Number: "+minKey);
		System.out.println("Sample Mass: "+minValue);
		System.out.println("Sample Location: "+minLoc);
		
	}

	public static void main(String[] args) {
		String url1 = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-samples.txt";
		String url2 = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-locations.txt";
		try {
			maxminmass(url1, url2);
		}
		catch(Exception e) {
			e.printStackTrace(System.out);
			System.out.println(e);
		}

	}

}
