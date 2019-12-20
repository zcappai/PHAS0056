package module9;

import javax.swing.*; //Importing package for window-based application
import java.awt.event.*; //Importing package for classes/interfaces used for event handling

/* Panel with start, stop and exit buttons for solar system model animation */
public class GUIPanel extends JPanel implements ActionListener {

	//Member variables
	SolarObjectsPanel animPanel; // panel containing animation
	//Buttons for controlling animations
	JButton startButton;
	JButton stopButton;
	JButton exitButton;

	/**
	 * Creates JPanel containing animation panel and buttons.
	 */
	public GUIPanel() {
		super();
		//Simple layout with vertical arrangement of components.
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		//Initialising solar system model
		animPanel = new SolarObjectsPanel(1000,1000,5.0);

		//Creating start, stop and exit buttons with labels
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		exitButton = new JButton("Exit");

		//Calls actionPerformed method of objects if button clicked
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		exitButton.addActionListener(this);

		//New panel to which start, stop and exit buttons are added, arranged horizontally
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(exitButton);

		//Both animation and button panels are added to main GUIPanel
		add(animPanel);
		add(buttonPanel);
	}
	/**
	 * Respond to button clicks
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==startButton) start();
		else if (e.getSource()==stopButton) stop();
		else if (e.getSource()==exitButton) System.exit(0);
	}
	/**
	 * Start animation by calling start() function of animation panel
	 */
	public void start() {animPanel.start();}

	/**
	 * Stop animation by calling stop() function of animation panel
	 */
	public void stop() {animPanel.stop();}
}