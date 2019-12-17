package module9;

import java.awt.*; //Importing package for creating graphical user interfaces (GUI)
import java.awt.event.*; //Importing package for classes/interfaces used for event handling
import javax.swing.*; //Importing package for window-based application

public class SolarObjectsPanel extends JPanel implements ActionListener{

	Polygon shape;
	final int delay = 50;
	final double deltaMer;
	final double deltaVen;
	final double deltaEar;
	final double deltaMar;
	double angleMer = 0.0;
	double angleVen = 0.0;
	double angleEar = 0.0;
	double angleMar = 0.0;

	Timer animationTimer;
	double period;

	public SolarObjectsPanel(int width, int height, double period) {
		this.period = period;
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.BLACK);
		deltaMer = 2*Math.PI*delay/(period*1000);
		deltaVen = 2*Math.PI*delay/(period*(225/88)*1000);
		deltaEar = 2*Math.PI*delay/(period*(365/88)*1000);
		deltaMar = 2*Math.PI*delay/(period*(687/88)*1000);
		animationTimer = new Timer(delay, this);
		animationTimer.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centreX = getWidth()/2;
		int centreY = getHeight()/2;

		int radius = getWidth()/25;

		double radMer = centreX*(36/141.6);
		double radVen = centreX*(67.2/141.6);
		double radEar = centreX*(93/141.6);
		double radMar = centreX;

		g.setColor(Color.YELLOW);
		g.fillOval(centreX-radius, centreY-radius, radius, radius);

		//Mercury
		g.setColor(Color.GRAY);
		g.fillOval((int) (Math.cos(angleMer)*(radMer)+centreX), (int) (Math.sin(angleMer)*(radMer)+centreY), radius/6, radius/6);

		//Venus
		g.setColor(Color.ORANGE);
		g.fillOval((int) (Math.cos(angleVen)*(radVen)+centreX), (int) (Math.sin(angleVen)*(radVen)+centreY), radius/3, radius/3);

		//Earth
		g.setColor(Color.BLUE);
		g.fillOval((int) (Math.cos(angleEar)*(radEar)+centreX), (int) (Math.sin(angleEar)*(radEar)+centreY), radius/2, radius/2);

		//Mars
		g.setColor(Color.RED);
		g.fillOval((int) (Math.cos(angleMar)*(radMar)+centreX), (int) (Math.sin(angleMar)*(radMar)+centreY), radius/4, radius/4);
	}

	/** Start the animation */
	public void start() {
		animationTimer.start();
	}

	/** Stop the animation */
	public void stop() {
		animationTimer.stop();
	}

	public void actionPerformed(ActionEvent event) {
		angleMer += deltaMer;
		angleVen += deltaVen;
		angleEar += deltaEar;
		angleMar += deltaMar;
		repaint();
	}
}
