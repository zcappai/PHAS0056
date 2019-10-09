package module2;

public class FallingParticle {
	
	double m;			//////static
	double d;			//////static
	double t;
	double z;
	double h;
	double v;
	static final double g = 9.81;
	
	public FallingParticle(double m, double d) {
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
	

	public static void main(String[] args) {
		FallingParticle p = new FallingParticle(10, 2.5);
		
	}

}
