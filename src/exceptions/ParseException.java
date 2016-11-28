package exceptions;

import tokenizer.*;

/**
This class defines exceptions to be thrown when parse errors are encountered.  This exception expects all necessary information to be transmitted by the two parameters of the constructor.  A new message is constructed and passed to the PDefException constructor via the `super' call. 

STATE:

This class has one state variable -- `token', which is the token seen when the parse error was detected.

    Token token;
    
INTERFACE:

This class has a constructor and public method: the constructor passes its parameter on to its super class constructor concatenated with extra data based on the parameter 't', while the public method getToken simply returns the value of the state variable 'token'.

    public ParseException(String msg, Token t) { 
    // Pre:  msg has a value and t != null
    // Post: super.getMessage() 
    //                  == msg + ", but saw the token " + t

    public Token getToken () 
    // Post: return value of token

HELPERS:

This class has no helper methods.

CLASS INVARIANT:

This class has no class invariant.

@author J. Mead -- August '09
*/


public class ParseException extends PDefException {

    // State -- that inherited from PDefException
    
    Token token;

    // Constructor
    
    public ParseException(String msg, Token t) 
    // Pre:  msg has a value and t != null
    // Post: super.getMessage() 
    //                  == msg + ", but saw the token " + t
    { 
        super(msg + ", but saw the token " + t); 
        token = t;
    }
    
    // Interface
    
    public Token getToken () 
    // Post: return value of token
    { return token; }

}
