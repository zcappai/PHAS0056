package module2;

public class Complex {

	double real; //real part
	double img;	//imaginary part
	
	public static Complex ONE = new Complex(1, 0);
	public static Complex ZERO = new Complex(0, 0);
	public static Complex I = new Complex(0, 1);

	public Complex(double r, double i) {
		this.real = r;
		this.img = i;
	}

	double real() {
		return real;
	}

	double imag() {
		return img;
	}

	double modulus() {
		double mod = Math.sqrt(Math.pow(real, 2) + Math.pow(img, 2));
		return mod;
	}

	double angle() {
		double ang = Math.atan2(img, real);
//		double tolerance = 0.001;
//		if(img<0) {
//			ang = Math.PI - ang;
//		}
//		if(real>0 & img<0) {
//			ang = 2*Math.PI - ang;
//		}
//		if(real<0 & img<0) {
//			ang = Math.PI + ang;
//		}/////////////////////////////////////////////How to fix problem of (0, -1) and (-1, 0), etc!
		return ang/Math.PI;
	}

	Complex conjugate() {
		Complex c1 = new Complex(real, -img);
		return c1;
	}
	
	Complex normalised() {
		double mod = this.modulus();
		double renorm = real/mod;
		double imgnorm = img/mod;
		Complex c1 = new Complex(renorm, imgnorm);
		return c1;
	}
	
	boolean equals(Complex c) {			//////////////doesn't work for negative numbers, and need to account for tolerance
		boolean test = false;
		if((real % c.real() == 0) && (img % c.imag() == 0)) {
			test = true;
		}
		return test;
	}
	
	public String toString() {
		return real+" + "+img+" i";			//////////If statement to account for negative img
	}
	//arg as measured anti-clockwise from positive real axis, as stated in exercise notes
	static Complex setFromModulusAngle(double mag, double ang) {		///////////static method? doesn't work unless static.
		double re = mag*Math.cos(ang);
		double im = mag*Math.sin(ang);
		Complex c = new Complex(re, im);
		return c;
	}

	static Complex add(Complex c1, Complex c2) {
		double real = c1.real() + c2.real();
		double img = c1.imag() + c2.imag();
		Complex c = new Complex(real, img);
		return c;
	}
	
	static Complex subtract(Complex c1, Complex c2) {
		double real = c1.real() - c2.real();
		double img = c1.imag() - c2.imag();
		Complex c = new Complex(real, img);
		return c;
	}

	static Complex multiply(Complex c1, Complex c2) {
		double real = c1.real()*c2.real() - c1.imag()*c2.imag();
		double img = c1.imag()*c2.real() + c1.real()*c2.imag();
		Complex c = new Complex(real, img);
		return c;
	}

	static Complex divide(Complex c1, Complex c2) {
		double renum = c1.real()*c2.real() + c1.imag()*c2.imag();
		double redenom = Math.pow(c2.real(), 2) + Math.pow(c2.imag(), 2);
		double imnum = c1.imag()*c2.real() - c1.real()*c2.imag();
		double imdenom = Math.pow(c2.real(), 2) + Math.pow(c2.imag(), 2);
		double real = renum/redenom;
		double img = imnum/imdenom;
		Complex c = new Complex(real, img);
		return c;
	}
	
	public static void main(String[] args) {

		Complex c1 = new Complex(-1, -1);
		Complex c2 = new Complex(1, 2);
		System.out.println("Real: "+c1.real());
		System.out.println("Imaginary: "+c1.imag());
		System.out.println("Modulus: "+c1.modulus());
		System.out.println("Argument: "+c1.angle()+"pi radians");
		System.out.println("Conjugate: "+c1.conjugate());
		System.out.println("Normalised: "+c1.normalised());			////////normalised method gives wrong answer when after conjugate
		System.out.println("Equal Number: "+c1.equals(c2));
		System.out.println("Modulus and Angle: "+setFromModulusAngle(1, 0.5*Math.PI));
		System.out.println("Add: "+add(c1, c2));
		System.out.println("Subtract: "+subtract(c1, c2));
		System.out.println("Multiply: "+multiply(c1, c2));
		System.out.println("Divide: "+divide(c1, c2));
	}

}
