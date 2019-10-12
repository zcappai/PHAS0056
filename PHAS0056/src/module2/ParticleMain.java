package module2;
public class ParticleMain {
	public static void main(String[] args) {

		//'FallingParticle' object created to form particle that can simulate descent
		FallingParticle part = new FallingParticle(4.3, 2.4);

		part.setH(5); //Sets initial starting height to 5m

		//Setting 5 'double' variables for different time steps
		double dt1 = 0.5; //0.5 seconds
		double dt2 = 0.1; //0.1 seconds
		double dt3 = 0.01; //0.01 seconds
		double dt4 = 0.001; //0.001 seconds
		double dt5 = 0.0001; //0.0001 seconds

		//Simulates drops of particles for above time steps
		part.drop(dt1);
		part.drop(dt2);
		part.drop(dt3);
		part.drop(dt4);
		part.drop(dt5);

		//Print statements to comment on differences between each simulated descent of particle
		System.out.println("DIFFERENCES IN TIME TAKEN TO REACH BASE AND VELOCITY AT BASE FOR DIFFERENT TIME STEPS");
		System.out.println("From the above, it is clear to see that for a given mass of particle, drag coefficient and initial height,");
		System.out.println("the time taken to reach the bottom of the vessel and the velocity at the bottom of the vessel becomes more accurate");
		System.out.println("with decreasing time step size. This is because, decreasing the size of the time steps means that the new velocity,");
		System.out.println("height and time are calculated more frequently. This means that the 0.0001s step gives a more accurate value for the");
		System.out.println("time elapsed and velocity at the bottom. The 0.0001s step calculates very small height changes, so when the height is");
		System.out.println("just below 0, the loop will stop, giving very accurate values. Whereas for the 0.5s step, the height is calculated over");
		System.out.println("larger time intervals, so there would be a large change in height which could decrease it far below 0, which would not");
		System.out.println("give an accurate time or velocity. As the time step size decreases, the time elapsed and velocity at the base appear to");
		System.out.println("converge to a specific value.");
	}
}
