package module5;

public class Theory {

	//Member variable
	int n; //Power on x-coordinate

	/**
	 * CONSTRUCTOR USED TO SET UP 'Theory' OBJECT
	 * @param n
	 */
	public Theory(int n) {
		//Assigns member variable to argument
		this.n = n;
	}

	/**
	 * CALCULATES VALUE OF FUNCTION y=x^n
	 * @param x-coordinate
	 * @return
	 */
	public double y(double x) {
		return Math.pow(x, n);
	}
}