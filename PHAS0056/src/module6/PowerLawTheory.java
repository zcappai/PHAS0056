package module6;

/* Implements 'Theory' interface to create method for calculating 'y' values,
 * toString' method and 'PowerLawTheory' constructor also created */
public class PowerLawTheory implements Theory {

	//Member variable
	double n; //Power of x-coordinate

	/**
	 * CONSTRUCTOR USED TO SET UP 'PowerLawTheory' OBJECT TO SET VALUE OF POWER
	 * @param n - power of x-coordinate
	 */
	public PowerLawTheory(double n) {
		this.n = n; //Assigns member variable to corresponding argument
	}

	/**
	 * CALCULATES FUNCTION FOR POWER LAW THEORY
	 * Function Used: "y = x^n"
	 */
	public double y(double x) {
		return Math.pow(x, n);
	}

	/**
	 * REPRESENTS POWER FUNCTION BEING USED, AS STRING
	 */
	@Override
	public String toString() {
		return "y = x^" + n;
	}
}