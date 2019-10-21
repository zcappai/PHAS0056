package module4;
import java.io.*;
import java.net.*;
import java.util.*;

public class WordCounter {

	public static BufferedReader brFromURL(String urlName) throws IOException{
		URL u = new URL(urlName);
		InputStream is = u.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		return br;
	}

	public static BufferedReader brFromFile(String fileName) throws IOException{
		FileReader fr = new FileReader(fileName);
		return new BufferedReader(fr);
	}

	public static int countWordsInResource(BufferedReader dataAsBR) {
		Scanner s = new Scanner(dataAsBR);
		int total = 0;
		while(s.hasNext()) {
			s.next();
			total++;
		}
		s.close();
		return total;
	}

	public static void main(String[] args) {
		try {
			System.out.println("Number of Words in URL: "+countWordsInResource(brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt")));
			System.out.println("\nUpon comparing the total words in the URL to the same text in Microsoft Word, there is a small discrepancy in the total number\n"
					+ "of words. This is because the delimiter in Java is white space, therefore when reading text with a double hyphen, Java reads the\n"
					+ "text as 1 word. Whereas Microsoft Word reads 2 words separated by a double hyphen as 2 words. This is the source of the discrepancy.");
		}
		catch (IOException e1) {
		}
	}
}
