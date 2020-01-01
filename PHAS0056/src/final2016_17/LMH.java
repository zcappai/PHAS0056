package final2016_17;

import java.util.*;

public class LMH implements Identity{

	public String classify(Object[] input) {
		String classification = "";
		RecordInfo info = (RecordInfo) input[2];
		ArrayList<Long> amps = (ArrayList<Long>) input[3];
		double t = info.n/info.freq;
		double Hz100 = spectralDensity(amps, t, 100);
		double Hz400 = spectralDensity(amps, t, 400);
		double Hz1000 = spectralDensity(amps, t, 1000);
		if(Hz100 > Hz400 & Hz100 > Hz1000) {
			classification = "low";
		}
		else if(Hz400 > Hz100 & Hz400 > Hz1000) {
			classification = "medium";
		}
		if(Hz1000 > Hz400 & Hz1000 > Hz100) {
			classification = "high";
		}
		return classification;
	}

	private double spectralDensity(ArrayList<Long> samples, double t, double f) {
		int bigN = samples.size();
		double z = 2 * Math.PI * f * t / bigN;
		double sumCos = 0;
		double sumSin = 0;
		for (int n = 0; n < bigN; ++n) {
			sumCos += samples.get(n)* Math.cos(z*n);
			sumSin += samples.get(n)* Math.sin(z*n);
		}
		double norm = t / (bigN*bigN);
		return norm * (sumCos*sumCos + sumSin*sumSin);
	}
}