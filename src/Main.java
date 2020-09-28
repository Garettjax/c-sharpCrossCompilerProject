
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
			//Done
			String path = ("input.txt");
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

			tokens = Translation.TranslateTokensToJava(tokens);

			//TODO: make it so it prints out "translated code" in Java
			//Done
			//NOTES: Didn't change anything in customParser

			for (Token element : tokens) {
				System.out.println("*************************************");
				System.out.println(element.rowNum);
				System.out.println(element.getLexeme());
				System.out.println(element.getTokenType());
				System.out.println("*************************************");
				System.out.println();
			}
//For translation
		/*	int temp=0;
			for (Token element1 : tokens) {
				if(temp!= element1.rowNum){
					System.out.println();
				}
				if((!element1.getLexeme().equals("-"))&&(!element1.getLexeme().equals("+"))){
					System.out.print(element1.getLexeme() + " ");
				}
				else{
					System.out.print(element1.getLexeme() + element1.getLexeme());
				}
				temp= element1.rowNum;
			}*/
		}
		catch (Exception e) {
			bufferedReader.close();
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}



//TODO: somehow the line number for 2nd/3rd line bracket and the last bracket is getting messed up, check if the orignal file had that issue