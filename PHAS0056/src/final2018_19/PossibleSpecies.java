package final2018_19;

import java.util.*;

public class PossibleSpecies implements ImageSelector{

	public ArrayList<Classification> select(ArrayList<Classification> classList, String species) {
		ArrayList<Classification> chosenSpecies = new ArrayList<>();
		for(Classification x : classList){
			String s = x.species;
			if (s.equals(species)) {
				chosenSpecies.add(x);
			}
		}
		HashMap<Integer, Integer> imageSpecies = new HashMap<>();
		for(Classification x : chosenSpecies) {
			int s = x.imgID;
			Integer i = imageSpecies.get(s);
			if(i == null) {
				imageSpecies.put(s, 1);
			}
			else {
				imageSpecies.put(s, i+1);
			}
		}
		HashMap<Integer, Integer> allSpecies = new HashMap<>();
		for(Classification x : classList) {
			int s = x.imgID;
			Integer i = allSpecies.get(s);
			if(i == null) {
				allSpecies.put(s, 1);
			}
			else {
				allSpecies.put(s, i+1);
			}
		}
		HashMap<Integer, Double> candidate = new HashMap<>();
		for(Map.Entry<Integer, Integer> x : imageSpecies.entrySet()) {
			int ID = x.getKey();
			int specific = imageSpecies.get(ID);
			int non_specific = allSpecies.get(ID);
			double ratio = (double) specific/non_specific;
			if(ratio > 0.5) {
				candidate.put(ID, ratio);
			}
		}
		ArrayList<Classification> candidateList = new ArrayList<>();
		for(Classification x : classList) {
			int s = x.imgID;
			if(candidate.containsKey(s)) {
				candidateList.add(x);
			}
		}
		return candidateList;
	}
}