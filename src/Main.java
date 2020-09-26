
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
			
			Translation translation = new Translation();
			tokens = Translation.TranslateTokensToJava(tokens);
			
			for (Token element : tokens) {
				int currentLine = element.getRowNumber();
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