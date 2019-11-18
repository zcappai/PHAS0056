package module6;

public class LabelledDataPoint extends DataPoint {

	protected String label;

	public LabelledDataPoint(String label, double x, double y, double ey) {
		super(x, y, ey);
		this.label = label;
		this.x = x;
		this.y = y;
		this.ey = ey;
	}

	@Override
	public String toString() {
		return label + ": x = " + x + ", y = " + y + " +- " + ey;
	}
}
