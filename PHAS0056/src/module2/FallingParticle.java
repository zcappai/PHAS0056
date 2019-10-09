package module2;

public class FallingParticle {

	double m;			//////static
	double d;			//////static
	double t = 0.0;
	double z;
	double h;
	double v = 0;
	static final double g = 9.81;

	public FallingParticle(double m, double d) {			///////reseting values for different objects?
		this.m = m;
		this.d = d;
	}

	public void setH(double val) {
		h = val;
	}

	public double getZ() {
		return z;
	}

	public void setV(double val) {
		v = val;
	}

	public double getV() {
		return v;
	}

	public double getT() {
		return t;
	}
	
	public void doTimeStep(double deltaT) {
		double a = (d*Math.pow(getV(), 2)/m) - g;
		System.out.println("\nCurrent Acceleration: "+a);
		setV(v + a*deltaT);
		System.out.println("Current Velocity: "+getV());
		z = z + getV()*deltaT;
	}
	
	public void drop(double deltaT) {
		z = h;
		System.out.println("\nInitial Time: "+getT());
		System.out.println("Initial Height: "+getZ());
		System.out.println("Initial Velocity: "+getV());
		while(z>0) {
			doTimeStep(deltaT);
			System.out.println("Current Height: "+getZ());
			t = t + deltaT;
			System.out.println("Current Time: "+getT());
		}
		System.out.println("\nTime Taken: "+getT());
		System.out.println("Velocity at Bottom: "+getV());
	}

	public static void main(String[] args) {
		FallingParticle p = new FallingParticle(4.3, 2.4);
		p.setH(5);
		p.drop(0.001);
	}

}
