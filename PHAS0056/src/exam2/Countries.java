package exam2;

import java.util.*;

public class Countries {

	final String reg_ID;
	final String reg_name;

	public Countries(String line) {
		try(Scanner sc = new Scanner(line)) {
			StringBuilder str = new StringBuilder(); //To Construct Country/Region Name
			this.reg_ID = sc.next();
			while(sc.hasNext()) {
				str.append(sc.next());
				str.append(" ");
			}
			this.reg_name = str.toString();
		}
	}
}