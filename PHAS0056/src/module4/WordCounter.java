package module4;
import java.io.*; //Importing input/output package
import java.net.*; //Importing networking package for 'URL'
import java.util.*; //Importing utilities package for 'Scanner'

public class WordCounter {

	//TAKES URL AS ARGUMENT AND RETURNS 'BUFFEREDREADER OBJECT
	public static BufferedReader brFromURL(String urlName) throws IOException{ //Specifies that method throws an IOException
		URL u = new URL(urlName); //Creates 'URL' object with URL from argument
		InputStream is = u.openStream(); //Using 'URL' objects openSteam method to get input stream from URL
		InputStreamReader isr = new InputStreamReader(is); //Reads from InputStream (reads contents of URL)
		//Returns 'BufferedReader' object, which buffers large parts of data from InputStream to memory for faster access
		return new BufferedReader(isr);
	}

	//TAKES NAME OF FILE ON DISK AS ARGUMENT AND RETURNS 'BUFFEREDREADER' OBJECT
	public static BufferedReader brFromFile(String fileName) throws IOException{ //Specifies that method throws an IOException
		FileReader fr = new FileReader(fileName); //Readers characters from specified file
		return new BufferedReader(fr); //Returns 'BufferedReader' object, which buffers data from file for faster access
	}

	//COUNTS NUMBER OF WORDS IN SPECIFIED RESOURCE
	public static int countWordsInResource(BufferedReader dataAsBR) { //Takes 'BufferedReader' object from URL/file as argument
		Scanner s = new Scanner(dataAsBR); //Looks for tokens in 'BufferedReader' objects separated by whitespace
		int total = 0; //Initialises total number of words
		//While 'Scanner' object has next token, 'total' variable is increased by 1
		while(s.hasNext()) {
			s.next(); //Finds and returns next complete token from 'Scanner' object
			total++; //Increase 'total' words variable by 1
		}
		s.close(); //Closing resource to clean memory
		return total; //Returns 'total' number of words
	}

	public static void main(String[] args) {

		//Tries to count number of words in specified URL and gives comments on outcome
		//IOException thrown when I/O exception occurs
		try {
			System.out.println("Number of Words in URL: "+countWordsInResource(brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt")));
			System.out.println("\nUpon comparing the total words in the URL to the same text in Microsoft Word, there is a small discrepancy in the total number\n"
					+ "of words. This is because the delimiter in Java is white space, therefore when reading text with a double hyphen, Java reads the\n"
					+ "text as 1 word. Whereas Microsoft Word reads 2 words separated by a double hyphen as 2 words. This is the source of the discrepancy.");
		}
		catch (IOException e1) {
			System.out.println(e1);
		}
	}
}
