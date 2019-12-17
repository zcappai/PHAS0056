package module9;

import javax.swing.*; //Importing package for window-based application

/* Main class that runs model of solar system and inherits 'JPanel' superclass */
public class SolarSystem extends JPanel {

	static void runModel() {
		JFrame frame = new JFrame("Solar System Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new AnimationGUIPanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runModel();
			}
		});
	}
}