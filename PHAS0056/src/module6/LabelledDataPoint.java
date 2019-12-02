package module6;

//SUBCLASS TO 'DataPoint'
//Creates constructor for 'LabelledDataPoint' object and 'toString' method
public class LabelledDataPoint extends DataPoint {

	protected String label;

	/**
	 * CONSTRUCTOR FOR 'LabelledDataPoint' OBJECT
	 * 'DataPoint' constructor called to make 'LabelledDataPoint' an instance of 'DataPoint'
	 * 'LabelledDataPoint' is sub-type of 'DataPoint'
	 * @param label of data point
	 * @param x-coordinate
	 * @param y-coordinate
	 * @param ey - Error on y
	 */
	public LabelledDataPoint(String label, double x, double y, double ey) {
		super(x, y, ey); //Calls 'DataPoint' constructor
		this.label = label; //Assigns member variable to corresponding argument
	}

	/**
	 * GETS LABEL OF DATA POINT
	 * @return
	 */
	public String getLabel() {	
		return label;
	}

	/**
	 * REPRESENTS 'LabelledDataPoint' OBJECT AS STRING
	 */
	@Override
	public String toString() {
		return getLabel() + ": x = " + getX() + ", y = " + getY() + " +- " + getEY();
	}
}