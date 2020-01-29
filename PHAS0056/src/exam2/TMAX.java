package exam2;

import java.util.*;

public class TMAX implements Measurement{

	public ArrayList<Readings> name(ArrayList<Readings> list) {
		ArrayList<Readings> TMAX = new ArrayList<Readings>();
		for(Readings x : list) {
			if(x.type.equals("TMAX")) {
				TMAX.add(x);
			}
		}
		return TMAX;
	}
}