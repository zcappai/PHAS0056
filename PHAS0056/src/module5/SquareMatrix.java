package module5;
import java.util.*; //Importing input/output package

public class SquareMatrix {

	double matrix[][]; //Member variable for matrix

	/**
	 * CONSTRUCTOR TAKES 2D ARRAY OF DOUBLE VALUES AS ARGUMENT
	 * USED TO SET UP 'SquareMatrix' OBJECT
	 * @param elements - 2D array of doubles
	 * @throws Exception - IF 2D ARRAY IS NOT VALID SQUARE MATRIX
	 */
	public SquareMatrix(double[][] elements) throws Exception{
		matrix = elements; //Assigns matrix member variable to 2D array argument
		for(int i = 0; i < elements.length; i++) {
			if(elements.length != elements[i].length) { //If row lengths don't equal column lengths
				throw new Exception("Argument is NOT valid square matrix! Please enter valid square matrix!");
			}
		}
	}

	/**
	 * ALLOWS MATRIX TO BE PRINTED IN READABLE FORM
	 */
	public String toString() {
		//Matrix to be stored as string in 'StringBuilder' object
		StringBuilder x = new StringBuilder();
		/*Each array, within array of arrays, appended to new line
		in 'StringBuilder' object*/
		for(int i=0; i < matrix.length; i++) {
			x.append(Arrays.toString(matrix[i]));
			x.append("\n");
		}
		return x.toString();
	}

	/**
	 * OUTPUTS UNIT MATRIX OF SPECIFIED SIZE
	 * @param size of matrix
	 * @return
	 * @throws Exception - IF OUTPUT MATRIX NOT SQUARE
	 */
	public static SquareMatrix unitMatrix(int size) throws Exception{
		//Creates empty, square 2D array
		double[][] unit = new double[size][size];
		//Sets leading diagonal elements to 1's
		for(int i = 0; i < size; i++) {
			unit[i][i] = 1;			
		}
		//Returns square unit matrix
		return new SquareMatrix(unit);
	}

	/**
	 * OVERRIDING 'hashCode' SINCE 'equals' WILL ALSO BE OVERRIDEN
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matrix);
		return result;
	}

	/**
	 * OVERRIDING 'equals' TO CHECK IF 2 MATRICES ARE EQUAL
	 * CHECKS, RESPECTIVELY, IF BOTH OBJECTS ARE SAME OBJECT, IF OBJECT IS EMPTY,
	 * IF BOTH OBJECTS BELONG TO SAME CLASS AND FINALLY COMPARES
	 * MEMBER VARIABLES IF BOTH OBJECTS BELONG TO SAME CLASS
	 */
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

	/**
	 * CALCULATES SUM OF 2 MATRICES
	 * @param sm1 - square matrix
	 * @param sm2 - square matrix
	 * @return
	 * @throws Exception - IF DIMENSIONS OF MATRICES NOT IDENTICAL
	 */
	public static SquareMatrix add(SquareMatrix sm1, SquareMatrix sm2) throws Exception{
		if(sm1.matrix.length != sm2.matrix.length) {
			throw new IllegalArgumentException("Dimensions of matrices not identical. Please enter square matrices!");
		}
		//Creates empty, square 2D array with dimensions of matrices in argument
		double[][] sum = new double[sm1.matrix.length][sm1.matrix.length];

		//Sums corresponding elements from both matrices and adds to empty 2D array
		for(int i = 0; i < sm1.matrix.length; i++) {
			for(int j = 0; j < sm1.matrix.length; j++) {
				sum[i][j] = sm1.matrix[i][j] + sm2.matrix[i][j];
			}
		}
		//Returns sum of 2 square matrices
		return new SquareMatrix(sum);
	}

	/**
	 * CALCULATES SUBTRACTION OF 2 MATRICES
	 * @param sm1 - square matrix
	 * @param sm2 - square matrix
	 * @return
	 * @throws Exception - IF DIMENSIONS OF MATRICES ARE NOT IDENTICAL
	 */
	public static SquareMatrix subtract(SquareMatrix sm1, SquareMatrix sm2) throws Exception{
		if(sm1.matrix.length != sm2.matrix.length) {
			throw new IllegalArgumentException("Dimensions of matrices not identical. Please enter matrices of identical size!");
		}
		//Creates empty, square 2D array with dimensions of matrices in argument
		double[][] minus = new double[sm1.matrix.length][sm1.matrix.length];

		//Subtracts corresponding elements from both matrices and adds to empty 2D array
		for(int i = 0; i < sm1.matrix.length; i++) {
			for(int j = 0; j < sm1.matrix.length; j++) {
				minus[i][j] = sm1.matrix[i][j] - sm2.matrix[i][j];
			}
		}
		//Returns subtraction of 2 square matrices
		return new SquareMatrix(minus);
	}

	/**
	 * CALCULATES PRODUCT OF 2 MATRICES 
	 * @param sm1 - square matrix
	 * @param sm2 - square matrix
	 * @return
	 * @throws Exception - IF DIMENSIONS OF MATRICES ARE NOT IDENTICAL
	 */
	public static SquareMatrix multiply(SquareMatrix sm1, SquareMatrix sm2) throws Exception{
		//Matrix multiplication general formula: P_ij = A_ik*B_kj, where RHS is summed over all k values
		if(sm1.matrix.length != sm2.matrix.length) {
			throw new IllegalArgumentException("Dimensions of matrices not identical. Please enter matrices of identical size!");
		}
		//Creates empty, square 2D array with dimensions of matrices in argument
		double[][] times = new double[sm1.matrix.length][sm1.matrix.length];

		//First 2 'for' loops set all elements of 2D array to 0
		for(int i = 0; i < sm1.matrix.length; i++) {
			for(int j = 0; j < sm1.matrix.length; j++) {

				//3rd 'for' loop multiplies according to equation above
				for(int k = 0; k < sm1.matrix.length; k++) {
					times[i][j] += sm1.matrix[i][k]*sm2.matrix[k][j];
				}
			}
		}
		//Returns product of 2 square matrices
		return new SquareMatrix(times);
	}

	/**
	 * NON-STATIC VERSION OF METHOD TO SUM 2 MATRICES
	 * CALLS STATIC VERSION
	 * @param sm2 - square matrix
	 * @return
	 * @throws Exception - IF DIMENSIONS OF MATRICES ARE NOT IDENTICAL
	 */
	public SquareMatrix add(SquareMatrix sm2) throws Exception{
		return add(this, sm2);
	}

	/**
	 * NON-STATIC VERSION OF METHOD TO SUBTRACT 2 MATRICES
	 * CALLS STATIC VERSION
	 * @param sm2 - square matrix
	 * @return
	 * @throws Exception - IF DIMENSIONS OF MATRICES ARE NOT IDENTICAL
	 */
	public SquareMatrix subtract(SquareMatrix sm2) throws Exception{
		return subtract(this, sm2);
	}

	/**
	 * NON-STATIC VERSION OF METHOD TO MULTIPLY 2 MATRICES
	 * CALLS STATIC VERSION
	 * @param sm2 - square matrix
	 * @return
	 * @throws Exception - IF DIMENSIONS OF MATRICES ARE NOT IDENTICAL
	 */
	public SquareMatrix multiply(SquareMatrix sm2) throws Exception{
		return multiply(this, sm2);
	}

	public static void main(String[] args) {}
}