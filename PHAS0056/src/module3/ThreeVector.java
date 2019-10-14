package module3;
public class ThreeVector {

	//Member Variables
	//Can be used by and shared between methods defined in class
	public double x; //x-component
	public double y; //y-component
	public double z; //z-component

	//Constructor
	//Used to set up 'ThreeVector' object when creating new 'ThreeVector' object using 'new' command
	public ThreeVector(double x, double y, double z) { //3 'double' variable arguments for 3 elements of vector

		//Assigns member variables to each component of 'ThreeVector' object
		//x, y and z refer to respective component of 'ThreeVector' object
		this.x = x;
		this.y = y;
		this.z = z;
	}

	//CALCULATES MAGNITUDE OF 3-COMPONENT VECTOR
	public double magnitude() {
		//Calculates by squaring each component, summing and then square rooting the sum
		double magn = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
		return magn; //Returns magnitude of 3D vector
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//CALCULATES UNIT VECTOR OF 3-COMPONENT VECTOR
	public ThreeVector unitVector() throws IllegalArgumentException{
		if(magnitude() == 0) {
			throw new IllegalArgumentException("Zero vector entered. No unit vector exists.");
		}
		//Calculates by dividing each component by magnitude of vector
		double xUnit = x/magnitude(); //x-component
		double yUnit = y/magnitude(); //y-component
		double zUnit = z/magnitude(); //z-component
		//Returns vector by creating new 'ThreeVector' object using unit components
		return new ThreeVector(xUnit, yUnit, zUnit);
	}

	//CONVERTS OBJECT TO STRING TO PRINT 'ThreeVector' OBJECTS AS VECTOR
	public String toString() {
		return "("+x+", "+y+", "+z+")"; //Vector string layout (x, y, z)
	}

	//CALCULATES SCALAR PRODUCT OF 2x 3-COMPONENT VECTORS (STATIC)
	public static double scalarProduct(ThreeVector v1, ThreeVector v2) { //2 'ThreeVector' objects as vector arguments
		//Calculates by multiplying corresponding components of 2 vectors (e.g. x1*x2)
		double xrow = v1.x * v2.x; //x-components
		double yrow = v1.y * v2.y; //y-components
		double zrow = v1.z * v2.z; //z-components
		return xrow + yrow + zrow; //Returns sum of products of corresponding components
	}

	//CALCULATES VECTOR PRODUCT OF 2x 3-COMPONENT VECTORS (STATIC)
	public static ThreeVector vectorProduct(ThreeVector v1, ThreeVector v2) { //2 'ThreeVector' objects as vector arguments
		//Calculates using equation for vector product: (y1*z2-z1*y2)i + (z1*x2-x1*z2)j + (x1*y2-y1*x2)k
		double xrow = (v1.y * v2.z) - (v1.z * v2.y); //x-component
		double yrow = (v1.z * v2.x) - (v1.x * v2.z); //y-component
		double zrow = (v1.x * v2.y) - (v1.y * v2.x); //z-components
		//Returns vector by creating new 'ThreeVector' object using vector product components
		return new ThreeVector(xrow, yrow, zrow);
	}

	//CALCULATES SUM OF 2x 3-COMPONENT VECTORS (STATIC)
	public static ThreeVector add(ThreeVector v1, ThreeVector v2) { //2 'ThreeVector' objects as vector arguments
		//Calculates by summing corresponding components from 2 vectors (e.g. x1+x2)
		double xrow = v1.x + v2.x; //x-component
		double yrow = v1.y + v2.y; //y-component
		double zrow = v1.z + v2.z; //z-component
		//Returns vector by creating new 'ThreeVector' object using summed components
		return new ThreeVector(xrow, yrow, zrow);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//CALCULATES ANGLES BETWEEN 2x 3-COMPONENT VECTORS (STATIC)
	public static double angle(ThreeVector v1, ThreeVector v2) throws IllegalArgumentException{ //2 'ThreeVector' objects as vector arguments
		if(v1.magnitude() == 0 || v2.magnitude() == 0) {
			throw new IllegalArgumentException("Zero vector entered. Angle cannot be found. Zero vector has no direction.");
		}
		//Calculates using equation for angle between vectors: cos(theta)=(v1.v2)/(|v1||v2|)
		double dot = scalarProduct(v1, v2); //Scalar product of 2 vectors
		double mag1 = v1.magnitude(); //Magnitude of 1st vector
		double mag2 = v2.magnitude(); //Magnitude of 2nd vector
		double cosx = dot/(mag1*mag2); //Cosine of angle from equation shown above
		return Math.acos(cosx); //Returns angle in radians by inverse cosine of 'cosx'
	}

	//CALCULATES SCALAR PRODUCT OF 2x 3-COMPONENT VECTORS (NON-STATIC)
	public double scalarProduct(ThreeVector v1) { //'ThreeVector' object as vector argument
		//Calculates by calling static 'scalarProduct()' method
		return scalarProduct(this, v1); //Returns scalar product
	}

	//CALCULATES VECTOR PRODUCT OF 2x 3-COMPONENT VECTORS (NON-STATIC)
	public ThreeVector vectorProduct(ThreeVector v1) { //'ThreeVector' object as vector argument
		//Calculates by calling static 'vectorProduct()' method
		return vectorProduct(this, v1); //Returns vector product
	}

	//CALCULATES SUM OF 2x 3-COMPONENT VECTORS (NON-STATIC)
	public ThreeVector add(ThreeVector v1) { //'ThreeVector' object as vector argument
		//Calculates by calling static 'add()' method
		return add(this, v1); //Returns sum of 2 vectors
	}

	//CALCULATES ANGLE BETWEEN 2x 3-COMPONENT VECTORS (NON-STATIC)
	public double angle(ThreeVector v1) { //'ThreeVector' object as vector argument
		//Calculates by calling static 'angle()' method
		return angle(this, v1); //Returns angle between 2 vectors in radians
	}

	public static void main(String[] args) {

		//2 'ThreeVector' objects created to test class
		ThreeVector vector1 = new ThreeVector(0, 0, 0);
		ThreeVector vector2 = new ThreeVector(1, 2, 3);

		//Print statements to test class
		try {
			ThreeVector v1 = vector1.unitVector();
			System.out.println(v1);
		}
		catch (Exception e) {
			System.out.println("Zero vector entered. CANNOT divide by zero. Please enter a non-zero vector!");
		}
		try {
			double v2 = angle(vector1, vector2);
			System.out.println(v2);
		}
		catch (Exception e) {
			System.out.println("Zero vector entered. Since zero vector has no direction, angle CANNOT be found!");
		}
	}
}	