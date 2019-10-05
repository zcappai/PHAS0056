package module1;

public class VectorMethods {

	public static void main(String[] args) {
		
		//Variables for 3-dimensional vectors used in dot product, magnitude and angle calculations
		double x1 = 4.0, y1 = 3.0, z1 = 2.0, x2 = 1.0, y2 = 5.0, z2 = 4.0; //Assigning values to double variables for vectors in scenario 1
		double x3 = 4.0, y3 = 3.0, z3 = 2.0, x4 = 0.0, y4 = 0.0, z4 = 0.0; //Assigning values to double variables for vectors in scenario 2
		
		//Creating "VectorMethods" object to call functions from outside "main" method
		VectorMethods vm = new VectorMethods();
		
		//ANGLE BETWEEN (4, 3, 2) AND (1, 5, 4) - Scenario 1
		double ang1 = vm.angle(x1, y1, z1, x2, y2, z2); //Calculating angle between vectors 1 and 2
		System.out.println("1)The angle between vectors (4, 3, 2) and (1, 5, 4) is "+ang1+" degrees");
		
		//ANGLE BETWEEN (4, 3, 2) AND (0, 0, 0) - Scenario 2
		double ang2 = vm.angle(x3, y3, z3, x4, y4, z4); //Calculating angle between vectors 1 and 2
		System.out.println("2a)The angle between vectors (4, 3, 2) and (0, 0, 0) is "+ang2);
		System.out.println("2b)This is because the magnitude of the 2nd vector is 0. So it has no direction, and since angle is calculated between directions, the angle is undefined");
		
	}
	//DEFINITION OF METHOD TO FIND MAGNITUDE OF VECTOR (3D)
	public double magnitude(double x1, double y1, double z1) {
		//Magnitude of vector calculated by squaring each element, summing them and then square rooting the value 
		double magn = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1, 2)+Math.pow(z1, 2)); 
		return magn; //Returns magnitude
	}
	//DEFINITION OF METHOD TO FIND DOT PRODUCT OF 2 VECTORS (3D)
	public double dotProduct(double x1, double y1, double z1, double x2, double y2, double z2) {
		//Below are products of corresponding x, y and z elements of both vectors
		double xrow = x1*x2; //x-elements
		double yrow = y1*y2; //y-elements
		double zrow = z1*z2; //z-elements
		return xrow + yrow + zrow; //Returns dot product of 2 vectors
		
	}
	//DEFINITION OF METHOD TO FIND ANGLE BETWEEN 2 VECTORS (3D)
	public double angle(double x1, double y1, double z1, double x2, double y2, double z2) {
		//Below 'dotProduct' method called and dot product of 2 vectors calculated
		double dot1 = dotProduct(x1, y1, z1, x2, y2, z2);
		//Below 'magnitude' method is called
		double mag1 = magnitude(x1, y1, z1); //Magnitude of 1st vector calculated
		double mag2 = magnitude(x2, y2, z2); //Magnitude of 2nd vector calculated
		
		double cosx = dot1/(mag1*mag2); //Cosine of angle calculated
		return Math.toDegrees(Math.acos(cosx)); //Returns angle by inverse cosine of 'cosx', then conversion to degrees

	}
}
