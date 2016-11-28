package syntaxTree;

import exceptions.AlreadyDeclaredException;
import exceptions.NotDeclaredException;
import symbolTable.SymbolTable;
import tokenizer.*;
import debug.*;

/**
This class is a subclass of StmtST and represents the structure of the grammar rule for the Declaration non-terminal.  

      Declaration --> typeT identT

STATE:

The state in this class includes two data member

   Token type
   Token name
      
INTERFACE:

The interface of this class includes a constructor. 
    
   DeclarationST(Token t, Token n) 
   // Pre: t.getType() == typeT AND n.getType() == identT
   
   public void traverse()

This last method performs a depth first traversal of nodes of 
this class
   
HELPER METHODS:
   
There are no helper methods.
   
CLASS INVARIANT:  

     type.getType() == typeT AND name.getType() == identT
          
@author J. Mead -- September '08
*/

public class DeclarationST extends StmtST {

    // Class Invariant: type.getType() == typeT AND
    //                  name.getType() == identT

    // State

	private Token type;
	private Token name;
	
	// Interface

	// Constructor
	public DeclarationST(Token t, Token n) 
    // Pre:  t.getType() == typeT AND n.getType() == identT
    {
                super();
		type = t;
		name = n;
	}
	
    public Token getName() { return name; }
    
    public Token getType() { return type; }
	
	public void traverse() {
	   debug.show(">>> Entering DeclarationST.traverse");
	   
	   System.out.println("DeclarationST");
	   
	   debug.show("<<< Leaving DeclarationST.traverse");
	}

	public void checkSemantics(SymbolTable st) throws AlreadyDeclaredException, NotDeclaredException{
		debugSC.show(">>> Entering DeclarationST.checkSemantics");

		st.addBinding(this);

		debugSC.show("<<< Leaving DeclarationST.checkSemantics");
	}

	public String briefToString() {
        debugSC.show(">>> Entering DeclarationST.briefToString");
        String str = "(" + type.getName();
        str += ", " + name.getName() + ")";
        debugSC.show("<<< Leaving DeclarationST.briefToString");
        return str;
    }

    public String toString(String pad) {
	   debug.show(">>> Entering DeclarationST.toString(pad)");
        String str = "Declaration: type = " + type.getName();
        str += ", name = " + name.getName() + "\n";
	   debug.show("<<< Leaving DeclarationST .toString(pad)");
        return str;
    }
}
