package module3;
public class Alphabet {

	//GENERATES RANDOM CHARACTER FROM INTEGER
	public static char randomCharacter() {
		//Random integer between 0 and 127 (both inclusive) is generated
		//'Math.random()' generates random number between 0 (inclusive) and 1
		int randn = (int)(Math.random()*128);
		char randc = (char) randn; //Casts integer to character
		return randc; //Returns character
	}

	public static void main(String[] args) {

		//'StringBuilder' object created to store string of characters
		StringBuilder str = new StringBuilder();

		//Initialising variables
		int total = 0; //Sum of all random integers
		int excepn = 0; //Number of exceptions thrown
		int n = 400; //Number of times loop is run

		while(n>0) { //Runs while n>0
			n--; //Reduced n by 1 each loop
			char rand = randomCharacter(); //Generates a random character
			if(Character.isLetterOrDigit(rand)) { //Checks if character is letter/digit
				str.append(rand); //If letter/digit, character appended to 'StringBuilder' object

				//Tries to interpret character as number by converting to string first
				//If character is number, it is added to 'total' variable
				try {
					int num = Integer.parseInt(String.valueOf(rand));
					total += num;
				}
				//If character is letter, exception thrown and 'excepn' variable increased by 1
				catch (Exception e1) {
					excepn++;
				}
			}			
		}

		//Print statements to output required results
		System.out.println("String: "+str);
		System.out.println("\nLength of String: "+str.length()+" characters");
		System.out.println("\nSum of Random Integers: "+total);
		System.out.println("\nTotal Number of Exceptions: "+excepn);
	}
}