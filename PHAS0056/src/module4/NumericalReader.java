package module4;
import java.io.*;
import java.net.*;
import java.util.*;

public class NumericalReader {

	private double minValue;
	private double maxValue;
	private int nValues;
	private double sumOfValues;
	private File file;
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
		//File outputfile = new File(dataFile);
		//file = outputfile;
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
				//FileWriter fw = new FileWriter(file);
				//BufferedWriter bw = new BufferedWriter(fw);
				//PrintWriter pw = new PrintWriter(bw);
				pw.println(token);
				sumOfValues += token;
				nValues++;
				if(token < minValue) {
					minValue = token;
				}
				else if(token > maxValue) {
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
		System.out.println("Sum of Values is "+sumOfValues);
	}

	public static void main(String[] args) {

		NumericalReader nr = new NumericalReader();
		BufferedReader reader = null;
		try {
			reader = nr.brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data1.txt");
		}
		catch(IOException e1) {
			System.out.println("Please enter a valid URL!");
		}
		String line = "";
		String saveDir;

		try {
			saveDir = NumericalReader.getStringFromKeyboard();
		}
		catch (IOException e2) {
			saveDir = System.getProperty("user.home");
		}

		String saveFile = (saveDir + File.separator + "numbers1.txt");

		try {
			nr.analysisStart(saveFile); // initialise minValue etc.
		}
		catch (IOException e3) {
			System.out.println(e3);
		}
		try {		
			while ((line = reader.readLine()) != null) {
				nr.analyseData(line); // analyse lines, check for comments etc.
			}
		}
		catch (IOException e4) {
			System.out.println(e4);
		}
		nr.analysisEnd(); // print min, max, etc.

		/*NumericalReader x = new NumericalReader();
		try {
			getStringFromKeyboard();
		}
		catch(Exception e1) {
		}*/

	}

}
