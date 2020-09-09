
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

// test push Chris valentin
public class Main {
	public static void main(String args[]) throws IOException {


		boolean debug = false;
		boolean debug1 = true;
		HashMap<String, Integer> assignVar = new HashMap<String, Integer>();
		String line;
		String[] elements;
		int lineCounter = 0;
		String space = " ";
		//Buffered reader & Buffered Writer
		//For input.cs and output.java
		try (
				InputStream in = new FileInputStream("input.cs");
				InputStreamReader inR = new InputStreamReader(in, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(inR);
				OutputStream out = new FileOutputStream("output.java");
				OutputStreamWriter outW = new OutputStreamWriter(out, StandardCharsets.UTF_8);
				BufferedWriter bw = new BufferedWriter(outW)


		) {
			//Takes line and breaks it into "elements" by space
			while ((line = br.readLine()) != null) {
				elements = line.split(space);
				for (int i = 0; i < elements.length; i++) {
					if (i == 0) {
						lineCounter++;
					}

					//writing to output.txt for making sure it is working
					if (debug) {
						bw.write(elements[i] + " ");
					}
/*
declaration statement
                    Ex: int counter;
                    if it starts with int, double, char, string etc and ends with ";"
                    TODO: make sure it ends with ;
                    possible in c# to do int counter, timer, stopwatch?
*/
					//checks if any of the primitive data types are found at index 0


					for (EnumPrimitiveDataType m : EnumPrimitiveDataType.values()) {
						if (elements[i].equals(m.getDataType())) {

							//rest of the stuff will be handled in its class
							if (debug) {
								System.out.println("Line: " + lineCounter + "" + " PrimDataType: " + m.getDataType());
							}
						}
					}
/*
assignment statement
                    if its declared before
                    Ex: counter = 1

*/
					if (elements[i].equals("" + EnumToken.ASSIG.getString()) && (i == 1 )) {
						//add hashmap to see if it was declared before


						if (debug) {
							System.out.println("" + elements[i]); //prints the "=" operator for testing
						}

						for (EnumPrimitiveDataType m : EnumPrimitiveDataType.values()) {
							if (elements[i-1].equals(m.getDataType())) {
								//   AssignmentStatement.assign();
								//rest of the stuff will be handled in its class
								if (debug) {
									System.out.println("PrimDataType " + m.getDataType());

								}
							}
						}
					}

					//if its not declared before
					//Ex: int x = 5;
					if (elements[i].equals("" + EnumToken.ASSIG.getString()) && (i == 2)) {
						//add hashmap to see if it was declared before


						if (debug) {
							System.out.println("" + elements[i]); //prints the "=" operator for testing
						}

						for (EnumPrimitiveDataType m : EnumPrimitiveDataType.values()) {
							if (elements[i-2].equals(m.getDataType())) {
								// DeclarationStatement.assign();
								//rest of the stuff will be handled in its class
								if (debug) {
									System.out.println("PrimDataType " + m.getDataType());

								}
							}
						}
					}
/*
FOR LOOP
 */
					if (elements[i].equals("" + EnumKeyword.FOR.getString())&&i==0) {
						//rest of the stuff will be handled in its class
						if (debug) {
							System.out.println(elements[i]);

						}
					}
/*
IF STATEMENT
 */
					if (elements[i].equals("" + EnumKeyword.IF.getString())&&i==0) {
						//rest of the stuff will be handled in its class
						if (debug) {
							System.out.println(elements[i]);
						}
					}




				}//End of for loop that goes through elements array
			}//end of while
		}//try
	}//end of public static void main}
}//end of main class






