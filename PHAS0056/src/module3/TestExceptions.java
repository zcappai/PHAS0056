package module3;
public class TestExceptions {
	public static void main(String[] args) {

		//COMPLEX
		//2 'Complex' objects created to test exceptions
		Complex c1 = new Complex(0, 0);
		Complex c2 = new Complex(1, 2);

		/*Tries to normalise and print complex number 'c1', but method throws an exception
		due to modulus of zero. Exception is caught and error message printed*/
		try {
			Complex norm = c1.normalised();
			System.out.println(norm);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}

		/*Tries to divide complex number 'c2' by 'c1', but method throws exception due to
		division by zero complex number. Exception is caught and error message printed*/
		try {
			Complex div = Complex.divide(c2, c1);
			System.out.println(div);
		}
		catch (Exception e2) {
			System.out.println(e2);
		}

		//THREEVECTOR
		//2 'ThreeVector' objects created to test exceptions
		ThreeVector vector1 = new ThreeVector(0, 0, 0);
		ThreeVector vector2 = new ThreeVector(1, 2, 3);

		/*Tries to calculate unit vector of zero vector, but method throws exception due to
		division by zero magnitude of vector. Exception is caught and error message printed*/
		try {
			ThreeVector v1 = vector1.unitVector();
			System.out.println(v1);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}

		/*Tries to calculate angle between a vector and zero vector, but method throws exception
		due to division by zero magnitude of zero vector. Exception is caught and error message printed*/
		try {
			double v2 = ThreeVector.angle(vector1, vector2);
			System.out.println(v2);
		}
		catch (Exception e2) {
			System.out.println(e2);
		}

		//FALLINGPARTICLE
		//'FallingParticle' object created to test exceptions
		FallingParticle p1 = new FallingParticle(5, 2);

		/*Tries to set negative starting height. Method throws exception due to unphysical
		height for falling particle model. Exception is caught and error message printed*/
		try {
			p1.setH(-5);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}

		/*Tries to run simulated drop with time step of 0.0s. Method throws exception due to unphysical
		time step as method would run indefinitely. Exception is caught and error message printed*/
		try {
			p1.drop(0.0);
		}
		catch (IllegalArgumentException e1) {
			System.out.println(e1);
		}

		/*Tries to set negative mass of particle. Method throws exception due to unphysical
		mass for the falling particle. Exception is caught and error message printed*/
		try {
			FallingParticle p2 = new FallingParticle(-5, 2);
			System.out.println(p2);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}

		/*Tries to set negative drag coefficient. Method throws exception due to unphysical
		drag coefficient. Exception is caught and error message printed*/
		try {
			FallingParticle p3 = new FallingParticle(5, -2);
			System.out.println(p3);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
	}
}
