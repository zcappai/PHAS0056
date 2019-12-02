package module8;

public class CountdownTask implements Runnable {

	int seconds;

	public CountdownTask(int seconds) {
		this.seconds = seconds;
	}

	public void run() {
		long start = System.currentTimeMillis(); //Assigns current time to "start"
		long end = start + seconds*1000; //"end" time is "runTime" after "start" time
		long total = seconds*1000;

		while(System.currentTimeMillis() < end) {
			long diff = end - System.currentTimeMillis();
			long remain = diff/1000;
			if(remain < total) {
				total = remain;
				System.out.println(total+" seconds");
			}
		}
//		System.out.println(seconds); 
	}

	public static void main(String[] args) {
		new CountdownTask(5).run();
	}

}
