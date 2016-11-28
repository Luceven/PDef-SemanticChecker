package exceptions;

import tokenizer.*;

/**
This class defines exceptions to be thrown when parse errors are encountered.  This exception expects all necessary information to be transmitted by the two parameters of the constructor.  A new message is constructed and passed to the PDefException constructor via the `super' call. 

STATE:

This class has three state variables, referencing and left and right argument types and the associated arithmetic operation.  These were the source of the exception being represented.

    Token rightType, leftType, op;
    
INTERFACE:

This class has a constructor and public method: the constructor passes its parameter on to its super class constructor concatenated with extra data based on the parameter 't', while the public method getToken simply returns the value of the state variable 'token'.

	public OperationException(String msg, Token leftType, 
	                         Token rightType, Token op)	
    // Pre:  msg, leftType, rightType, op != null
    // Post: super.getMessage() 
            == msg + ": the types " + leftType.getName() + 
	           " and " + rightType.getName() + 
	           " are incompatible with operation `" + op.getName() + "'."
	           
	public String toString()
	// Post: return super.toString()

HELPERS:

This class has no helper methods.

CLASS INVARIANT:

This class has no class invariant.

@author J. Mead -- August '09
*/

public class OperationException extends UseException {

    Token rightType, leftType, op;

	public OperationException(String msg, Token leftType, 
	                         Token rightType, Token op)	
	{ 
	     super(msg + ": the types " + leftType.getName() + " and "
	         + rightType.getName() + " are incompatible with operation `" 
	         + op.getName() + "'."); 
	    this.rightType = rightType;
	    this.leftType = leftType;
	    this.op = op;
	}
	
	public String toString()
	// Post: return super.toString()
	{ 
		return super.toString(); 
	}

}
