package tokenizer;

/**

DESCRIPTION:

This class defines the structure of a Token object for production by the tokenizer and use by the parser.

STATE:

The state of an object includes a variable 'type', holding this token's TokenType value, and a variable 'name', which holds the String value of the token.  In addition there are two secondary variables: 'line', which is the number of the input line the token appears on, and 'position', which is the offset of the character on the line. 

	private TokenType type;  // type of this particular token
	private String    name;  // string of characters associated with
						     // this particular token
	private int position;
	private int line;
   
INTERFACE:

There are two components to the interface.  The following enumerated type defines the legal type names of tokens.  

	public enum TokenType { IDENT_T, TYPE_T, ASSIGN_T, 
	                        RCB_T, LCB_T, RP_T, LP_T, COMMA_T, 
	                        ADD_T, SUB_T, MUL_T, DIV_T, MOD_T, 
	                        INT_T, FLOAT_T, ERROR_T, EOT_T };

The interface of this class includes the constructor 
    
	public TokenType getType()
		// Pre:  type has a value
		// Post: return type
		
and the following methods.
		
	public String getName() 
		// Pre:  name has a value
		// Post: return name

	public String toString() 
		// Pre:  type and name have values
		// Post: return string containing character form of
		//       "<line, position> type(name)"
   
HELPER METHODS:
   
There are no helper methods.

CLASS INVARIANT:  

The class has no invariant.
          
@author J. Mead -- August '09
*/


public class Token {

    // State 
    
	public enum TokenType { IDENT_T, TYPE_T, ASSIGN_T, 
	                        RCB_T, LCB_T, RP_T, LP_T, COMMA_T, 
	                        ADD_T, SUB_T, MUL_T, DIV_T, MOD_T, 
	                        INT_T, FLOAT_T, ERROR_T, EOT_T };

	private TokenType type;  // type of this particular token
	private String    name;  // string of characters associated with
						     // this particular token
	private int position;
	private int line;
	
	// Constructor

    public Token(TokenType t, String s, int l, int pos) {
       type = t;
       name = s;
       line = l;
       position = pos;
    }
    
    // Interface -- public methods

	public TokenType getType() { return type; }
		// Pre:  type has a value
		// Post: return type
	public TokenType getTokenType() 
		// Pre:  type has value TYPE_T
		// Post: if name == "int" return TokenType.INT_T
                //       if name == "float" return TokenType.FLOAT_T
        {
                if (name.equals("int"))   return TokenType.INT_T;
                if (name.equals("float")) return TokenType.FLOAT_T;
                return TokenType.ERROR_T;
        }
	public String getName() { return name; }
		// Pre:  name has a value
		// Post: return name

	public String toString() 
		// Pre:  type and name have values
		// Post: return string containing character form of
		//       "<line, position> type(name)"
	{
		return  
		       type.toString() + "( " + name + " )" +
		       "<" + line + "," + position + ">";
	}

}
