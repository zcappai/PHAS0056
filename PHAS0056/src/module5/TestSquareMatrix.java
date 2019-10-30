package module5;

public class TestSquareMatrix {
///////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		SquareMatrix A = null;
		SquareMatrix B = null;
		SquareMatrix C = null;
		SquareMatrix D = null;
		double[][] a = new double[][] {{2, 1, 0}, {0, 1, 0}, {-1, 0, 2}};
		double[][] b = new double[][] {{1, 3, 1}, {0, 2, 0}, {1, 0, -1}};
		double[][] c = new double[][] {{2, 3}, {3, 4}};
		double[][] d = new double[][] {{-4, 3}, {3, -2}};

		try {
			A = new SquareMatrix(a);
			System.out.println("Matrix A:\n"+A);
		}
		catch(Exception e1) {
			System.out.println(e1);
		}

		try {
			B = new SquareMatrix(b);
			System.out.println("Matrix B:\n"+B);
		}
		catch(Exception e2) {
			System.out.println(e2);
		}

		try {
			C = new SquareMatrix(c);
			System.out.println("Matrix C:\n"+C);
		}
		catch(Exception e3) {
			System.out.println(e3);
		}

		try {
			D = new SquareMatrix(d);
			System.out.println("Matrix D:\n"+D);
		}
		catch(Exception e4) {
			System.out.println(e4);
		}
	}

}
