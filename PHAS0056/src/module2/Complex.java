package module2;

public class Complex {

	double real, img;

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
		double ang = Math.atan(Math.abs(img/real));
		if(real<0 & img>0) {
			ang = Math.PI - ang;
		}
		if(real>0 & img<0) {
			ang = 2*Math.PI - ang;
		}
		if(real<0 & img<0) {
			ang = Math.PI + ang;
		}/////////////////////////////////////////////How to fix problem of (0, -1) and (-1, 0), etc!
		return ang/Math.PI;
	}


	Complex conjugate() {
		if(img<0) {
			img = Math.abs(img);
			}
		if(img>0) {
			img = -img;
		}
		Complex c1 = new Complex(real, img);
		return c1;
	}
	
	Complex normalised() {
		double mod = this.modulus();
		Complex c1 = new Complex(real/mod, img/mod);
		return c1;
	}
	
	boolean equals(Complex c) {			//////////////doesn't work for negative numbers
		boolean test = false;
		if((real % c.real() == 0) && (img % c.imag() == 0)) {
			test = true;
		}
		return test;
	}
	
	public String toString() {
		if(Math.abs(img)/img == -1) {
			return real+""+img+"i";
		}
		return real+"+"+img+" i";
	}
	
	static Complex setFromModulusAngle(double mag, double ang) {		///////////static method? doesn't work unless static.
		double re = mag*Math.cos(ang);
		double im = mag*Math.sin(ang);
		Complex c = new Complex(re, im);
		return c;
	}

	public static void main(String[] args) {

		Complex c1 = new Complex(3, 3);
		Complex c2 = new Complex(3, 3);
		System.out.println("Real: "+c1.real());
		System.out.println("Imaginary: "+c1.imag());
		System.out.println("Modulus: "+c1.modulus());
		System.out.println("Argument: "+c1.angle()+" radians");
		System.out.println("Conjugate: "+c1.conjugate());
		System.out.println("Normalised: "+c1.normalised());
		System.out.println("Equal Number: "+c1.equals(c2));
		System.out.println("Modulus and Angle: "+setFromModulusAngle(1, Math.PI/2));
	}

}
