package syntaxTree;

import exceptions.AlreadyDeclaredException;
import exceptions.NotDeclaredException;
import symbolTable.SymbolTable;
import tokenizer.*;
import debug.*;

/**
This class is a subclass of StmtST and represents the structure 
of the grammar rule for the Assignment non-terminal.  

      Assignment --> identT assignT Exp

STATE:

The state in this class includes two data member

   Token        target
   ExpressionST source
      
INTERFACE:

The interface of this class includes a constructor. 
    
   AssignmentST(Token t, ExpressionST e) 
   // Pre: t.getType() == identT 
   
   public void traverse()

This last method performs a depth first traversal of nodes of 
this class
   
HELPER METHODS:
   
There are no helper methods.
   
CLASS INVARIANT:  

     type.getType() == typeT
          
@author J. Mead -- September '08
*/

public class AssignmentST extends StmtST {

    // Class Invariant: target.getType() == identT AND

    // State

	private Token        target;
	private Token        source; // remove for PDef
//	private ExpressionST source;
	
	// Interface

	// Constructor
//	public AssignmentST(Token t, ExpressionST s) 
	public AssignmentST(Token t, Token s) // remove for PDef
    // Pre:  t.getType() == typeT AND s.getType() == typeT
    {
    	    super();
        target = t;
        source = s;
	}
	
	public void traverse() {
	   debug.show(">>> Entering AssignmentST.traverse");
	   
//	   source.traverse(); // remove for PDef
	   System.out.println("AssignmentST");
	   
	   debug.show("<<< Leaving AssignmentST .traverse");
	}

	public void checkSemantics(SymbolTable st) throws AlreadyDeclaredException, NotDeclaredException {
		debugSC.show(">>> Entering AssignmentST.checkSemantics");

		DeclarationST entry = st.findBinding(target);
        if ( entry == null )
            throw new NotDeclaredException("Undeclared identifier: ", target);

		debugSC.show("<<< Leaving AssignmentST.checkSemantics");
	}

    public String toString(String pad) {
	   debug.show(">>> Entering AssignmentST.toString(pad)");
       String str = "Assignment: target = " + target.getName();
//     str += ", source = " + source.toString() + "\n";
       str += ", source = " + source.getName() + "\n";   // remove for PDef
	   debug.show(">>> Entering AssignmentST.toString(pad)");
       return str;
    }
}
