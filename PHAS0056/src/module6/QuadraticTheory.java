package module6;

public class QuadraticTheory implements Theory {

	double a;
	double b;
	double c;

	public QuadraticTheory(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double y(double x) {
		return a*x*x + b*x + c;
	}

	@Override
	public String toString() {
		return a + "x^2 " + b + "x " + c;
	}
}
