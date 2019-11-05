package module5;

public class DataPoint {

	//Member variables
	double x; //x-coordinate
	double y; //y-coordinate
	double ey; //error on y-coordinate

	/**
	 * CONSTRUCTOR USED TO SET UP 'DataPoint' OBJECT
	 * @param x
	 * @param y
	 * @param ey
	 */
	public DataPoint(double x, double y, double ey) {
		//Assigns member variables to corresponding arguments
		this.x = x;
		this.y = y;
		this.ey = ey;
	}

	/**
	 * GETS VALUE OF X-COORDINATE
	 * @return
	 */
	public double getX() {	
		return x;
	}

	/**
	 * GETS VALUE OF Y-COORDINATE
	 * @return
	 */
	public double getY() {	
		return y;
	}

	/**
	 * GETS VALUE OF ERROR ON Y
	 * @return
	 */
	public double getEY() {	
		return ey;
	}
}