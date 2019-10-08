package module2;

public class ThreeVector {

	double x;
	double y;
	double z;

	public ThreeVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	double magnitude() {
		double magn = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
		return magn;
	}

	ThreeVector unitVector() {
		double xUnit = x/magnitude();
		double yUnit = y/magnitude();
		double zUnit = z/magnitude();
		ThreeVector unVec = new ThreeVector(xUnit, yUnit, zUnit);
		return unVec;
	}

	public String toString() {
		return "("+x+", "+y+", "+z+")";
	}

	static double scalarProduct(ThreeVector v1, ThreeVector v2) {
		double xrow = v1.x * v2.x;
		double yrow = v1.y * v2.y;
		double zrow = v1.z * v2.z;
		return xrow + yrow + zrow;
	}

	static ThreeVector vectorProduct(ThreeVector v1, ThreeVector v2) {
		double xrow = (v1.y * v2.z) - (v1.z * v2.y);
		double yrow = (v1.z * v2.x) - (v1.x * v2.z);
		double zrow = (v1.x * v2.y) - (v1.y * v2.x);
		ThreeVector xProd = new ThreeVector(xrow, yrow, zrow);
		return xProd;
	}

	static ThreeVector add(ThreeVector v1, ThreeVector v2) {
		double xrow = v1.x + v2.x;
		double yrow = v1.y + v2.y;
		double zrow = v1.z + v2.z;
		ThreeVector addVec = new ThreeVector(xrow, yrow, zrow);
		return addVec;
	}

	static double angle(ThreeVector v1, ThreeVector v2) {
		double dot = scalarProduct(v1, v2);
		double mag1 = v1.magnitude();
		double mag2 = v2.magnitude();
		double cosx = dot/(mag1*mag2);
		return Math.acos(cosx);
	}

	public static void main(String[] args) {
		ThreeVector vector1 = new ThreeVector(1, 2, 3);
		ThreeVector vector2 = new ThreeVector(4, 5, 6);

		System.out.println("Magnitude: "+vector1.magnitude());
		System.out.println("Unit Vector:"+vector1.unitVector());
		System.out.println("Scalar Product: "+scalarProduct(vector1, vector2));
		System.out.println("Vector Product: "+vectorProduct(vector1, vector2));
		System.out.println("Vector Addition: "+add(vector1, vector2));
		System.out.println("Angle Between Vectors: "+angle(vector1, vector2));
	}
}	