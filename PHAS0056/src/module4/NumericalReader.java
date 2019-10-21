package module4;
import java.io.*;
import java.net.*;
import java.util.*;

public class NumericalReader {
	
	private double minValue;
	private double maxValue;
	private double nValues;
	private double sumOfValues;

	public static String getStringFromKeyboard() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("Please Type Something: ");
		String s = br.readLine();
		return s;
	}

	public BufferedReader brFromURL(String urlName) throws IOException{
		URL u = new URL(urlName);
		InputStream is = u.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		return br;
	}

	void analysisStart(String dataFile) throws IOException{
		FileWriter fw = new FileWriter(dataFile);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
	}

	void analyseData(String line) {
		
	}

	public static void main(String[] args) {
		NumericalReader x = new NumericalReader();
		try {
			getStringFromKeyboard();
		}
		catch(Exception e1) {
		}

	}

}
