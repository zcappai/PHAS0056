package module2;

public class ParticleMain {

	public static void main(String[] args) {
		FallingParticle part = new FallingParticle(4.3, 2.4);
		
		double dt1 = 0.5;
		double dt2 = 0.1;
		double dt3 = 0.01;
		double dt4 = 0.001;
		double dt5 = 0.0001;
		
		part.setH(5);
		part.drop(dt1);
		part.drop(dt2);
		part.drop(dt3);
		part.drop(dt4);
		part.drop(dt5);

	}

}
