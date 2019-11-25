package module6;

//SUPERCLASS
//Creates constructor for 'DataPoint' object, 'getter' and 'toString' methods
public class DataPoint {

	//Member variables
	protected double x; //x-coordinate
	protected double y; //y-coordinate
	protected double ey; //error on y-coordinate

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

	/**
	 * REPRESENTS 'DataPoint' OBJECT AS STRING
	 */
	@Override
	public String toString() {
		return "x = " + getX() + ", y = " + getY() + " +- " + getEY();
	}
}