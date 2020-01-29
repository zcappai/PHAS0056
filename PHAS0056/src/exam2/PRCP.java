package exam2;

import java.util.*;

public class PRCP  implements Measurement{

	public ArrayList<Readings> name(ArrayList<Readings> list) {
		ArrayList<Readings> PRCP = new ArrayList<Readings>();
		for(Readings x : list) {
			if(x.type.equals("PRCP")) {
				PRCP.add(x);
			}
		}
		return PRCP;
	}
}