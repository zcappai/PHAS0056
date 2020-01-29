package exam2;

import java.util.*;

public class SNWD  implements Measurement{

	public ArrayList<Readings> name(ArrayList<Readings> list) {
		ArrayList<Readings> SNWD = new ArrayList<Readings>();
		for(Readings x : list) {
			if(x.type.equals("SNWD")) {
				SNWD.add(x);
			}
		}
		return SNWD;
	}
}