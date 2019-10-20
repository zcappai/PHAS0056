package module3;
public class FallingParticle {

	//Member Variables
	//Can be used by and shared between methods defined in class
	double m; //Mass of falling particle (kilograms, kg)
	double d; //Drag coefficient (dimensionless)
	double t; //Time elapsed since particle drop (seconds, s)
	double z; //Vertical position of particle (measured upwards from base of vessel) (metres, m)
	double h; //Initial starting height of particle (measured upwards from base of vessel) (metres, m)
	double v; //Velocity of particle (measured upwards) (metres per second, ms^-1)
	//'static final' ensure 'g' remains a constant, which can't be reinitialised
	static final double g = 9.81; //Acceleration due to gravity (metres per second squared, ms^-2)

	//AMENDED SECTION
	//Constructor
	//Used to set up 'FallingParticle' object when creating new 'FallingParticle' object using 'new' command
	public FallingParticle(double m, double d) throws IllegalArgumentException{ //Specifies that method can throw an exception
		//Initialising variables
		t = 0.0; //Sets start time to 0.0
		v = 0.0; //Sets start velocity to 0.0

		//Throws illegal argument exception if mass is unphysical
		if(m<=0) {
			//Error message printed when exception thrown
			throw new IllegalArgumentException("Unphysical mass of "+m+"kg entered. Please enter physical value!");
		}
		//Throws illegal argument exception if drag coefficient is unphysical
		if(d<0) {
			//Error message printed when exception thrown
			throw new IllegalArgumentException("Unphysical drag coefficient of "+d+" entered. Please enter physical value!");
		}
		//Assigns member variables to each part of 'FallingParticle' object
		//m and d refer to mass and drag coefficient of 'FallingParticle' object
		this.m = m;
		this.d = d;
	}

	//AMENDED SECTION
	//SETS INITIAL STARTING HEIGHT OF PARTICLE DROP
	public void setH(double val) throws IllegalArgumentException{ //Specifies that method can throw an exception
		//Throws exception if initial starting height is set to be unphysical
		if(val<0) {
			//Error message printed when exception thrown
			throw new IllegalArgumentException("Unphysical height of "+val+"m entered. Please enter physical value!");
		}
		h = val; //Sets initial starting height
	}

	//GETS CURRENT VERTICAL POSITION OF PARTICLE (MEASURED UPWARDS FROM BASE)
	public double getZ() {
		return z; //Returns vertical position
	}

	//SETS VELOCITY OF PARTICLE (MEASURED UPWARDS FROM BASE)
	public void setV(double val) { //'double' variable argument for velocity
		v = val; //Sets velocity of particle
	}

	//GETS CURRENT VELOCITY OF PARTICLE (MEASURED UPWARDS FROM BASE)
	public double getV() {
		return v; //Returns velocity of particle
	}

	//GETS TIME ELAPSED SINCE PARTICLE DROP
	public double getT() {
		return t; //Returns time elapsed
	}

	//CALCULATES ACCELERATION OF PARTICLE IN CURRENT STATE, AND UPDATES VELOCITY AND POSITION
	public void doTimeStep(double deltaT) { //'double' variable argument for time step
		//Calculates acceleration using equation: a = (d*v^2/m)-g
		double a = (d*Math.pow(getV(), 2)/m) - g;
		//Calculates and sets new velocity using expression: v+a*deltaT
		setV(v + a*deltaT);
		//Calculates and sets new position using expression: z+v*deltaT
		z += v*deltaT;
		//Sets new time elapsed using expression: t+deltaT
		t += deltaT;
	}

	//SIMULATES DESCENT OF DROPPED PARTICLE TO BASE OF VESSEL
	public void drop(double deltaT) throws IllegalArgumentException{ //Specifies that method can throw an exception
		z = h; //Sets current position to initial height
		if(deltaT<=0) {
			//Error message printed when exception thrown
			throw new IllegalArgumentException("Unphysical time step of "+deltaT+"s entered. Please enter physical value!");
		}
		//Runs 'doTimeSteps()' method while current position of particle is above base (z>0)
		while(z>0) {
			doTimeStep(deltaT); //Updates velocity and position of particle every loop
		}
		System.out.println("Time Steps: "+deltaT+" s"); //Prints size of time step
		System.out.println("Time Taken: "+getT()+" s"); //Prints time taken to reach base of vessel
		System.out.println("Velocity at Bottom: "+getV()+" m/s\n"); //Prints velocity at base of vessel
	}

	public static void main(String[] args) {

		//Testing try and catch structures
		FallingParticle p = new FallingParticle(5, 2);

		//Exception thrown when setting negative height
		try {
			p.setH(-5);
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
	}
}