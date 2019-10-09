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

	public double real() {
		return real;
	}

	public double imag() {
		return img;
	}

	public double modulus() {
		double mod = Math.sqrt(Math.pow(real, 2) + Math.pow(img, 2));
		return mod;
	}

	public double angle() {
		double ang = Math.atan2(img, real);
		if(img<0) {
			ang = ang + 2*Math.PI;
		}
		return ang/Math.PI;
	}

	public Complex conjugate() {
		Complex c1 = new Complex(real, -img);
		return c1;
	}
	
	public Complex normalised() {
		double mod = this.modulus();
		double renorm = real/mod;
		double imgnorm = img/mod;
		Complex c1 = new Complex(renorm, imgnorm);
		return c1;
	}
	
	public boolean equals(Complex c) {
		double tolerance = 0.0001;
		if(Math.abs(real - c.real())<tolerance & Math.abs(img - c.imag())<tolerance) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return real+" + "+img+" i";			//////////If statement to account for negative img
	}
	//arg as measured anti-clockwise from positive real axis, as stated in exercise notes
	static Complex setFromModulusAngle(double mag, double ang) {
		double re = mag*Math.cos(ang);
		double im = mag*Math.sin(ang);
		Complex c = new Complex(re, im);
		return c;
	}

	public static Complex add(Complex c1, Complex c2) {
		double real = c1.real() + c2.real();
		double img = c1.imag() + c2.imag();
		Complex c = new Complex(real, img);
		return c;
	}
	
	public static Complex subtract(Complex c1, Complex c2) {
		double real = c1.real() - c2.real();
		double img = c1.imag() - c2.imag();
		Complex c = new Complex(real, img);
		return c;
	}

	public static Complex multiply(Complex c1, Complex c2) {
		double real = c1.real()*c2.real() - c1.imag()*c2.imag();
		double img = c1.imag()*c2.real() + c1.real()*c2.imag();
		Complex c = new Complex(real, img);
		return c;
	}

	public static Complex divide(Complex c1, Complex c2) {
		Complex num = multiply(c1, c2.conjugate());
		double denom = Math.pow(c2.real(), 2) + Math.pow(c2.imag(), 2);
		Complex c = new Complex(num.real()/denom, num.imag()/denom);
		return c;
	}
	
	public static void main(String[] args) {

		Complex c1 = new Complex(1, 2);
		Complex c2 = new Complex(3, 4);
		System.out.println("Real: "+c1.real());
		System.out.println("Imaginary: "+c1.imag());
		System.out.println("Modulus: "+c1.modulus());
		System.out.println("Argument: "+c1.angle()+"pi radians");
		System.out.println("Conjugate: "+c1.conjugate());
		System.out.println("Normalised: "+c1.normalised());
		System.out.println("Equal Number: "+c1.equals(c2));
		System.out.println("Modulus and Angle: "+setFromModulusAngle(1, 0.5*Math.PI));
		System.out.println("Add: "+add(c1, c2));
		System.out.println("Subtract: "+subtract(c1, c2));
		System.out.println("Multiply: "+multiply(c1, c2));
		System.out.println("Divide: "+divide(c1, c2));
	}

}
