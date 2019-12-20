package module9;

import javax.swing.*; //Importing package for window-based application

/* Main class that runs model of solar system */
public class SolarSystem {

	/**
	 * Creates and displays JFrame and adds JPanel with GUI, graphics and animations
	 * to model the solar system. Creates top-level container as window on screen
	 */
	static void runModel() {
		JFrame frame = new JFrame("Solar System Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Applications closes with window
		JPanel panel = new GUIPanel();
		frame.add(panel);
		frame.setResizable(false);
		frame.pack(); //Arranges components within window
		frame.setVisible(true); //Displays the JFrame when run
	}

	public static void main(String[] args) {
		/* Calls method to create and display GUI, such that all Swing methods
		 * are run from single "event-dispatching thread" only, to make sure Swing
		 * methods function properly */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runModel();
			}
		});
	}
}