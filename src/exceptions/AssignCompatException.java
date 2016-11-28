package exceptions;

import tokenizer.*;

/**
This class defines exceptions to be thrown when a type mismatch occurs in an assignment. 

STATE:

This class has two state variables, representing the source and target tokens associated with an erroneous assignment.

    Token target
    Token source
    
INTERFACE:

This class has a constructor and public method: the constructor passes its parameter on to its super class constructor concatenated with extra data based on the parameters 'target' and 'source', while the public method toString simply returns the message string from the super class.

	public AssignCompatException(String msg, Token target, Token source)
	// Pre: msg, target, source != null
	// Post: target == this.target AND
	//       source == this.source AND
	//       super.getMessage()
	//            == msg + ": target of type " + target.getName()
    //               + " is assignment incompatible with the source of type " 
    //               + source.getName()

	public String toString()
	// Post: return super.toString()

HELPERS:

This class has no helper methods.

CLASS INVARIANT:

This class has no class invariant.

@author J. Mead -- August '09
*/
public class AssignCompatException extends UseException {

    Token.TokenType target;
    Token.TokenType source;
    
    public AssignCompatException(String msg, Token.TokenType target, Token.TokenType source)
    // Pre: msg, target, source != null
    // Post: target == this.target AND
    //       source == this.source AND
    //       super.getMessage()
    //            == msg + ": target of type " + target.getName()
    //               + " is assignment incompatible with the source of type "
    //               + source.getName()
    {
        super( msg + ": target of type " + target +
              " is assignment incompatible with the source of type " +
              source );
        this.source = source;
        this.target = target; 
    }
	
	public String toString() 
	// Post: return super.toString()
	{ 
		return super.toString(); 
	}

}
