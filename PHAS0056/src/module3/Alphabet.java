package module3;
public class Alphabet {
	
	public static char randomCharacter() {
		int randn = 0 + (int)(Math.random()*((127 - 0) + 1));
		char randc = (char) randn;
		return randc;
	}

	public static void main(String[] args) {

		StringBuilder str = new StringBuilder();
		int total = 0;
		int excepn = 0;
		int n = 400;

		while(n>0) {
			n--;
			char rand = randomCharacter();
			if(Character.isLetterOrDigit(rand)) {
				str.append(rand);
				try {
					int num = Integer.parseInt(String.valueOf(rand));
					total = total + num;
				}
				catch (Exception e1) {
					excepn++;
				}
			}			
		}
		System.out.println("String: "+str);
		System.out.println("\nLength of String: "+str.length()+" characters");
		System.out.println("\nSum of Added Numbers: "+total);
		System.out.println("\nTotal Number of Exceptions: "+excepn);
	}
}