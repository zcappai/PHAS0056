package module6;

import java.util.*;

public interface GoodnessOfFitCalculator {

	public double goodnessOfFit(Collection<DataPoint> data, Theory theo);
	public Collection<Double> quadCollection(Collection<DataPoint> data, QuadraticTheory n);
	public Collection<Double> powerCollection(Collection<DataPoint> data, PowerLawTheory n);
}