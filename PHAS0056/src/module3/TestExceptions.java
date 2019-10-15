package module3;

public class TestExceptions {

	public static void main(String[] args) {

		//COMPLEX
		Complex c1 = new Complex(0, 0);
		Complex c2 = new Complex(1, 2);

		try {
			Complex norm = c1.normalised();
			System.out.println(norm);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
		try {
			Complex div = Complex.divide(c2, c1);
			System.out.println(div);
			
		}
		catch (Exception e2) {
			System.out.println(e2);
		}

		//THREEVECTOR
		ThreeVector vector1 = new ThreeVector(0, 0, 0);
		ThreeVector vector2 = new ThreeVector(1, 2, 3);

		try {
			ThreeVector v1 = vector1.unitVector();
			System.out.println(v1);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
		try {
			double v2 = ThreeVector.angle(vector1, vector2);
			System.out.println(v2);
		}
		catch (Exception e2) {
			System.out.println(e2);
		}

		//FALLINGPARTICLE
		FallingParticle p1 = new FallingParticle(5, 2);

		try {
			p1.setH(-5);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
		try {
			p1.drop(0.0);
		}
		catch (IllegalArgumentException e1) {
			System.out.println(e1);
		}
		try {
			FallingParticle p2 = new FallingParticle(-5, 2);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
		try {
			FallingParticle p3 = new FallingParticle(5, -2);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}

	}

}
