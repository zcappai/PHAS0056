package module1;

public class DataTypes {

	public static void main(String[] args) {
		
		//Assigning a double variable the value of 5.0 and printing it
		double doubVar = 5.0; 
		System.out.println("The value of doubVar is "+doubVar);
		
		//Assigning a float variable the value of 5 and printing it
		float floatVar = 5;
		System.out.println("The value of floatVar is "+floatVar);
		
		//Assigning an integer variable the value of 5 and printing it
		int intVar = 5;
		System.out.println("The value of intVar is "+intVar);
		
		//Assigning a long variable the value of 5 and printing it
		long longVar = 5;
		System.out.println("The value of longVar is "+longVar);
		
		//Assigning a byte variable the value of 5 and printing it
		byte byteVar = 5;
		System.out.println("The value of byteVar is "+byteVar);
		
		//Printing results from multiplying each variable above by itself
		System.out.println("\ndoubVar squared is "+doubVar*doubVar);
		System.out.println("floatVar squared is "+floatVar*floatVar);
		System.out.println("intVar squared is "+intVar*intVar);
		System.out.println("longVar squared is "+longVar*longVar);
		System.out.println("byteVar squared is "+byteVar*byteVar);
		
		//INVESTIGATING THE RESULT WHEN VARIABLE TYPES ARE MIXED
		//Result from integer + string in string format
		String newVar1 = 5 + "HelloWorld";
		System.out.println("\n1a)5 + 'HelloWorld': "+newVar1+", this has appended the beginning of the string with the integer.");
		
		//Result from letter + number in character format
		char newVar2 = 'a' + 6;
		System.out.println("2)'a' + 6: "+newVar2+", this has changed the character 'a' to 'g', which is 6 spaces after 'a' in the alphabet.");
		
		//Result from letter in integer format
		int newVar3 = 'a';
		System.out.println("3)"+newVar3+", is the value of the letter 'a' in decimal. This can be confirmed by looking at the ASCII table.");
		
		//Result from letter - number in character format when result is taken beyond alphabet
		char newVar4 = 'a' - 12;
		System.out.println("4)"+newVar4+", is the value of the letter 'a' as a character minus 12. This can be explained using ASCII in which 'U' is 12 characters behind 'a'.");
		
		//Result from letter - number in byte format when result is taken beyond alphabet
		byte newVar5 = 'a' - 12;
		System.out.println("5)"+newVar5+", is the value of the letter 'a' as a byte minus 12. This can be explained using ASCII in which the decimal of 'a'(97) minus 12 is 85.\n");
		
		//USING AN UNINITIALISED VARIABLE
		//int j=1; int i; j=i+1;
		//The incorrect code above gives an error. Since the integer 'i' has not been initialised, it cannot be called for use in the calculation.
		
		//CASTING FROM double TO int
		//Assigning a double variable the value of 4.99
		double doubVar1 = 4.99;
		int intVar1 = (int) doubVar1; //Casting the double variable to an int variable
		System.out.println("Casting double to int: "+intVar1);
		System.out.println("The double, doubVar1, has been converted to an integer by truncating (removing) the decimal numbers and assigning it to intVar1, which is an integer.");
	}

}
