package tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Character;

import debug.*;

/**

DESCRIPTION:

This class implements a tokenizer which extracts token values from the input stream, where the tokens are defined by the following regular expression:

     Regular Expression    Token.TokenType value    Terminating character
            ,                    COMMA_T               any character
            =                    ASSIGN_T                     "
            {                    LCB_T                        "
            }                    RCB_T                        "
            (                    RP_T                         "
            )                    LP_T                         "
            +                    ADD_T                        "
            -                    SUB_T                        "
            *                    MUL_T                        "
            /                    DIV_T                        "
            %                    MOD_T                        "
        int | float              TYPE_T          `,={}()+-*%/' or whitespace
      0 | [1-9][0-9]*            INT_T                        "
 (0 | [1-9][0-9]*).[0-9]+        FLOAT_T                      "
        [a-zA-Z]+                IDENT_T                      "


STATE:

The state of an object includes the variable 'inFile', which holds references the input file in the form of a BufferedReader object, the boolean flag 'echo' indicating whether the input characters are echoed to standard out, and a variable 'debug' of the class TokenizerDebug which controls display of debug information.  In addition, the variables 'line' and 'position' track the position in the input file of the current character read; also, the variable 'sawCR' is set to true if a carriage return character was just seen, and the variable 'didPutBack' is set to true if the method putBackChar was just called.

    private BufferedReader inFile;    // the input stream
    private boolean        echo = false;  // if true the input is echoed
    private TokenizerDebug debug;     // object which controls the display
                                      // of debugging information from calls
                                      // to debug.show
	private int            line;      // token appears on this input line
	private int            position;  // token starts at this position on line
	private boolean        sawCR = false; // true if carriage return was just seen
	private boolean        didPutBack = false; // true if put-back was just done
                                      
There are two definitions present for convenience: the value of the constant 'eofChar' is returned by 'getChar' when the end of file is detected, and the enumerated type StateName defines names for the states in finite state machine implemented by GetNextTokens.

    private static final char eofChar = (char)0;
	private enum StateName { START_S, ID_S, INT_S, FLOAT_S, ZERO_S, 
		                     PERIOD_S, ERROR_S, DONE_S };

   
INTERFACE:

The interface includes a constructor

The interface of this class includes the constructor 
    
    public Tokenizer (BufferedReader in, boolean echo)
    // Post: inFile == in AND this.echo == echo

and the following method, which identifies tokens on the input stream, returning the next one each time it is called.

    public Token getNextToken()
    // Pre:  inFile has a value
    // Post: inFile has initial blanks removed as well as the characters
    //       of the next token on inFile.  The tokens are determined by the
    //       regular expression in DESCRIPTION above

   
HELPER METHODS:
   
There are x helper methods:

	private char getChar()
	// Pre:  ch is the character at the head of inFile
	// Post: inFile is original inFile with ch removed AND
	//       return ch -- Except
	//       if inFile.eof is true return tab character
	//       if ch is tab or eol return blank character

    private void putBackChar(char ch)
    // Pre:  inFile has a value
    // Post: inFile is the original inFile with ch added as its first character

    private Token.TokenType char2Token (char ch)
    // Pre:  ch has a character value
    // Post: return TokenType value corresponding to ch

    private Token.TokenType string2Token(String str)
    // Pre:  str has a value
    // Post: return TokenType value corresponding to str:

    // Pre:  p >= 0
    // Post: if didPutBack == true then return p else return p+1

CLASS INVARIANT:  

The class invariant indicates the input stream pointer is at the next character following the last token identified -- that means the first character in the file if no token has as yet been read.

    Class Invariant: 
       When GetNextToken is called the inFile pointer is at either 
       at the first character of the file (if no token has been read)
       or at the first character following the last token read from
       inFile.
          
@author J. Mead -- August '09
*/
/**
This class implements a tokenizer which extracts token values from the input stream, where the tokens are defined by the following regular expression:



getNextToken implements the finite state machine for the regular expression above -- it reads the sequence of characters of the next token on the input stream (inFile) and returns the corresponding instance of the Token class.  

@author J. Mead -- July '08
*/


public class Tokenizer{

    // STATE

	private static final char eofChar = (char)0;

	private enum StateName { START_S, ID_S, INT_S, FLOAT_S, ZERO_S, 
		                     PERIOD_S, ERROR_S, DONE_S };

	private BufferedReader inFile;
	
	private int            line;
	private int            position;
	private boolean        sawCR = false;
	private boolean        didPutBack = false;
	private boolean        echo = false;
	private TokenizerDebug debug;
	
	// CONSTRUCTOR

	public Tokenizer (BufferedReader in, boolean echo)
	// Post: inFile == in, this.echo == echo, debug.getFlag() == debug
	{
		this.inFile = in;
		this.echo = echo;
        this.debug = new TokenizerDebug();		
	}
	
	// INTERFACE

    public Token getNextToken()
    // Pre:  inFile has a value
    // Post: inFile has initial blanks removed as well as the characters
    //       of the next token on inFile.  The tokens are determined by the
    //       regular expression in DESCRIPTION above
    {
        debug.show("Entering getNextToken");
        StateName state = StateName.START_S;
        
        Token.TokenType type = Token.TokenType.ERROR_T;
        String    name  = "";
        int startingLin = line;
        int startingPos = nextPosition(position); 
            // because haven't read the first character of the next token yet

        while (state != StateName.DONE_S) {
            char ch = getChar();
            switch (state) {
            case START_S:
                debug.show("\tEntering state -- START_S: ", ch);
                if (ch == ' ') {
                state = StateName.START_S;
                startingLin = line;
                startingPos = nextPosition(position);
                }
                else if (ch == eofChar) {
                type  = Token.TokenType.EOT_T;
                state = StateName.DONE_S;
                }
                else if (ch == '0') {
                name += ch;
                state = StateName.ZERO_S;
                }
                else if (Character.isDigit(ch)) {
                name += ch;
                state = StateName.INT_S;
                }
                else if (Character.isLetter(ch)) {
                name += ch;
                state = StateName.ID_S;
                }
                else { // Assert: special, possibly invalid character
                name += ch;
                type  = char2Token(ch);
                state = StateName.DONE_S;
                }
                debug.show("\tLeaving state -- START_S: ", ch);                       
                break;
            case ID_S:
                debug.show("\tEntering state -- ID_S: ", ch);                       
                if (Character.isLetter(ch)) {
                name += ch;
                state = StateName.ID_S;
                }
                else if (Character.isDigit(ch)) {
                name += ch;
                state = StateName.ERROR_S;
                }
                else { // its a complete string
                putBackChar(ch);
                type  = string2Token(name);
                state = StateName.DONE_S;
                }
                debug.show("\tLeaving state -- ID_S: ", ch);                       
                break;
            case INT_S:
                debug.show("\tEntering state -- INT_S: ", ch);                       
                if (Character.isDigit(ch)) {
                name += ch;
                state = StateName.INT_S;
                }
                else if (ch == '.') {
                name += ch;
                state = StateName.PERIOD_S;
                }
                else if (Character.isLetter(ch)) {
                name += ch;
                state = StateName.ERROR_S;
                }
                else { // its a complete string
                putBackChar(ch);
                type  = Token.TokenType.INT_T;
                state = StateName.DONE_S;
                }
                debug.show("\tLeaving state -- INT_S: ", ch);                       
                break;
            case ZERO_S:
                debug.show("\tEntering state -- INT_S: ", ch);                       
                if (Character.isDigit(ch) || Character.isLetter(ch)) {
                name += ch;
                state = StateName.ERROR_S;
                }
                else if (ch == '.') {
                name += ch;
                state = StateName.PERIOD_S;
                }
                else {
                putBackChar(ch);
                type  = Token.TokenType.INT_T;
                state = StateName.DONE_S;
                }		
                debug.show("\tLeaving state -- INT_S: ", ch);                       
                break;
            case PERIOD_S:
                debug.show("\tEntering state -- PERIOD_S: ", ch);
                if (Character.isDigit(ch)) {
                    name += ch;
                    state = StateName.FLOAT_S;
                }
                else {
                    name += ch;
                    state = StateName.ERROR_S;
                }
                debug.show("\tLeaving state -- PERIOD_S: ", ch);
                break;
            case FLOAT_S:
                debug.show("\tEntering state -- FLOAT_S: ", ch);
                if (Character.isDigit(ch)) {
                    name += ch;
                    state = StateName.FLOAT_S;
                }
                else if (Character.isLetter(ch)) {
                    name += ch;
                    state = StateName.ERROR_S;
                }
                else { // its a complete string
                    putBackChar(ch);
                    type  = Token.TokenType.FLOAT_T;
                    state = StateName.DONE_S;
                }
                debug.show("\tLeaving state -- FLOAT_S: ", ch);
                break;
            case ERROR_S:
                debug.show("\tEntering state -- ERROR_S: ", ch);                       
                if (Character.isLetter(ch) || Character.isDigit(ch)) {
                    name += ch;
                    state = StateName.ERROR_S;
                }
                else { // its a complete string
                    putBackChar(ch);
                    type  = Token.TokenType.ERROR_T;
                    state = StateName.DONE_S;
                }
                debug.show("\tLeaving state -- ERROR_S: ", ch);                       
                break;
            case DONE_S: // Should never get here!  For completeness.
                break;
            }

        }

        Token token = new Token(type, name, startingLin, startingPos);

        debug.show("Leaving getNextToken");       
        
        return token;

    }
    
    // Helpers -- private methods
    
    private char getChar()
    // Pre:  ch is the character at the head of inFile
    // Post: inFile is original inFile with ch removed AND
    //       return ch -- Except
    //       if inFile.eof is true return tab character
    //       if ch is tab or eol return blank character
    {
        char ch;

        int v = 0;
        
        try { inFile.mark(1); v = inFile.read(); }
        catch (IOException e) { 
            System.out.println("problem!"); 
            System.exit(0); 
        }

        if (v == -1)
            ch = eofChar;
        else {
            ch = (char)v;
            if (echo && !didPutBack) System.out.print(ch);

            // if (echo && !didPutBack) System.out.print(ch);

            if (didPutBack)
                    didPutBack = false;
            else position++;

            if (ch == '\n') {
                line++;
                position = 0;
            }

            sawCR = (ch == '\n');
            
            if (ch == '\n' || ch == '\t') ch = ' ';
        }
        return ch;
    }


    private void putBackChar(char ch)
    // Pre:  inFile has a value
    // Post: inFile is the original inFile with ch added as its first character
    {
       debug.show("Entering putBackChar");   
              
       try {
            if (ch != eofChar) {
                inFile.reset();
                didPutBack = true;
                if (sawCR) line--;
            }    		
       }
       catch(IOException e) {
            System.exit(0);
       }

       debug.show("Leaving putBackChar");                              
    }



    private Token.TokenType char2Token (char ch)
    // Pre:  ch has a character value
    // Post: return TokenType value corresponding to ch
    {
            switch (ch) {
            case '=' : return Token.TokenType.ASSIGN_T;
            case ',' : return Token.TokenType.COMMA_T; 		
            case '{' : return Token.TokenType.LCB_T;
            case '}' : return Token.TokenType.RCB_T;
            case '(' : return Token.TokenType.LP_T;
            case ')' : return Token.TokenType.RP_T;
            case '+' : return Token.TokenType.ADD_T;
            case '-' : return Token.TokenType.SUB_T;
            case '*' : return Token.TokenType.MUL_T;
            case '%' : return Token.TokenType.MOD_T;
            case '/' : return Token.TokenType.DIV_T;
            default  : return Token.TokenType.ERROR_T;
            }

    }

    private Token.TokenType string2Token(String str)
    // Pre:  str has a value
    // Post: return TokenType value corresponding to str:
    {
            if (str.equals("int") || 
                    str.equals("char") || 
                    str.equals("float"))
                return Token.TokenType.TYPE_T;
            else 
                return Token.TokenType.IDENT_T;
    
    }

    private int nextPosition(int p)
    // Pre:  p >= 0
    // Post: if didPutBack == true then return p else return p+1
    {
            if (didPutBack) return p;
            else            return p+1;
    }

}
