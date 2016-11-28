package exceptions;

import tokenizer.*;

/**
This class defines the exception to be thrown when an identifier is encountered that has already been declared.   

STATE:

This class defines no local state.
    
INTERFACE:

This class has a constructor and public method: the constructor passes its parameter on to its super class constructor along with its parameter 't', while the public method toString simply returns the value of the object's message + token.toString().

	public AlreadyDeclaredException(String msg, Token t) 
	// Pre:  msg, t != null
	// Post: this == super(msg,t);
	
	public String toString() 
	// Post: return super.toString() + token

HELPERS:

This class has no helper methods.

CLASS INVARIANT:

This class has no class invariant.

@author J. Mead -- August '09
*/

public class AlreadyDeclaredException extends DeclarationException {

	public AlreadyDeclaredException(String msg, Token t)
	// Pre:  msg, t != null
	// Post: this == super(msg,t);
	{ super(msg, t); }
	
	public String toString() 
	// Post: return super.toString() + token
	{ 
		return super.toString() + token; 
	}

}
