package MidTerm2017;

public class SeaIce {

	//Member variables
	int year; //Year of measurement
	int mo; //Month of measurement
	String data_type; //Data source used to compile data
	String hemi; //Region of sea ice
	double extent; //Total area of land or sea covered by ice in millions of km^2
	double area; //Total area of actual ice in millions of km^2

	//Constructor with arguments for all sea ice data
	public SeaIce(int year, int mo, String data_type, String hemi, double extent, double area) {
		this.year = year;
		this.mo = mo;
		this.data_type = data_type;
		this.hemi = hemi;
		this.extent = extent;
		this.area = area;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
