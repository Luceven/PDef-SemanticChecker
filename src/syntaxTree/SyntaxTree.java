package syntaxTree;

import debug.*;
import exceptions.AlreadyDeclaredException;
import exceptions.NotDeclaredException;
import symbolTable.SymbolTable;

/**
This abstract class is the top of the hierarchy representing the structures that make up a syntax tree.  

The idea is that the for each non-terminal in the PDef grammar there will be a subclass of this class representing the structure of the non-terminal.

STATE:

The state in this class includes a single data member

   SyntaxTreeDebug debug
   
which controls debugging printing in the syntax tree classes.  This data member is declared in this class so that it will be inherited into each subclass.
   
INTERFACE:

While there is no constructor given, Java automatically substitutes an empty constructor -- i.e.,

   SyntaxTree () { }
   
The interface of this class includes a display method:
    
   void printST()
   
The method, in calling 'this.toString', uses late binding to display the syntax tree starting at the node referenced by 'this'.

   public abstract void traverse()

This last method is abstract and in its subclasses performs a depth first traversal of nodes of the subclass
   
HELPER METHODS:
   
There are no helper methods.
   
CLASS INVARIANT:  

There is no class invariant
          
@author J. Mead -- September '08
*/

public abstract class SyntaxTree {
	
    protected SyntaxTreeDebug debug = null;

    protected SemanticCheckDebug debugSC = null;
    
    protected SyntaxTree()

    {
        debug = new SyntaxTreeDebug();
        debugSC = new SemanticCheckDebug();
    }

    public abstract void checkSemantics(SymbolTable st) throws AlreadyDeclaredException, NotDeclaredException;

    public void printST() { 
        System.out.println ( this.toString() );
    }
    
    public abstract void traverse();

}
