package module5;
import java.util.*;

public class SquareMatrix {

	double matrix[][];

	public SquareMatrix(double[][] elements) throws Exception{
		matrix = elements;
		for(int i = 0; i < elements.length; i++) {
			if(elements.length != elements[i].length) {
				throw new Exception("Argument is NOT valid square matrix! Please enter valid square matrix!");
			}
		}
	}

	public String toString() {
		StringBuilder x = new StringBuilder();
		for(int i=0; i < matrix.length; i++) {
			x.append(Arrays.toString(matrix[i]));
			x.append("\n");
		}
		return x.toString();
	}

	public static SquareMatrix unitMatrix(int size) throws Exception{
		double[][] unit = new double[size][size];
		for(int i = 0; i < size; i++) {
			unit[i][i] = 1;			
		}
		SquareMatrix x = new SquareMatrix(unit);
		return x;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matrix);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SquareMatrix other = (SquareMatrix) obj;
		if (!Arrays.deepEquals(matrix, other.matrix))
			return false;
		return true;
	}

	public static SquareMatrix add(SquareMatrix sm1, SquareMatrix sm2) throws Exception{
		if(sm1.matrix.length != sm2.matrix.length) {
			throw new IllegalArgumentException("Dimensions of matrices not identical. Please enter matrices of identical size!");
		}

		double[][] sum = new double[sm1.matrix.length][sm1.matrix.length];

		for(int i = 0; i < sm1.matrix.length; i++) {
			for(int j = 0; j < sm1.matrix.length; j++) {
				sum[i][j] = sm1.matrix[i][j] + sm2.matrix[i][j];
			}
		}

		return new SquareMatrix(sum);
	}

	public static SquareMatrix subtract(SquareMatrix sm1, SquareMatrix sm2) throws Exception{
		if(sm1.matrix.length != sm2.matrix.length) {
			throw new IllegalArgumentException("Dimensions of matrices not identical. Please enter matrices of identical size!");
		}

		double[][] minus = new double[sm1.matrix.length][sm1.matrix.length];

		for(int i = 0; i < sm1.matrix.length; i++) {
			for(int j = 0; j < sm1.matrix.length; j++) {
				minus[i][j] = sm1.matrix[i][j] - sm2.matrix[i][j];
			}
		}

		return new SquareMatrix(minus);
	}

	public static SquareMatrix multiply(SquareMatrix sm1, SquareMatrix sm2) throws Exception{
		if(sm1.matrix.length != sm2.matrix.length) {
			throw new IllegalArgumentException("Dimensions of matrices not identical. Please enter matrices of identical size!");
		}

		double[][] times = new double[sm1.matrix.length][sm1.matrix.length];

		for(int i = 0; i < sm1.matrix.length; i++) {
			for(int j = 0; j < sm1.matrix.length; j++) {
				times[i][j] = 0;
				for(int k = 0; k < sm1.matrix.length; k++) {
					times[i][j] += sm1.matrix[i][k]*sm2.matrix[k][j];
				}
			}
		}

		return new SquareMatrix(times);
	}

	public static void main(String[] args) {
		SquareMatrix y1 = null;
		SquareMatrix y2 = null;
		double[][] x1 = new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		double[][] x2 = new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		try {
			y1 = new SquareMatrix(x1);
			System.out.println(y1);
		}
		catch(Exception e1) {
			System.out.println(e1);
		}

		try {
			y2 = new SquareMatrix(x2);
			System.out.println(y2);
		}
		catch(Exception e1) {
			System.out.println(e1);
		}

		try {
			SquareMatrix z = unitMatrix(3);
			System.out.println(z);
		}
		catch(Exception e2) {
			System.out.println(e2);
		}

		try {
			SquareMatrix sum = add(y1, y2);
			System.out.println(sum);
		}
		catch(Exception e3) {
			System.out.println(e3);
		}

		try {
			SquareMatrix minus = subtract(y1, y2);
			System.out.println(minus);
		}
		catch(Exception e4) {
			System.out.println(e4);
		}

		try {
			SquareMatrix times = multiply(y1, y2);
			System.out.println(times);
		}
		catch(Exception e5) {
			System.out.println(e5);
		}
		System.out.println(y1.equals(y2));


	}

}