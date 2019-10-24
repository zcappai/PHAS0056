package module4;
import java.io.*;
import java.net.*;
import java.util.*;

public class NumericalReader {

	private double minValue;
	private double maxValue;
	private int nValues;
	private double sumOfValues;
	private FileWriter f;
	private BufferedWriter bw;
	private PrintWriter pw;

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
		f = new FileWriter(dataFile);
		bw = new BufferedWriter(f);
		pw = new PrintWriter(bw);
		
		minValue = 0;
		maxValue = 0;
		nValues = 0;
		sumOfValues = 0;
	}

	void analyseData(String line) throws IOException {
		Scanner sc = new Scanner(line);
		if((line.isEmpty() || Character.isLetter(line.charAt(0))) == false) {
			while(sc.hasNext()) {
				double token = Double.parseDouble(sc.next());
				System.out.println(token);
				pw.println(token);
				sumOfValues += token;
				nValues++;
				if(token < minValue || minValue == 0) {
					minValue = token;
				}
				else if(token > maxValue || maxValue == 0) {
					maxValue = token;
				}
			}
			sc.close();
		}
	}

	void analysisEnd() {
		pw.close();
		System.out.println("\nMinimum Value is "+minValue);
		System.out.println("Maximum Value is "+maxValue);
		System.out.println("Number of Values is "+nValues);
		System.out.println("Sum of Values is "+sumOfValues+"\n");
	}

	public static void main(String[] args) {

		NumericalReader nr1 = new NumericalReader();
		NumericalReader nr2 = new NumericalReader();
		BufferedReader reader = null;
		String line = "";
		String saveDir;
		
		try {
			saveDir = NumericalReader.getStringFromKeyboard();
		}
		catch (IOException e2) {
			saveDir = System.getProperty("user.home");
		}

		String saveFile1 = (saveDir + File.separator + "numbers1.txt");

		try {
			reader = nr1.brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data1.txt");
		}
		catch(IOException e1) {
			System.out.println("Please enter a valid URL!");
		}

		try {
			nr1.analysisStart(saveFile1); // initialise minValue etc.
		}
		catch (IOException e3) {
			System.out.println(e3);
		}
		try {		
			while ((line = reader.readLine()) != null) {
				nr1.analyseData(line); // analyse lines, check for comments etc.
			}
		}
		catch (IOException e4) {
			System.out.println(e4);
		}
		nr1.analysisEnd(); // print min, max, etc.
		
		try {
			reader = nr2.brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data2.txt");
		}
		catch(IOException e5) {
			System.out.println("Please enter a valid URL!");
		}

		String saveFile2 = (saveDir + File.separator + "numbers2.txt");

		try {
			nr2.analysisStart(saveFile2); // initialise minValue etc.
		}
		catch (IOException e6) {
			System.out.println(e6);
		}
		try {		
			while ((line = reader.readLine()) != null) {
				nr2.analyseData(line); // analyse lines, check for comments etc.
			}
		}
		catch (IOException e7) {
			System.out.println(e7);
		}
		nr2.analysisEnd(); // print min, max, etc.
	}
}
