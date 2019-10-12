package module2;
public class Complex {

	//Member Variables
	//Can be used by and shared between methods defined in class
	double real; //Real part
	double img;	//Imaginary part

	//Static Member Variables
	//All instances of the class share the same variable
	public static Complex ONE = new Complex(1, 0); //(1+0i)
	public static Complex ZERO = new Complex(0, 0); //(0+0i)
	public static Complex I = new Complex(0, 1); //(0+i)

	//Constructor
	//Used to set up 'Complex' object when creating new 'Complex' object using 'new' command
	public Complex(double r, double i) { //2 'double' variable arguments for real and imaginary parts

		//Assigns member variables to each part of 'Complex' object
		//r and i refer to real and imaginary parts of 'Complex' object
		this.real = r;
		this.img = i;
	}

	//RETURNS REAL PART OF COMPLEX NUMBER
	public double real() {
		return real; //Returns real part
	}

	//RETURNS IMAGINARY PART OF COMPLEX NUMBER
	public double imag() {
		return img; //Returns imaginary part
	}

	//CALCULATES MODULUS OF COMPLEX NUMBER
	public double modulus() {
		//Calculates by squaring real and imaginary parts, summing and then square rooting the sum
		double mod = Math.sqrt(Math.pow(real, 2) + Math.pow(img, 2));
		return mod; //Returns modulus
	}

	//CALCULATES ARGUMENT OF COMPLEX NUMBER
	//Measured anti-clockwise from positive real axis on Argand diagram
	public double angle() {
		//'Math.atan2' converts from Cartesian to polar coordinates and calculates inverse tangent
		double ang = Math.atan2(img, real); //Angle given between -pi and pi

		//Adjusts arguments to give angles between 0 and 2pi
		if(img<0) { //If complex number is in lower 2 quadrants of Argand diagram (negative imaginary part)
			ang = ang + 2*Math.PI; //Adds 2pi when angle between 0 and -pi
		}
		return ang; //Returns argument in radians
	}

	//CALCULATES COMPLEX CONJUGATE OF COMPLEX NUMBER
	public Complex conjugate() {
		//Returns complex number by creating new 'Complex' object with real and negative imaginary parts
		return new Complex(real, -img);
	}

	//CALCULATES NORMALISED FORM OF COMPLEX NUMBER (MODULUS = 1)
	public Complex normalised() {
		//Calculates by dividing real and imaginary parts by modulus
		double mod = this.modulus(); //Modulus of complex number
		double renorm = real/mod; //Real part normalised
		double imgnorm = img/mod; //Imaginary part normalised
		//Returns complex number by creating new 'Complex' object with normalised real and imaginary parts
		return new Complex(renorm, imgnorm);
	}

	//RETURNS BOOLEAN OF 'true' IF CURRENT AND ARGUMENT COMPLEX NUMBERS ARE EQUAL
	public boolean equals(Complex c) { //'Complex' object as complex number argument
		double tolerance = 0.0001; //'tolerance' level required due to 'double' variables

		//'true' if absolute difference between corresponding real and imaginary parts is less than tolerance 
		if(Math.abs(real - c.real())<tolerance & Math.abs(img - c.imag())<tolerance) {
			return true;
		}
		//'false' if absolute difference between corresponding real and imaginary parts is greater than tolerance
		else {
			return false;
		}
	}

	//CONVERTS OBJECT TO STRING TO PRINT 'Complex' OBJECTS AS COMPLEX NUMBER
	public String toString() {
		return real+" + "+img+" i"; //Complex number string layout (a+bi)
	}

	//CALCULATES COMPLEX NUMBER FROM GIVEN MODULUS AND ARGUMENT
	//Argument measured anti-clockwise from positive real axis on Argand diagram
	static Complex setFromModulusAngle(double mag, double ang) { //2 'double' variables for modulus and argument as arguments
		//Calculates by multiplying magnitude with cosine (real) or sine (imaginary) of argument
		double tolerance = 0.0001; //'tolerance' level required due to 'double' variables
		double re = mag*Math.cos(ang); //Real part of complex number
		double im = mag*Math.sin(ang); //Imaginary part of complex number

		//Imaginary part is 0 if absolute difference between 0 and imaginary part is less than tolerance
		if(Math.abs(0 - im)<tolerance) {
			im = 0;
		}
		//Real part is 0 if absolute difference between 0 and real part is less than tolerance
		else if(Math.abs(0 - re)<tolerance) {
			re = 0;
		}
		//Returns complex number by creating new 'Complex' object with calculated real and imaginary parts
		return new Complex(re, im);
	}

	//CALCULATES SUM OF 2 COMPLEX NUMBERS
	public static Complex add(Complex c1, Complex c2) {  //2 'Complex' objects as complex number arguments
		//Calculates by summing corresponding real and imaginary parts
		double real = c1.real() + c2.real(); //Real part
		double img = c1.imag() + c2.imag(); //Imaginary part
		//Returns complex number by creating new 'Complex' object with summed real and imaginary parts
		return new Complex(real, img);
	}

	//CALCULATES DIFFERENCE BETWEEN 2 COMPLEX NUMBERS
	public static Complex subtract(Complex c1, Complex c2) {  //2 'Complex' objects as complex number arguments
		//Calculates by subtracting corresponding real and imaginary parts (e.g. c1-c2)
		double real = c1.real() - c2.real(); //Real part
		double img = c1.imag() - c2.imag(); //Imaginary part
		//Returns complex number by creating new 'Complex' object with subtracted real and imaginary parts
		return new Complex(real, img);
	}

	//CALCULATES PRODUCT OF 2 COMPLEX NUMBERS
	public static Complex multiply(Complex c1, Complex c2) {  //2 'Complex' objects as complex number arguments
		//Calculates using equation: (a+bi)(c+di) = (ac-bd)+(bc+ad)i
		double real = c1.real()*c2.real() - c1.imag()*c2.imag(); //Real part
		double img = c1.imag()*c2.real() + c1.real()*c2.imag(); //Imaginary part
		//Returns complex number by creating new 'Complex' object with real and imaginary parts
		return new Complex(real, img);
	}

	//CALCULATES RATIO OF 2 COMPLEX NUMBERS
	public static Complex divide(Complex c1, Complex c2) {  //2 'Complex' objects as complex number arguments
		//Calculates using equation: (a+bi)/(c+di) = (a+bi)(c-di)/(c^2+d^2)
		Complex num = multiply(c1, c2.conjugate()); //Numerator of above equation
		double denom = Math.pow(c2.real(), 2) + Math.pow(c2.imag(), 2); //Denominator of above equation
		//Returns complex number by creating new 'Complex' object with real and imaginary parts
		return new Complex(num.real()/denom, num.imag()/denom); //Numerator divided by denominator for complex number
	}

	public static void main(String[] args) {

		//2x 'Complex' objects created to test class
		Complex c1 = new Complex(-1, -2);
		Complex c2 = new Complex(3, 4);

		//Print statements to test class
		System.out.println("Real (c1): "+c1.real());
		System.out.println("Imaginary (c1): "+c1.imag());
		System.out.println("Modulus (c1): "+c1.modulus());
		System.out.println("Argument (c1): "+c1.angle()+" radians");
		System.out.println("Conjugate (c1): "+c1.conjugate());
		System.out.println("Normalised (c1): "+c1.normalised());
		System.out.println("Equal Number (c1): "+c1.equals(c2));
		System.out.println("\nFrom Modulus and Angle: "+setFromModulusAngle(1, 1.75*Math.PI));
		System.out.println("\nAdd: "+add(c1, c2));
		System.out.println("Subtract: "+subtract(c1, c2));
		System.out.println("Multiply: "+multiply(c1, c2));
		System.out.println("Divide: "+divide(c1, c2));
	}
}
