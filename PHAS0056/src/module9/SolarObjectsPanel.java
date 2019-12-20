package module9;

import java.awt.*; //Importing package for creating graphical user interfaces (GUI)
import java.awt.event.*; //Importing package for classes/interfaces used for event handling
import java.util.ArrayList; //Importing ArrayList from utilities package

import javax.swing.*; //Importing package for window-based application

/* JPanel subclass containing graphics solar system model, and implements
 * ActionListener interface to add animations so that graphics can move */
public class SolarObjectsPanel extends JPanel implements ActionListener{

	/* ArrayList of changes in angle for each planet relative to orbital period,
	 * and of updated angles for each respective planet relative to +ve x-axis */
	final ArrayList<Double> delta = new ArrayList<Double>();
	ArrayList<Double> angle = new ArrayList<Double>();

	Timer animationTimer; //Controls frame rate of animation
	final int delay = 50; //Delay in ms (milliseconds) between steps
	double period;

	/**
	 * Constructor for setting panel (and window) dimensions, change in angle for
	 * each planet relative to orbital period of Mercury and initial angle of each
	 * planet relative to +ve x-axis to zero.
	 */
	public SolarObjectsPanel(int width, int height, double period) {
		this.period = period;
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.BLACK);

		delta.add(2*Math.PI*delay/(period*(88/88)*1000)); //Mercury (88 days)
		delta.add(2*Math.PI*delay/(period*(225/88)*1000)); //Venus (225 days)
		delta.add(2*Math.PI*delay/(period*(365/88)*1000)); //Earth (365 days)
		delta.add(2*Math.PI*delay/(period*(687/88)*1000)); //Mars (687 days)

		for(int i = 0; i<delta.size(); i++) angle.add(0.0);

		//Creates and starts Timer to periodically update position of planets in model
		animationTimer = new Timer(delay, this);
		animationTimer.start();
	}

	/**
	 * Draws model of solar system, including Sun and all planets. All planets initially
	 * aligned along +ve x-axis.
	 * Equations used: x=rcos(theta) & y=rsin(theta) to calculate (x, y) position of planet
	 * relative to Sun with each updated angle
	 */
	protected void paintComponent(Graphics g) {
		//Calls superclass method, downcasts to Graphics2D and edges are antialiased for aesthetic 
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//Finding centre (origin) of panel, as well as setting arbitrary radius of Sun
		int centreX = getWidth()/2;
		int centreY = getHeight()/2;
		int radius = getWidth()/50;

		ArrayList<Double> orbRad = new ArrayList<Double>(); //ArrayList of all relative orbital radii
		double farthest = 1.524; //Orbital radius of farthest planet in AU (Mars)

		/* Orbital radius of planets relative to orbital radius of farthest planet from Sun
		 * Relationship used: (T2/T1)^2=(r2/r1)^3, which is calculated from Kepler's 3rd Law.
		 * Divided by 7 to fit onto screen, since Kepler's 3rd Law gives radius that extends
		 * beyond edge of screen for given orbital period */
		orbRad.add(Math.cbrt(Math.pow(period*(88/88)/(period*225/88), 2))*centreX*(0.723/farthest)/7); //Mercury
		orbRad.add(Math.cbrt(Math.pow((period*225/88)/period*(88/88), 2))*centreX*(0.39/farthest)/7); //Venus
		orbRad.add(Math.cbrt(Math.pow((period*365/88)/period*(225/88), 2))*centreX*(0.723/farthest)/7); //Earth
		orbRad.add(Math.cbrt(Math.pow((period*687/88)/period*(365/88), 2))*centreX*(1/farthest)/7); //Mars

		//Sun - Yellow circle centred on origin of panel
		g2.setColor(Color.YELLOW);
		g2.fillOval(centreX-radius/2, centreY-radius/2, radius, radius);

		//Mercury - Grey circle next to Sun 
		g2.setColor(Color.GRAY);
		g2.fillOval((int) (Math.cos(angle.get(0))*(orbRad.get(0))+centreX), (int) (Math.sin(angle.get(0))*(orbRad.get(0))+centreY), 5, 5);

		//Venus - Orange circle next to Mercury
		g2.setColor(Color.ORANGE);
		g2.fillOval((int) (Math.cos(angle.get(1))*(orbRad.get(1))+centreX), (int) (Math.sin(angle.get(1))*(orbRad.get(1))+centreY), 9, 9);

		//Earth - Blue circle next to Venus
		g2.setColor(Color.BLUE);
		g2.fillOval((int) (Math.cos(angle.get(2))*(orbRad.get(2))+centreX), (int) (Math.sin(angle.get(2))*(orbRad.get(2))+centreY), 11, 11);

		//Mars - Red circle next to Earth
		g2.setColor(Color.RED);
		g2.fillOval((int) (Math.cos(angle.get(3))*(orbRad.get(3))+centreX), (int) (Math.sin(angle.get(3))*(orbRad.get(3))+centreY), 7, 7);
	}

	/**
	 * Starts animation
	 */
	public void start() {
		animationTimer.start();
	}

	/**
	 * Stops animation
	 */
	public void stop() {
		animationTimer.stop();
	}

	/**
	 * Called by animation Timer at regular intervals to rotate planets about
	 * origin, by increasing angle of each planet relative to +ve x-axis by its
	 * change in angle, and updates displayed graphics through repaint().
	 */
	public void actionPerformed(ActionEvent event) {
		for(int i = 0; i<angle.size(); i++) {
			angle.set(i, angle.get(i)+delta.get(i));
		}
		repaint();
	}
}