package module1;
public class AlgorithmControl {
	public static void main(String[] args) {
		
		//Creating "AlgorithmControl" object to call functions from outside "main" method
		AlgorithmControl ac = new AlgorithmControl();
		
		//Calculating total number of loops for given loop runtime (millisecond)
		int totalLoops = ac.timer(10000, 1000); //10 seconds, 1000 loops
		int totalLoops1 = ac.timer(10000, 50000); //10 seconds, 50000 loops
		System.out.println("\nTotal Number of Loops: "+totalLoops+" (10s, 1000 loops)");
		System.out.println("Total Number of Loops: "+totalLoops1+" (10s, 50000 loops)");
		System.out.println("\n1)The total number of loops completed has increased, despite the runtime remaining the same for both scenarios.");
		System.out.println("This is because the number of loops between printing has increased. Therefore, the 2nd 'timer()' can run more loops before printing.");
		System.out.println("Printing affects the performance of running code, so more printing reduces performance. Therefore the 1st scenario runs fewer loops.");
	}
	//DEFINITION OF VOID METHOD TO PRINT INTEGERS 1 TO 8
	public void loop() {
		int max = 8; //Sets a maximum value for the number of loops
		
		//"for" loop starts counter at 1, increasing by 1 per loop, to 8
		for(int counter = 1; counter<=max; counter++) { //"counter++" increases counter value by 1
			System.out.println(counter); //Each loop prints the value of the counter
		}
	}
	//DEFINTION OF VOID METHOD TO PRINT INTEGERS 10 DECREASING TO -5
	public void decrement() {
		boolean end; //Initially assigns boolean to true
		int x = 10; //Initial condition for counter
		end = true; //Initial condition for boolean
		
		//"while" loop starts at 10, decreasing by 1 per loop, to -5
		while(end) {
			System.out.println(x); //Prints value of counter
			x--; //Decreases value of counter by 1

			//If the counter is lower than -5, the method stops
			if(x < -5) {
				end = false; //"end" set to false, assigns boolean to false so method stops
			}
		}
	}
	//DEFINTION OF VOID METHOD TO PRINT NUMBERS 2.5 INCREASING BY 0.1 TO 4.3
	public void increment() {
		double max = 4.3; //Sets maximum value for counter
		
		//"for" loop starts at 2.5, increasing by 0.1, to 4.3
		for(double counter = 2.5; counter<=max; counter+=0.1) { //"counter+=0.1" increases value by 0.1
			System.out.println(counter); //Each loop prints value of counter
			}
		}
	//DEFINITION OF VOID METHOD TO PRINT TOTAL NUMBER OF LOOPS RUN EVERY 100 LOOPS
	//LOOP RUNS FOR 5 SECONDS
//		long start = System.currentTimeMillis(); //Assigns current time to "start"
//		long end = start + 5000; //Assigns time that is 5000ms (5 seconds) from "start"
//		int x = 0; //Initial condition for counter
//		
//		//"While" loop increases value of counter by 1 per loop during 5 second interval
//		while(System.currentTimeMillis() < end) {
//			x++; //Counter increased by 1 per loop
//			
//			//"if" statement prints total loops run every 100 loops
//			if(x % 100 == 0) {
//				System.out.println(x+" loops so far");
//			}
//		}
//	}
	//DEFINTION OF METHOD TO PRINT TOTAL NUMBER OF LOOPS RUN EVERY SPECIFIED NUMBER OF LOOPS
	//LOOP RUNTIME AND NUMBER OF LOOPS BETWEEN PRINTING ARE BOTH ARGUMENTS
	public int timer(long runTime, int loopSteps) {
		long start = System.currentTimeMillis(); //Assigns current time to "start"
		long end = start + runTime; //"end" time is "runTime" after "start" time
		int x = 0; //Initial condition for counter
		
		//"While" loop increases value of counter by 1 per loop during "runTime" interval
		while(System.currentTimeMillis() < end) {
			x++; //Counter increased by 1 per loop
			
			//"if" statement prints total loops for every "loopSteps" loops
			if(x % loopSteps == 0) {
				System.out.println(x+" loops so far");
			}
		}
		return x; //Returns total number of loops
	}
}
