package module2;
public class TestComplex {
	public static void main(String[] args) {

		//2 'Complex' objects created to form 2 complex numbers
		Complex c1 = new Complex(1, -2);
		Complex c2 = new Complex(-2, 1);

		//Print statements to output products and ratios of 2 complex numbers
		System.out.println("Product (c1*c2): "+Complex.multiply(c1, c2));
		System.out.println("Ratio (c1/c2): "+Complex.divide(c1, c2));
		System.out.println("\nProduct (c1*I): "+Complex.multiply(c1, Complex.I));
		System.out.println("Ratio (c1/ZERO): "+Complex.divide(c1, Complex.ZERO));
		System.out.println("(Sum of real and imaginary parts squared for 'ZERO' complex number is zero,");
		System.out.println("but cannot divide by 0. So cannot divide 'c1' by 'ZERO'.)");
		System.out.println("\nProduct (c1*conjugate): "+Complex.multiply(c1, c1.conjugate()));
		System.out.println("Product (c2*conjugate): "+Complex.multiply(c2, c2.conjugate()));
	}
}