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
		System.out.println("Please Enter The Save Directory:"); //Prompts user to enter save directory of data files
		InputStreamReader isr = new InputStreamReader(System.in); //Reads characters input from keyboard
		BufferedReader br = new BufferedReader(isr); //Buffers data from keyboard to memory for faster access
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
	void analyseData(String line) { //Takes line of data as a 'String' from URL as argument
		Scanner sc = new Scanner(line); //Looks for tokens in line separated by whitespace

		/*Line is only analysed if it is not empty or first character is not a letter
		So line only analysed if line contains numbers*/
		if((line.isEmpty() || Character.isLetter(line.charAt(0))) == false) {

			//Analyses current token while there is next token
			while(sc.hasNext()) {
				//Returns new 'double' initialised to value of token
				double token = Double.parseDouble(sc.next());
				System.out.println(token); //Prints number to screen
				pw.println(token); //Writes number to file 
				sumOfValues += token; //Adds number to 'sumOfValues'
				nValues++; //Increases 'nValues' by 1 per number

				/*Sets 'minValue' to number if either number is less than 'minValue'
				Or if 'minValue' is 0 (setting its initial value)*/
				if(token < minValue || minValue == 0) {
					minValue = token;
				}
				/*Sets 'maxValue' to number if either number is greater than 'maxValue'
				Or if 'maxValue' is 0 (setting its initial value*/
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
		pw.close(); //Closes the file for writing and cleans the memory
		System.out.println("\nMinimum Value is "+minValue);
		System.out.println("Maximum Value is "+maxValue);
		System.out.println("Number of Values is "+nValues);
		System.out.println("Sum of Values is "+sumOfValues);
		System.out.println("Average Value is "+sumOfValues/nValues+"\n");
	}

	public static void main(String[] args) {

		//Creating 2 'NumericalReader' objects to analyse data from 2 URLs 
		NumericalReader nr1 = new NumericalReader();
		NumericalReader nr2 = new NumericalReader();

		//2 'String' variables for name of data files
		String dataFile1 = "numbers1.txt";
		String dataFile2 = "numbers2.txt";

		BufferedReader reader = null; //Initialises variable for reading line
		String line = ""; //Initialises variable for storing line as 'String' 
		String saveDir; //Initialises variable for storing save directory as 'String'

		/*Tries to call 'getStringFromKeyboard' method to specify save directory of data.
		If there is no user input, IOException is thrown and save directory defaults to
		home directory, which is also printed in the error message*/
		try {
			saveDir = NumericalReader.getStringFromKeyboard(); //Sets input string as save directory
		}
		catch (IOException e1) {
			saveDir = System.getProperty("user.home"); //Sets home directory as save directory
			System.out.println(e1+saveDir);
		}

		//Stores file location for 1st data file as 'String'
		String saveFile1 = (saveDir + File.separator + dataFile1);

		/*Tries to call 'brFromURL' method for 'nr1' and assigns it to 'BufferedReader' variable, so that
		contents of URL can be read. If invalid URL is entered, IOException is thrown and error message shown*/
		try {
			reader = nr1.brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data1.txt");
		}
		catch(IOException e2) {
			System.out.println("Please enter a valid URL!"); //Message when invalid URL entered
		}

		/*Tries to call 'analysisStart' method for 'nr1' to create data file and initialise variables,
		but if method throws an IOException, error message will be printed*/
		try {
			nr1.analysisStart(saveFile1); //Creates data file and initialises variables
		}
		catch (IOException e3) {
			System.out.println(e3);
		}

		/*Tries to call 'analyseData' method for 'nr1' to analyse line of text from URL, while the 'BufferedReader'
		object is not empty. 'analyseData' method prints numbers to screen, writes numbers to file and updates all
		variables. If method throws IOException, error message will be printed*/
		try {		
			while ((line = reader.readLine()) != null) {
				nr1.analyseData(line); //Analyses line, updating variables and printing to screen/writing to file
			}
		}
		catch (IOException e4) {
			System.out.println(e4);
		}

		nr1.analysisEnd(); //Prints maximum, minimum, sum of, total number of and average value(s)

		/*Tries to call 'brFromURL' method for 'nr2' and assigns it to 'BufferedReader' variable, so that
		contents of URL can be read. If invalid URL is entered, IOException is thrown and error message shown*/
		try {
			reader = nr2.brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data2.txt");
		}
		catch(IOException e5) {
			System.out.println("Please enter a valid URL!"); //Message when invalid URL entered
		}

		//Stores file location for 2nd data file as 'String'
		String saveFile2 = (saveDir + File.separator + dataFile2);

		/*Tries to call 'analysisStart' method for 'nr2' to create data file and initialise variables,
		but if method throws an IOException, error message will be printed*/
		try {
			nr2.analysisStart(saveFile2); //Creates data file and initialises variables
		}
		catch (IOException e6) {
			System.out.println(e6);
		}

		/*Tries to call 'analyseData' method for 'nr2' to analyse line of text from URL, while the 'BufferedReader'
		object is not empty. 'analyseData' method prints numbers to screen, writes numbers to file and updates all
		variables. If method throws IOException, error message will be printed*/
		try {		
			while ((line = reader.readLine()) != null) {
				nr2.analyseData(line); // analyse lines, check for comments etc.
			}
		}
		catch (IOException e7) {
			System.out.println(e7);
		}
		nr2.analysisEnd(); //Prints maximum, minimum, sum of, total number of and average value(s)
	}
}
