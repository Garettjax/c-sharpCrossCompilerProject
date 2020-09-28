
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Main {

	// create hash map

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		//System.out.println("Please provide a valid path to input.txt file: ");
		ArrayList<Token> tokens = new ArrayList<Token>();
		String text = "", line = "";
		
		try {
			//String path = bufferedReader.readLine();
			bufferedReader.close();
			//TODO find a way to get the project's relative path for this file
			//Note: It automatically does in IntelliJ, so it might be IDE based problem?
			//Use one or the other
			String path = ("input.txt");
			//String path = "C:\\Users\\Brett\\eclipse-workspace\\c-sharpCrossCompilerProject\\bin\\input.txt";

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
			}


		//	System.out.println(temp);


			/*
			int temp =2;
			int	temp1;
			for(Token element1 : tokens) {

			//	System.out.println(element1.rowNum);
				if(element1.rowNum == temp){
					System.out.print(element1.getLexeme());
				}
				System.out.println();


				temp1= element1.rowNum;
				System.out.println(temp +" "+ element1.rowNum);
				if(element1.rowNum!=temp1){


				}
				temp++;
			//	temp = element1.rowNum;*/

			//	if(temp == element1.rowNum){



			//	}
			//	System.out.println(element1.getLexeme());



			//		for(int i =element1.rowNum; ){


		//			}

					//	for (Token element : tokens) {

					//		int currentLine = element1.getRowNumber();
							//if(element.getLexeme() != null) {

					//		System.out.print(element1.getLexeme());

							//	System.out.println(element.getTokenType());
							//	System.out.println(element.getRowNumber());

							//}
					//	}

		//		System.out.println();

			Translation translation = new Translation();
			tokens = Translation.TranslateTokensToJava(tokens);

			int temp2=0;
			for (Token element1 : tokens) {

				if(temp2!= element1.rowNum){
					System.out.println();

				}
				if((!element1.getLexeme().equals("-"))&&(!element1.getLexeme().equals("+"))){


					System.out.print(element1.getLexeme() + " ");
				}
				else{
					System.out.print(element1.getLexeme() + element1.getLexeme());

				}

				//	System.out.print(element1.rowNum);
				temp2= element1.rowNum;
				//		System.out.println(element1.rowNum);

			}
			
		}
		catch (Exception e) {
			bufferedReader.close();
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}