package module3;
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
		double mod = Math.sqrt(real*real + img*img);
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

	//AMENDED SECTION
	//CALCULATES NORMALISED FORM OF COMPLEX NUMBER (MODULUS = 1)
	public Complex normalised() throws Exception{ //Specifies that method can throw an exception
		//Calculates by dividing real and imaginary parts by modulus
		double mod = this.modulus(); //Modulus of complex number

		//Throws exception if modulus of complex number is zero
		if(mod == 0) {
			//Error message printed when exception thrown
			throw new Exception("Modulus is zero. Division by zero not allowed. Please enter non-zero complex number!");
		}
		double renorm = real/mod; //Real part normalised
		double imgnorm = img/mod; //Imaginary part normalised
		//Returns complex number by creating new 'Complex' object with normalised real and imaginary parts
		return new Complex(renorm, imgnorm);
	}

	//RETURNS BOOLEAN OF 'true' IF CURRENT AND ARGUMENT COMPLEX NUMBERS ARE EQUAL
	public boolean equals(Complex c) { //'Complex' object as complex number argument

		//'true' if absolute difference between corresponding real and imaginary parts is less than tolerance 
		if(real == c.real() & img == c.imag()) {
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
		double re = mag*Math.cos(ang); //Real part of complex number
		double im = mag*Math.sin(ang); //Imaginary part of complex number
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

	//AMENDED SECTION
	//CALCULATES RATIO OF 2 COMPLEX NUMBERS
	public static Complex divide(Complex c1, Complex c2) throws IllegalArgumentException{ //Specifies that method can throw an exception
		//Throws exception if both real and imaginary parts of c2 are zero
		if(c2.real() == 0 & c2.imag() == 0) {
			//Error message printed when exception thrown
			throw new IllegalArgumentException("2nd complex number is zero. Division by zero not allowed. Please enter non-zero complex number!");
		}
		//Calculates using equation: (a+bi)/(c+di) = (a+bi)(c-di)/(c^2+d^2)
		Complex num = multiply(c1, c2.conjugate()); //Numerator of above equation
		double denom = c2.real()*c2.real() + c2.imag()*c2.imag();
		return new Complex(num.real()/denom, num.imag()/denom); //Numerator divided by denominator for complex number
	}

	public static void main(String[] args) {

		//2 'Complex' objects created to test class
		Complex c1 = new Complex(0, 0);
		Complex c2 = new Complex(1, 2);

		//Testing try and catch structures
		//Exception thrown when normalising zero complex number
		try {
			Complex norm = c1.normalised();
			System.out.println(norm);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
		//Exception thrown when dividing by zero complex number
		try {
			Complex div = divide(c2, c1);
			System.out.println(div);
		}
		catch (Exception e2) {
			System.out.println(e2);
		}
	}
}
