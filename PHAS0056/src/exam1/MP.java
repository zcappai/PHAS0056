package exam1;

public class MP {

	//Member variables
	String ONS;
	int PANO;
	String constit; //Constituency
	String lname; //Candidate's last name
	String fname; //Candidate's first name
	String party;
	String partyID; //Party identifier
	int votes;

	//Constructor with arguments for all election data
	public MP(String ONS, int PANO, String constit, String lname, String fname, String party, String partyID, int votes) {
		this.ONS = ONS;
		this.PANO = PANO;
		this.constit = constit;
		this.lname = lname;
		this.fname = fname;
		this.party = party;
		this.partyID = partyID;
		this.votes = votes;
	}

	public static void main(String[] args) {

	}

}
