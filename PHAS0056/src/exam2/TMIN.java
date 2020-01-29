package exam2;

import java.util.*;

public class TMIN  implements Measurement{

	public ArrayList<Readings> name(ArrayList<Readings> list) {
		ArrayList<Readings> TMIN = new ArrayList<Readings>();
		for(Readings x : list) {
			if(x.type.equals("TMIN")) {
				TMIN.add(x);
			}
		}
		return TMIN;
	}
}