package module8;

public class CountdownTask implements Runnable {

	static int seconds;

	public CountdownTask(int seconds) {
		CountdownTask.seconds = seconds;
	}

	public void run() {
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis() + (seconds * 1000);
		long temp = startTime + 1000 ;
		System.out.print(seconds + " seconds remaining\n" );
		while (startTime < endTime) {
			if (startTime == temp) {
				seconds--;
				System.out.print(seconds + " seconds remaining\n");
				temp += 1000;
			}
			startTime = System.currentTimeMillis();
		}
		System.out.println("0 seconds remaining");
	}

	public static void main(String[] args) {
		CountdownTask timer = new CountdownTask(10);
		timer.run();
	}
}