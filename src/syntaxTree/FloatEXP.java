package syntaxTree;

import symbolTable.SymbolTable;
import tokenizer.*;
import debug.*;

/**
This class is a subclass of ExpressionST and represents the structure of an expression that is an floating point literal

STATE:

The state in this class includes one data member

   Token value
      
INTERFACE:

The interface of this class includes a constructor. 
    
   FloatEXP(Token v) 
   // Pre: v.getType() == floatT

   public void traverse()

This last method performs a depth first traversal of nodes of 
this class
   
HELPER METHODS:
   
There are no helper methods.
   
CLASS INVARIANT:  

     value.getType() == floatT
          
@author J. Mead -- September '08
*/

public class FloatEXP extends ExpressionST {

    // Class Invariant: value.getType() == floatT

    // State

	private Token value;
	
	// Interface

	// Constructor
    public FloatEXP(Token v) 
    // Pre: v.getType() == floatT
    {
        super();
		value = v;
	}
	
	public void traverse() {
	   System.out.println("FloatEXP"+value);
	}

	public void checkSemantics(SymbolTable st) {
		debugSC.show(">>> Entering AssignmentST.checkSemantics");

		debugSC.show("<<< Leaving AssignmentST.checkSemantics");
	}

    public String toString() {
	   debug.show(">>> Entering FloatEXP.toString()");
	   debug.show("<<< Leaving FloatEXP.toString()");
       return value.getName();
    }

}
