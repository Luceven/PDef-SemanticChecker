package exceptions;

import tokenizer.*;

/**
This abstract class is a subclass of PDefException and is meant to head a hierarchy of exception classes associated with declaration oriented exceptions.

STATE:

The abstract class has a single state variable representing the token seen when the exception was thrown.

    Token token
    
INTERFACE:

This class has a constructor and public method: the constructor passes its parameter directly to its super class constructor; while the public method getToken simply returns the value of the state variable 'token'.

	public DeclarationException(String msg, Token t) 
	// Pre:  msg, t != null
	// Post: super.getMessage == msg AND token == t
	
	public Token getToken()
	// Post: return token

HELPERS:

This class has no helper methods.

CLASS INVARIANT:

This class has no class invariant.

@author J. Mead -- August '09
*/

public class DeclarationException extends PDefException {

	Token  token;

    public DeclarationException(String msg, Token t)
    // Pre:  msg, t != null
    // Post: super.getMessage == msg AND token == t
    {
        super(msg + t.getName());
        token = t;
    }
	
	public Token getToken() 
	// Post: return token
	{ return token; }
	
}
