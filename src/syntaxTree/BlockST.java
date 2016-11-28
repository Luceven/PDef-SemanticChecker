package syntaxTree;

import java.util.*;

import exceptions.AlreadyDeclaredException;
import exceptions.NotDeclaredException;
import symbolTable.SymbolTable;
import tokenizer.*;
import debug.*;

/**
This class is a subclass of SyntaxTree and represents the 
structure of the grammar rule for the Block non-terminal.  

      Block --> lcbT StmtList rcbT

STATE:

The state in this class includes one data member

   LinkedList<StmtST> list
   
which represents the list of object references for the 
statements in a PDef list.
      
INTERFACE:

The interface of this class includes a constructor. 
    
   BlockST(LinkedList<StmtST> l) 
   
   public void traverse()

This last method performs a depth first traversal of nodes of 
this class
   
HELPER METHODS:
   
There are no helper methods.
   
CLASS INVARIANT:  

    list.size() >= 1
    
The fact that the size of list is >=1 is because StmtList 
is required to have at least one element.
              
@author J. Mead -- September '08
*/

public class BlockST extends StmtST {

    // Class Invariant: list.size() >= 1

    // State

	private LinkedList<StmtST> list;
	private SymbolTable localST;
	
	// Interface
	
	// Constructor
	public BlockST(LinkedList<StmtST> l) 
    {
        super();
		list = l;
		localST = null;
	}
	
	public void traverse() {
	   debug.show(">>> Entering BlockST");
	   
	   for (StmtST st: list)
	       st.traverse();
	   System.out.println("BlockST");
	   
	   debug.show("<<< Leaving BlockST");
	}

	public void checkSemantics(SymbolTable st) throws AlreadyDeclaredException, NotDeclaredException{
		debugSC.show(">>> Entering BlockST.checkSemantics");
        localST = new SymbolTable(st);

		for (StmtST entry: list)
			entry.checkSemantics(localST);

		debugSC.show("<<< Leaving BlockST.checkSemantics");
	}

	public void checkSemantics() throws AlreadyDeclaredException, NotDeclaredException {
		debugSC.show(">>> Entering BlockST.checkSemantics");

        this.checkSemantics(null);

		debugSC.show("<<< Leaving BlockST.checkSemantics");
    }

    public String toString(String pad) {
	    debug.show(">>> Entering BlockST.toString(pad)");
        String str = "List:\n";
        pad += "    ";
        str += pad + "Symbol Table: " + localST.toString() + "\n";

        for (StmtST st: list)
			str += pad + st.toString(pad);
	    debug.show("<<< Leaving BlockST.toString(pad)");
        return str;
    }
}
