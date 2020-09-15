//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//
//public class Main {
//	public static void main(String args[]) throws IOException {
//
//
//		boolean debug = false;
//		boolean debug1 = true;
//		HashMap<String, Integer> assignVar = new HashMap<String, Integer>();
//		String line;
//		String[] elements;
//		int lineCounter = 0;
//		String space = " ";
//		//Buffered reader & Buffered Writer
//		//For input.cs and output.java
//		try (
//				InputStream in = new FileInputStream("input.cs");
//				InputStreamReader inR = new InputStreamReader(in, StandardCharsets.UTF_8);
//				BufferedReader br = new BufferedReader(inR);
//				OutputStream out = new FileOutputStream("output.java");
//				OutputStreamWriter outW = new OutputStreamWriter(out, StandardCharsets.UTF_8);
//				BufferedWriter bw = new BufferedWriter(outW)
//
//
//		) {
//			//Takes line and breaks it into "elements" by space
//			while ((line = br.readLine()) != null) {
//				elements = line.split(space);
//				for (int i = 0; i < elements.length; i++) {
//					if (i == 0) {
//						lineCounter++;
//					}
//
//					//writing to output.txt for making sure it is working
//					if (debug) {
//						bw.write(elements[i] + " ");
//					}
///*
//declaration statement
//                    Ex: int counter;
//                    if it starts with int, double, char, string etc and ends with ";"
//                    TODO: make sure it ends with ;
//                    possible in c# to do int counter, timer, stopwatch?
//*/
//					//checks if any of the primitive data types are found at index 0
//
//
//					for (EnumPrimitiveDataType m : EnumPrimitiveDataType.values()) {
//						if (elements[i].equals(m.getDataType())) {
//
//							//rest of the stuff will be handled in its class
//							if (debug) {
//								System.out.println("Line: " + lineCounter + "" + " PrimDataType: " + m.getDataType());
//							}
//						}
//					}
///*
//assignment statement
//                    if its declared before
//                    Ex: counter = 1
//
//*/
//					if (elements[i].equals("" + EnumToken.ASSIG.getString()) && (i == 1 )) {
//						//add hashmap to see if it was declared before
//
//
//						if (debug) {
//							System.out.println("" + elements[i]); //prints the "=" operator for testing
//						}
//
//						for (EnumPrimitiveDataType m : EnumPrimitiveDataType.values()) {
//							if (elements[i-1].equals(m.getDataType())) {
//								//   AssignmentStatement.assign();
//								//rest of the stuff will be handled in its class
//								if (debug) {
//									System.out.println("PrimDataType " + m.getDataType());
//
//								}
//							}
//						}
//					}
//
//					//if its not declared before
//					//Ex: int x = 5;
//					if (elements[i].equals("" + EnumToken.ASSIG.getString()) && (i == 2)) {
//						//add hashmap to see if it was declared before
//
//
//						if (debug) {
//							System.out.println("" + elements[i]); //prints the "=" operator for testing
//						}
//
//						for (EnumPrimitiveDataType m : EnumPrimitiveDataType.values()) {
//							if (elements[i-2].equals(m.getDataType())) {
//								// DeclarationStatement.assign();
//								//rest of the stuff will be handled in its class
//								if (debug) {
//									System.out.println("PrimDataType " + m.getDataType());
//
//								}
//							}
//						}
//					}
///*
//FOR LOOP
// */
//					if (elements[i].equals("" + EnumKeyword.FOR.getString())&&i==0) {
//						//rest of the stuff will be handled in its class
//						if (debug) {
//							System.out.println(elements[i]);
//
//						}
//					}
///*
//IF STATEMENT
// */
//					if (elements[i].equals("" + EnumKeyword.IF.getString())&&i==0) {
//						//rest of the stuff will be handled in its class
//						if (debug) {
//							System.out.println(elements[i]);
//						}
//					}
//
//
//
//
//				}//End of for loop that goes through elements array
//			}//end of while
//		}//try
//	}//end of public static void main}
//}//end of main class

//package ParserProject;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("Please provide a valid path to input.txt file: ");
		ArrayList<Token> tokens = new ArrayList<Token>();
		String text = "", line = "";
		
		try {
			//String path = bufferedReader.readLine();
			bufferedReader.close();
			//TODO find a way to get the project's relative path for this file
			String path = "C:\\Users\\Brett\\eclipse-workspace\\c-sharpCrossCompilerProject\\bin\\input.txt";
			
			File file = new File(path);
			bufferedReader = new BufferedReader(new FileReader(path));
			//System.out.println(path);
			
			
			while ((line = bufferedReader.readLine()) != null) {
				text += line + '\n';
			}
			bufferedReader.close();
			
			CustomParser customParser = new CustomParser(text);
			
			tokens = customParser.getAllTokens(customParser);
			
			Iterator<Token> itr = tokens.iterator();
			while(itr.hasNext()) {
				Token next = itr.next();
				if(next.getRowNumber() == -1) {
					itr.remove();
				}
				
			//TODO: Translate this to Java and output source code (I'm not sure why we need to do this)
				
			//TODO: Syntax Checking and error handling
				
			//TODO: Execute this C# that we have turned into tokens
			}
			for (Token element : tokens) {
				//if(element.getLexeme() != null) {
					System.out.println("\n******************************");
					System.out.println(element.getLexeme());
					System.out.println(element.getTokenType());
					System.out.println(element.getRowNumber());
					System.out.println("******************************\n");
				//}
			}
			
		}
		catch (Exception e) {
			bufferedReader.close();
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}





