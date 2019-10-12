package module2;
public class TestThreeVector {
	public static void main(String[] args) {

		//3 'ThreeVector' objects created to form 3 different vectors
		ThreeVector v1 = new ThreeVector(4, 5, 3);
		ThreeVector v2 = new ThreeVector(1, 4, 2);
		ThreeVector v3 = new ThreeVector(0, 0, 0);

		//Print statements to output vectors with corresponding unit vectors
		System.out.println("VECTORS AND CORRESPONDING UNIT VECTORS");
		System.out.println("Vector 1: "+v1);
		System.out.println("Vector 1 (unit): "+v1.unitVector());
		System.out.println("Vector 2: "+v2);
		System.out.println("Vector 2 (unit): "+v2.unitVector());
		System.out.println("Vector 3: "+v3);
		System.out.println("Vector 3 (unit): "+v3.unitVector());
		System.out.println("(Magnitude of 'v3' = 0 but cannot divide by 0. So no unit vector exists for 'v3' vector.)");

		//Print statements to output scalar products of 2 given vectors
		System.out.println("\nSCALAR PRODUCT");
		System.out.println("Vectors 1 & 2 (non-static): "+v1.scalarProduct(v2));
		System.out.println("Vectors 1 & 2 (static): "+ThreeVector.scalarProduct(v1, v2));
		System.out.println("Vectors 1 & 3 (non-static): "+v1.scalarProduct(v3));
		System.out.println("Vectors 1 & 3 (static): "+ThreeVector.scalarProduct(v1, v3));

		//Print statements to output vector product of 2 given vectors
		System.out.println("\nVECTOR PRODUCT");
		System.out.println("Vectors 1 & 2 (non-static): "+v1.vectorProduct(v2));
		System.out.println("Vectors 1 & 2 (static): "+ThreeVector.vectorProduct(v1, v2));
		System.out.println("Vectors 1 & 3 (non-static): "+v1.vectorProduct(v3));
		System.out.println("Vectors 1 & 3 (static): "+ThreeVector.vectorProduct(v1, v3));

		//Print statements to output angle between 2 given vectors
		System.out.println("\nANGLE BETWEEN 2 VECTORS");
		System.out.println("Angle Between Vectors 1 & 2 (non-static): "+v1.angle(v2)+" radians");
		System.out.println("Angle Between Vectors 1 & 2 (static): "+ThreeVector.angle(v1, v2)+" radians");
		System.out.println("Angle Between Vectors 1 & 3 (non-static): "+v1.angle(v3)+" radians");
		System.out.println("Angle Between Vectors 1 & 3 (static): "+ThreeVector.angle(v1, v3)+" radians");
		System.out.println("(Since angle is calculated between directions and 'v3' vector has no direction,");
		System.out.println("angle cannot be calculated between 'v1' and 'v3' vectors.)");

		//Print statements to describe outcome of removing 'toString()' method defined in 'ThreeVector' class
		System.out.println("\nOUTCOME OF REMOVING 'toString()' METHOD");
		System.out.println("Upon commenting out the 'toString()' method and running this class, all of the vector outputs");
		System.out.println("are no longer displayed as vectors. The output consists of 'package_name.class_name@object_hash_code'.");
		System.out.println("This is because the 'toString()' method converts an object into a string so that it can be printed to the screen");
		System.out.println("in a form that can be understood. Without the 'toString()' method, a string of the object's class and hashcode is");
		System.out.println("returned, which is not useful for us. Therefore the 'toString()' method is required.");
	}
}