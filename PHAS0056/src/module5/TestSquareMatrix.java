package module5;

public class TestSquareMatrix {
	public static void main(String[] args) {

		//Creating 4 'SquareMatrix' objects to represent test matrices
		SquareMatrix A = null;
		SquareMatrix B = null;
		SquareMatrix C = null;
		SquareMatrix D = null;

		//Creating 4x 2D square arrays for contents of matrices
		double[][] a = new double[][] {{2, 1, 0}, {0, 1, 0}, {-1, 0, 2}};
		double[][] b = new double[][] {{1, 3, 1}, {0, 2, 0}, {1, 0, -1}};
		double[][] c = new double[][] {{2, 3}, {3, 4}};
		double[][] d = new double[][] {{-4, 3}, {3, -2}};

		/**
		 * Tries to create matrix A using 2D square array
		 * Catches exception if 2D array NOT square
		 */
		try {
			A = new SquareMatrix(a);
			System.out.println("Matrix A:\n"+A);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to create matrix B using 2D square array
		 * Catches exception if 2D array NOT square
		 */
		try {
			B = new SquareMatrix(b);
			System.out.println("Matrix B:\n"+B);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to create matrix C using 2D square array
		 * Catches exception if 2D array NOT square
		 */
		try {
			C = new SquareMatrix(c);
			System.out.println("Matrix C:\n"+C);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to create matrix D using 2D square array
		 * Catches exception if 2D array NOT square
		 */
		try {
			D = new SquareMatrix(d);
			System.out.println("Matrix D:\n"+D);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to calculate sum of matrix A and B
		 * Catches exception if dimensions of both matrices NOT identical
		 */
		try {
			SquareMatrix sumAB = SquareMatrix.add(A, B);
			System.out.println("Sum of A and B:\n"+sumAB);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to calculate subtraction of matrix B from A
		 * Catches exception if dimensions of both matrices NOT identical
		 */
		try {
			SquareMatrix minusAB = SquareMatrix.subtract(A, B);
			System.out.println("Subtraction of B from A:\n"+minusAB);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to calculate product of matrix A and B
		 * Catches exception if dimensions of both matrices NOT identical
		 * Matrix initialised outside try-catch as it will be used later
		 */
		SquareMatrix timesAB = null;
		try {
			timesAB = SquareMatrix.multiply(A, B);
			System.out.println("Product of A and B:\n"+timesAB);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to calculate product of matrix B and A
		 * Catches exception if dimensions of both matrices NOT identical
		 * Matrix initialised outside try-catch as it will be used later
		 */
		SquareMatrix timesBA = null;
		try {
			timesBA = SquareMatrix.multiply(B, A);
			System.out.println("Product of B and A:\n"+timesBA);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to calculate commutator [A, B] = AB - BA
		 * Subtracts previously calculated products, AB and BA
		 * Catches exception if dimensions of both matrices NOT identical
		 */
		try {
			SquareMatrix comAB = SquareMatrix.subtract(timesAB, timesBA);
			System.out.println("Commutator of A and B:\n"+comAB);
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/**
		 * Tries to calculate product of matrix C and D
		 * Checks if results is equal to 2x2 unit matrix
		 * Catches exception if dimensions of both matrices NOT identical
		 */
		try {
			SquareMatrix timesCD = SquareMatrix.multiply(C, D);
			System.out.println("Product of C and D:\n"+timesCD);

			if(timesCD.equals(SquareMatrix.unitMatrix(2))) {
				System.out.println("Product CD is equal to 2x2 unit matrix!");
			}
			else {
				System.out.println("Product CD is NOT equal to 2x2 unit matrix!");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
