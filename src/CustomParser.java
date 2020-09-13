import java.util.ArrayList;

public class CustomParser {

    String text = "";
    int position = 0;
    int currentLine = 0;
    char lastChar = '\0';
    char currentChar = '\0';
    char nextChar = '\0';
    ArrayList<Token> tokens;

    //Constructor
    CustomParser(String text) {
        this.text = text;
        this.position = -1;
        this.currentLine = 0;
        this.lastChar = '\0';
        this.currentChar = 's';
        this.nextChar = '\0';
    }
    /* Returns and arrayList of tokens, each token is a string containing
     * the token type, token value, and line number for the token
     */
    public ArrayList<Token> getAllTokens(CustomParser self) {
        //Declare/instantiate variables
        Token token;
        String tempToken = "";
        tokens = new ArrayList<Token>();

        //This is for the very first character in input stream
        if(self.currentLine == 0) {
            moveForward(self);
        }

        //Keep going till the end of the file
        while(!self.endOfFile(self)) {

            //self explanatory functions
            removeComments(self);
            removeWhitespace(self);

            //If current character is a symbol then it's a token
            //Let's add the token
            if(containsSymbol(self.currentChar)) {
                switch (self.currentChar) {
                    case '(':
                        tokens.add(new Token("(", "LEFT_PAREN", self.currentLine));
                        //tokens.add("Left paren, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                    case ')':
                        tokens.add(new Token(")", "RIGHT_PAREN", self.currentLine));
                        //tokens.add("Right paren, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                    case '[':
                        tokens.add(new Token("[", "LEFT_BRACKET", self.currentLine));
                        //tokens.add("Left bracket, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                    case ']':
                        tokens.add(new Token("]", "RIGHT_BRACKET", self.currentLine));
                        //tokens.add("Right bracket, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                    case '+':
                        tokens.add(new Token("+", "ADDITION_OP", self.currentLine));
                        //tokens.add("Addition, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                    case '-':
                        tokens.add(new Token("-", "SUBTRACTION_OP", self.currentLine));
                        //tokens.add("Subtraction Op, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                    case '\\':
                        tokens.add(new Token("\\", "DIVISION_OP", self.currentLine));
                        //tokens.add("Division Op, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                    case '*':
                        tokens.add(new Token("*", "MULTIPLICATION_OP", self.currentLine));
                        //tokens.add("Multiplication Op, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                    case ',':
                        tokens.add(new Token(",", "COMMA", self.currentLine));
                        //tokens.add("Comma, symbol: " + self.currentChar + ", Line #" + self.currentLine);
                        break;
                }


                self.moveForward(self);
            }
            //Let's now check for stringLiteral's
            token = stringLiteral(self);

            //If we got one we add it, if not move on
            if (token.getLexeme() != "") {
                tokens.add(token);
                //token = "";
            }
            //Stops at the first sign of whitespace, a special symbol like addition '+', or if we reached the end of the file
            while(!Character.isWhitespace(self.currentChar) && self.currentChar != '\0' && !containsSymbol(self.currentChar)) {

                removeComments(self);
                removeWhitespace(self);
                removeComments(self);

                tempToken += self.currentChar;
                self.moveForward(self);
            }
            //Let's check for reserved words. If we find one then add it
            if (isReservedWord(tempToken)) {
                //tokens.add(new Token(tempToken, "RESERVED", self.currentLine));
                tokens.add(addReservedWord(tempToken));
                tempToken = "";
                //tokens.add("Reserved Word, symbol: " + token + ", Line #" + self.currentLine);
            }

            else if (tempToken != "") {
                //Look for integer literal using regular expressions
                if (tempToken.matches("[0-9]+")) {
                    tokens.add(new Token(tempToken, "INTEGER_LITERAL", self.currentLine));
                    //tokens.add("Integer Literal, symbol: " + token + ", Line #" + self.currentLine);
                }
                else if (tempToken.matches("0[0-9A-F]+h")) {
                    tokens.add(new Token(tempToken, "HEX", self.currentLine));
                    //tokens.add("Hexadecimal, symbol: " + token + ", Line #" + self.currentLine);
                }
                else if (tempToken.matches("=")) {
                    tokens.add(new Token(tempToken, "ASSIGNMENT_OP", self.currentLine));
                    //tokens.add("Assignment Op, symbol: " + token + ", Line #" + self.currentLine);
                }
                else if (tempToken.matches("[<>=!]+")) {
                    tokens.add(new Token(tempToken, "RELATIONAL_OP", self.currentLine));
                    //tokens.add("Relational Op, symbol: " + token + ", Line #" + self.currentLine);
                }
                else if (tempToken.matches("[-+]?[0-9]*\\.?[0-9]+")) {
                    tokens.add(new Token(tempToken, "REAL_CONSTANT", self.currentLine));
                    //tokens.add("Real Constant, symbol: " + token + ", Line #" + self.currentLine);
                }
                else {
                    tokens.add(new Token(tempToken, "IDENTIFIER", self.currentLine));
                    //tokens.add("Identifier, symbol: " + token + ", Line #" + self.currentLine);
                }

                tempToken = "";
            }
        }
        tokens.add(new Token("End of File", "EOF", self.currentLine));
        return tokens;
    }

    //Ignore whitespace
    public void removeWhitespace(CustomParser self) {
        while (self.currentChar != '\0' && Character.isWhitespace(self.currentChar)) {
            self.moveForward(self);
        }
    }
    //Check for and return tokens of type string literal
    public Token stringLiteral (CustomParser self) {
        String result = "";

        if (self.currentChar != '\"') {
            return new Token(null, null, -1);
        }
        self.moveForward(self);
        while (self.currentChar != '\0' && self.currentChar != '\"') {
            result += self.currentChar;
            self.moveForward(self);
        }
        self.moveForward(self);
        return new Token(result, "STRING_LITERAL", self.currentLine);
        //return "String Literal, symbol: \"" + result + "\", line #" + self.currentLine;
    }

    //Function to remove the comments from input
    public String removeComments(CustomParser self) {
        String result = "";
        //int start = self.position;

        //If it's not a comment then return
        if (self.currentChar != '/') {
            return "";
        }
        self.moveForward(self);

        //Remove single line comments
        if(self.currentChar == '/') {
            while (self.currentChar != '\0' && self.currentChar != '\n' ) {
                result += self.currentChar;
                self.moveForward(self);
            }
            return result;
        }
        //Remove multiple line comments
        if (self.currentChar == '*') {
            self.moveForward(self);
            while (self.currentChar != '\0' && self.currentChar != '*' && self.nextChar != '/') {
                result += self.currentChar;
                self.moveForward(self);
            }
            self.moveForward(self);
            self.moveForward(self);
            return result;
        }
        return result;
    }

    //Move forward one character
    public void moveForward(CustomParser self) {
        if(self.lastChar == '\n') {
            self.currentLine += 1;
            //System.out.println("NewLine: " + self.currentLine);
        }

        self.position += 1;
        self.lastChar = self.currentChar;

        if (self.position >= (self.text.length())) {
            self.currentChar = '\0';
            self.nextChar = '\0';
        }
        else {
            self.currentChar = self.text.charAt(self.position);
            //System.out.println(self.currentChar);
            if (self.position >= self.text.length() - 1) {
                self.nextChar = '\0';
            }
            else {
                self.nextChar = self.text.charAt(self.position + 1);
            }
        }
    }

    //Check reserved words list
    public boolean isReservedWord(String value) {
        boolean reserved = false;

        ArrayList<String> reservedWordsList = new ArrayList<String>();
        reservedWordsList.add("import");
        reservedWordsList.add("symbol");
        reservedWordsList.add("forward");
        reservedWordsList.add("specifications");
        reservedWordsList.add("references");
        reservedWordsList.add("function");
        reservedWordsList.add("declarations");
        reservedWordsList.add("implementations");
        reservedWordsList.add("main");
        reservedWordsList.add("parameters");
        reservedWordsList.add("constant");
        reservedWordsList.add("begin");
        reservedWordsList.add("endfun");
        reservedWordsList.add("if");
        reservedWordsList.add("then");
        reservedWordsList.add("else");
        reservedWordsList.add("endif");
        reservedWordsList.add("repeat");
        reservedWordsList.add("until");
        reservedWordsList.add("endrepeat");
        reservedWordsList.add("display");
        reservedWordsList.add("input");
        reservedWordsList.add("not");
        reservedWordsList.add("greater");
        reservedWordsList.add("or");
        reservedWordsList.add("equal");
        reservedWordsList.add("set");
        reservedWordsList.add("return");
        reservedWordsList.add("define");
        reservedWordsList.add("of");
        reservedWordsList.add("type");
        reservedWordsList.add("array");
        reservedWordsList.add("struct");
        reservedWordsList.add("integer");
        reservedWordsList.add("short");
        reservedWordsList.add("enum");
        reservedWordsList.add("double");
        reservedWordsList.add("variables");
        reservedWordsList.add("endwhile");
        reservedWordsList.add("while");
        reservedWordsList.add("symbol");
        reservedWordsList.add("global");
        reservedWordsList.add("constants");
        reservedWordsList.add("byte");
        reservedWordsList.add("for");
        reservedWordsList.add("endfor");
        reservedWordsList.add("exit");
        reservedWordsList.add("is");
        reservedWordsList.add("increment");

        if(reservedWordsList.contains(value)) {
            reserved = true;
        }
        else {
            reserved = false;
        }
        return reserved;
    }
    public Token addReservedWord(String value) {
        if(value.equalsIgnoreCase("NOT") ||
                value.equalsIgnoreCase("GREATER") ||
                value.equalsIgnoreCase("OR") ||
                value.equalsIgnoreCase("EQUAL")) {
            return new Token(value, "RELATIONAL_OP", this.currentLine);
        }
        return new Token(value, value.toUpperCase(), this.currentLine);
    }

    //Check if character is a special symbol
    public boolean containsSymbol(char ch) {
        boolean isSymbol = false;

        ArrayList<Character> symbolList = new ArrayList<Character>();
        symbolList.add('+');
        symbolList.add('-');
        symbolList.add('*');
        symbolList.add(',');
        symbolList.add('(');
        symbolList.add(')');
        symbolList.add('[');
        symbolList.add(']');

        if(symbolList.contains(ch)) {
            isSymbol = true;
        }

        return isSymbol;
    }

    //Check if end of file
    public boolean endOfFile(CustomParser self) {
        if(self.currentChar == '\0') {
            return true;
        }
        else {
            return false;
        }
    }
}