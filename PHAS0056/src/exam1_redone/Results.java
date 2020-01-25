package exam1_redone;

import java.util.*;

public class Results {

	final String ID;
	final double PANO;
	final String constit;
	final String surname;
	final String firstname;
	final String party;
	final String partyID;
	final double votes;

	public Results(String line) {
		try(Scanner sc = new Scanner(line)) {
			sc.useDelimiter(",");
			this.ID = sc.next();
			this.PANO = sc.nextDouble();
			this.constit = sc.next();
			this.surname = sc.next();
			this.firstname = sc.next();
			this.party = sc.next();
			this.partyID = sc.next();
			this.votes = sc.nextDouble();
		}
	}

	public String toString() {
		return "Constituency = " + constit + "\nName = " + firstname+" "+surname + "\nParty = " + party + ", Party ID = " + partyID + "\nVotes = " + votes;
	}
}