package syntaxTree;

import symbolTable.SymbolTable;
import tokenizer.*;
import debug.*;

/**
This class is a subclass of ExpressionST and represents the structure of an expression that is an identifier (a variable use)

STATE:

The state in this class includes one data member

   Token name
      
INTERFACE:

The interface of this class includes a constructor. 
    
   IdentEXP(Token n) 
   // Pre: n.getType() == identT

   public void traverse()

This last method performs a depth first traversal of nodes of 
this class
   
HELPER METHODS:
   
There are no helper methods.
   
CLASS INVARIANT:  

     name.getType() == identT
          
@author J. Mead -- September '08
*/

public class IdentEXP extends ExpressionST {

    // Class Invariant: name.getType() == identT

    // State

	private Token name;
	
	// Interface

	// Constructor
    public IdentEXP(Token n) 
    // Pre: n.getType() == identT
    {
        super();
		name = n;
	}

	public void traverse() {
	   System.out.println("IdentEXP" + name);
	}

	public void checkSemantics(SymbolTable st) {
		debugSC.show(">>> Entering AssignmentST.checkSemantics");

		debugSC.show("<<< Leaving AssignmentST.checkSemantics");
	}

    public String toString() {
	   debug.show(">>> Entering IdentEXP.toString()");
	   debug.show("<<< Leaving IdentEXP.toString()");
       return name.getName();
    }

}
