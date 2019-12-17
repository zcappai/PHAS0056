package module9;

import java.awt.*; //Importing package for creating graphical user interfaces (GUI)
import javax.swing.*; //Importing package for window-based application
import java.awt.event.*; //Importing package for classes/interfaces used for event handling

/**
 * Panel with start, stop and exit buttons for rotating square
animation.
 */
public class AnimationGUIPanel extends JPanel implements ActionListener {

	private SolarObjectsPanel animPanel; // panel containing animation
	private JButton startButton;
	private JButton stopButton;
	private JButton exitButton;

	/** Create JPanel containing animation panel and buttons. */
	public AnimationGUIPanel() {
		super();
		setPreferredSize(new Dimension(1000,1000));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		animPanel = new SolarObjectsPanel(1000,1000,10);
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		exitButton = new JButton("Exit");
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		exitButton.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(exitButton);
		add(animPanel);
		add(buttonPanel);
	}
	/** Respond to button clicks */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==startButton) start();
		else if (e.getSource()==stopButton) stop();
		else if (e.getSource()==exitButton) System.exit(0);
	}
	/** Start animation */
	public void start() {animPanel.start();}
	/** Stop animation */
	public void stop() {animPanel.stop();}
}