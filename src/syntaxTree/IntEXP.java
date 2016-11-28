package syntaxTree;

import symbolTable.SymbolTable;
import tokenizer.*;
import debug.*;

/**
This class is a subclass of ExpressionST and represents the structure of an expression that is an integer literal

STATE:

The state in this class includes one data member

   Token value
      
INTERFACE:

The interface of this class includes a constructor. 
    
   IntEXP(Token v) 
   // Pre: v.getType() == intT

   public void traverse()

This last method performs a depth first traversal of nodes of 
this class
   
HELPER METHODS:
   
There are no helper methods.
   
CLASS INVARIANT:  

     value.getType() == intT
          
@author J. Mead -- September '08
*/

public class IntEXP extends ExpressionST {

    // Class Invariant: value.getType() == intT

    // State

	private Token value;
	
	// Interface

	// Constructor
    public IntEXP(Token v) 
    // Pre: v.getType() == intT
    {
        super();
		value = v;
	}

	public void traverse() {
	   System.out.println("IntEXP"+value);
	}

	public void checkSemantics(SymbolTable st) {
		debugSC.show(">>> Entering AssignmentST.checkSemantics");

		debugSC.show("<<< Leaving AssignmentST.checkSemantics");
	}

    public String toString() {
	   debug.show(">>> Entering IntEXP.toString()");
	   debug.show("<<< Leaving IntEXP.toString()");
       return value.getName();
    }
}

