package symbolTable;

import java.util.LinkedList;
import java.util.ListIterator;

import debug.*;
import tokenizer.*;
import syntaxTree.*;
import exceptions.*;

/**
DESCRIPTION:



STATE:

An object of SymbolTable is meant to represent an hierarchical symbol table, where the hierarchy is an inverted tree (i.e., links from leaves toward the root).

The state of a SymbolTable object consists of a variable 'localST', which represents the symbol table of declarations, the variable 'parentST' is a reference to the parent symbol table (one level up the hierarchy), and the variable 'debug' controls the display of debugging output from a SymbolTable object.
   
	private LinkedList<DeclarationST> localST = new LinkedList<DeclarationST>();
	private SymbolTable parentST;
	private SymbolTableDebug debug;

INTERFACE:

The interface of this class consists of a constructor

	public SymbolTable (SymbolTable up)
	// Pre:  up == null OR up references a SymbolTableObject
	// Post: parentST == up AND debug == new SymbolTableDebug() AND
	//       localST == new LinkedList<DeclarationST>()

	public boolean isAtThisLevel(String n)
	// Pre:  n has a value
	// Post: return true if string n appears in localST

	public void addBinding(DeclarationST entry) 
			throws AlreadyDeclaredException 
	// Pre:  entry != null
	// Post: if an entry already exists in localST for entry.getName()
	//       then an AlreadyDeclaredException exception is thrown
	//       else entry is added to myST

	public DeclarationST findBindingThisLevel(Token n) 
	// Pre:  n != null
	// Post: if there is int r such that localST.get(r).getName() == n 
	//       then return localST.get(r) 
	//       else return null;

	public DeclarationST findBinding(Token n) 
	// Pre:  n != null
	// Post: if findBindingThisLevel(n) != null 
	//       then return findBindingThisLevel(n)
	//       else return parentST.findBinding(n)

	public String toString() {  
    // Post: return String representation for data referenced by localST
    
    public void print() 
    // Post: display on standard out this.toString()
   
HELPER METHODS:
   
There are no helper methods in this class.
   
CLASS INVARIANT:  

There is no class invariant. 
          
@author J. Mead -- August '09
*/


public class SymbolTable {

    // STATE

	private LinkedList<DeclarationST> localST = new LinkedList<DeclarationST>();
	private SymbolTable parentST;

	private SymbolTableDebug debug;

    // CONSTRUCTOR

	public SymbolTable (SymbolTable up) 
	// Pre:  up == null OR up references a SymbolTableObject
	// Post: parentST == up AND debug == new SymbolTableDebug() AND
	//       localST == new LinkedList<DeclarationST>()
	{
		parentST = up;
		debug = new SymbolTableDebug();
	}
	
	// INTERFACE
	
	public boolean isAtThisLevel(String n) 
	// Pre:  n has a value
	// Post: return true if string n appears in localST
	{
		debug.show("--->>> Entering SymbolTable.isAtThisLevel");

		ListIterator<DeclarationST> itr = localST.listIterator();
		boolean found = false;
		while (!found && itr.hasNext()) {
			DeclarationST ste = itr.next();
			if ( n.equals(ste.getName().getName()))
				 found = true;
		}
		debug.show( "<<<--- Leaving SymbolTable.isAtThisLevel" );
		return found;
	}

	public void addBinding(DeclarationST entry) 
			throws AlreadyDeclaredException 
	// Pre:  entry != null
	// Post: if an entry already exists in localST for entry.getName()
	//       then an AlreadyDeclaredException exception is thrown
	//       else entry is added to myST
    {
		debug.show("--->>> Entering Entering addEntry");

		if ( isAtThisLevel( (entry.getName()).getName() ) )
			throw new AlreadyDeclaredException
				("Parameter name already defined!", entry.getName());

		// Assert: n->getName() not yet defined at this level
		localST.addLast(entry);

		debug.show( "<<<--- Leaving Leaving addEntry");
	}

	public DeclarationST findBindingThisLevel(Token n) 
	// Pre:  n != null
	// Post: if there is int r such that localST.get(r).getName() == n 
	//       then return localST.get(r) 
	//       else return null;
	{
		debug.show("--->>> Entering SymbolTable.findBindingThisLevel");

        ListIterator<DeclarationST>itr = localST.listIterator();
        while (itr.hasNext()) {
            DeclarationST ste = itr.next();
            if (ste.getName().getName().equals(n.getName())) {
                return ste;
            }
        }
		
		debug.show( "<<<--- Leaving SymbolTable.findBindingThisLevel" );
		return null;
	}
	
	public DeclarationST findBinding(Token n) 
	// Pre:  n != null
	// Post: if findBindingThisLevel(n) != null 
	//       then return findBindingThisLevel(n)
	//       else return parentST.findBinding(n)
	{
		debug.show("--->>> Entering SymbolTable.findBinding");

		if (findBindingThisLevel(n) != null) {
            return findBindingThisLevel(n);
        }

		debug.show( "<<<--- Leaving SymbolTable.findBinding" );
		return parentST.findBinding(n);
	}

	public String toString() 
    // Post: return String representation for data referenced by localST
	{
	    String line = "";
        ListIterator<DeclarationST>itr = localST.listIterator();
        while(itr.hasNext())
            line += itr.next().briefToString() + " ";
//        for (DeclarationST st: localST)
//            //line += "( " + st.getName() + ", " + st.getType() + " )";
//            line += st.briefToString();
        return line;
	}
	
	public void print() 
    // Post: display on standard out this.toString()
	{
        System.out.println( this );
    }

}
