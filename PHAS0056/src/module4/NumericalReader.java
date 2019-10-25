package module4;
import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package for 'Scanner'

public class NumericalReader {

	//Member variables
	private double minValue; //Minimum value of numbers within URL
	private double maxValue; //Maximum value of numbers within URL
	private int nValues; //Total number of numbers within URL
	private double sumOfValues; //Sum of numbers within URL
	private FileWriter f; //Allows characters to be written to files
	private BufferedWriter bw; //Buffers data to memory for faster writing to files
	private PrintWriter pw; //Allows writing of formatted data to file

	//ALLOWS USER TO ENTER 'String' VIA KEYBOARD AND STORES RESULTING 'String'
	public static String getStringFromKeyboard() throws IOException{ //Specifies that method throws an IOException
		InputStreamReader isr = new InputStreamReader(System.in); //Reads characters input from keyboard
		BufferedReader br = new BufferedReader(isr); //Buffers data from keyboard to memory for faster access
		System.out.println("Please Enter The Save Directory:"); //Prompts user to enter save directory of data files
		String s = br.readLine(); //Reads line of text entered and stores as a 'String'

		//Throws exception if user provide no keyboard input
		if(s.isEmpty()) {
			throw new IOException("No Save Directory Specified! Default Directory: ");
		}
		return s; //Returns save directory as 'String'
	}

	//TAKES URL AS ARGUMENT AND RETURNS 'BufferedReader' OBJECT
	public BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	//CREATES FILE CALLED 'dataFile' AND INITIALISES PRIVATE VARIABLES
	void analysisStart(String dataFile) throws IOException{ //Specifies that method throws an IOException
		f = new FileWriter(dataFile); //Creates new file called 'dataFile' to which characters can be written
		bw = new BufferedWriter(f); //Buffers data to memory for faster writing to file
		pw = new PrintWriter(bw); //Allows writing of formatted data to specified file

		//Initialising member variables for analysis of data
		minValue = 0;
		maxValue = 0;
		nValues = 0;
		sumOfValues = 0;
	}

	//ANALYSES EACH LINE OF DATA FROM URL
	void analyseData(String line) throws IOException { //Specifies that method throws and IOException
		Scanner sc = new Scanner(line); //Looks for tokens in line separated by whitespace

		/*Line is only analysed if it is not empty or first character is not a letter
		So line only analysed if line contains numbers*/
		if((line.isEmpty() || Character.isLetter(line.charAt(0))) == false) {

			//Analyses current token while there is next token
			while(sc.hasNext()) {
				//Returns new 'double' initialised to value of token
				double token = Double.parseDouble(sc.next());
				System.out.println(token); //Prints value of number to screen
				pw.println(token); //Writes value of number to file 
				sumOfValues += token; //Adds number to 'sumOfValues'
				nValues++; //Increases 'nValues' by 1 per number

				//Sets 'minValue' to number if either number is less than 'minValue'
				//Or if 'minValue' is 0, to set its initial value
				if(token < minValue || minValue == 0) {
					minValue = token;
				}
				//Sets 'maxValue' to number if either number is greater than 'maxValue'
				//Or if 'maxValue' is 0, to set its initial value
				else if(token > maxValue || maxValue == 0) {
					maxValue = token;
				}
			}
			sc.close(); //Closing resource to clean memory
		}
	}

	//PRINTS MINIMUM, MAXIMUM, TOTAL SUM OF, NUMBER OF AND AVERAGE VALUE(S) 
	void analysisEnd() {
		//Closing 'PrintWriter' closes 'BufferedWriter' and 'FileWriter'
		pw.close(); //Closes resource to clean memory
		System.out.println("\nMinimum Value is "+minValue);
		System.out.println("Maximum Value is "+maxValue);
		System.out.println("Number of Values is "+nValues);
		System.out.println("Sum of Values is "+sumOfValues);
		System.out.println("Average Value is "+sumOfValues/nValues+"\n");
	}

	public static void main(String[] args) {

		//Creating 2 'NumericalReadr' objects to analyse data from 2 URLs 
		NumericalReader nr1 = new NumericalReader();
		NumericalReader nr2 = new NumericalReader();

		BufferedReader reader = null; //Initialises variable for reading line
		String line = ""; //Initialises variable for storing line as 'String' 
		String saveDir; //Initialises variable for storing save directory as 'String'

		/*Tries to call 'getStringFromKeyboard' method to specify save directory of data.
		If there is no user input, IOException is thrown and save directory defaults to
		home directory, which is printed*/
		try {
			saveDir = NumericalReader.getStringFromKeyboard();
		}
		catch (IOException e1) {
			saveDir = System.getProperty("user.home");
			System.out.println(e1+saveDir);
		}

		String saveFile1 = (saveDir + File.separator + "numbers1.txt"); //Stores file location as 'String'

		/*Tries to call 'brFromURL' method for 'nr1' and assigns it to 'BufferedReader' variable, so that
		contents of URL can be read. If invalid URL is entered, IOException is thrown and error message shown*/
		try {
			reader = nr1.brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data1.txt");
		}
		catch(IOException e2) {
			System.out.println("Please enter a valid URL!");
		}

		/**/
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
