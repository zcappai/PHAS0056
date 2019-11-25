package module6;

/* Implements 'Theory' interface to create method for calculating 'y' values
 * 'toString' method and 'QuadraticTheory' constructor also created */ 
public class QuadraticTheory implements Theory {

	//Member variables
	double a; //Coefficient of x^2
	double b; //Coefficient of x
	double c; //y-intercept

	/**
	 * CONSTRUCTOR USED TO SET UP 'QuadraticTheory' OBJECT TO SET VALUE OF COEFFICIENTS
	 * @param a
	 * @param b
	 * @param c
	 */
	public QuadraticTheory(double a, double b, double c) {
		//Assigns member variables to corresponding arguments
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * CALCULATES FUNCTION FOR QUADRATIC THEORY
	 * Function Used: "y = ax^2 + bx + c"
	 */
	public double y(double x) {
		return a*x*x + b*x + c;
	}

	/**
	 * REPRESENTS QUADRATIC FUNCTION BEING USED, AS STRING
	 */
	@Override
	public String toString() {
		return "y = " + a + "x^2 + " + b + "x + " + c;
	}
}