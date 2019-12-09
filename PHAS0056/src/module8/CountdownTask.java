package module8;

//Implements Runnable interface, with inherited abstract 'run()' function that will be countdown
public class CountdownTask implements Runnable {

	//Member variable
	int seconds; //Duration of countdown timer

	//Constructor to set duration of countdown timer
	public CountdownTask(int seconds) {
		this.seconds = seconds; //Assigns member variable to corresponding argument
	}

	/**
	 * Implements 'run()' method as countdown timer
	 * First sets start time, then end time using duration of timer
	 * Sets 'temp' variable as check for time elapsed since start of countdown
	 * Initial time printed, then if starting time is equal to itself plus 1000 ms
	 * (1 second), then 'seconds' member variable is reduced by 1
	 * and printed. Time elapsed variable increased by 1 second (1000 ms) and
	 * loop is repeated until reaching the end time. Starting time is updated
	 * for each loop of 'while' loop. While loop will run as long as updated start
	 * time is less than end time.
	 */
	public void run() {
		//Starting and ending times of countdown
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis() + (seconds * 1000);

		long temp = startTime + 1000 ; //Variable to check elapsed time
		System.out.print(seconds + " seconds remaining\n" );

		//Counts down in intervals of 1 second from given duration time
		while (startTime < endTime) {
			if (startTime >= temp) {
				seconds--;
				System.out.print(seconds + " seconds remaining\n");
				//Increases elapsed time by 1 second to check if another second has passed
				temp += 1000;
			}
			//Updates starting time to check when to update & print new duration and when to stop
			startTime = System.currentTimeMillis();
		}
		System.out.println("0 seconds remaining");
	}
}