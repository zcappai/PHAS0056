package module2;

public class TextComplex {

	public static void main(String[] args) {
		Complex c1 = new Complex(1, -2);
		Complex c2 = new Complex(-2, 1);
		System.out.println("Product (c1*c2): "+Complex.multiply(c1, c2));
		System.out.println("Ratio (c1/c2): "+Complex.divide(c1, c2));
		System.out.println("Product (c1*I): "+Complex.multiply(c1, Complex.I));
		System.out.println("Ratio (c1/ZERO): "+Complex.divide(c1, Complex.ZERO));
		System.out.println("Product (c1*conjugate): "+Complex.multiply(c1, c1.conjugate()));
		System.out.println("Product (c2*conjugate): "+Complex.multiply(c2, c2.conjugate()));

	}

}
