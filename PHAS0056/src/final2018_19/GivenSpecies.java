package final2018_19;

import java.util.*;

public class GivenSpecies implements ImageSelector{

	public ArrayList<Classification> select(ArrayList<Classification> expList, String species) {
		ArrayList<Classification> chosenSpecies = new ArrayList<>();
		for (Classification x : expList) {
			String s = x.species;
			if (s.equals(species)) {
				chosenSpecies.add(x);
			}
		}
		return chosenSpecies;
	}
}