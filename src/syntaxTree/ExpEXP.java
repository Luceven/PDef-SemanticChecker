package syntaxTree;

import symbolTable.SymbolTable;
import tokenizer.*;
import debug.*;

/**
This class is a subclass of ExpressionST and represents the structure of an expression that is the combination of two expressions via an operator:  exp op exp

STATE:

The state in this class includes three data members

   ExpressionST leftExp
   ExpressionST rightExp
   Token        op
      
INTERFACE:

The interface of this class includes a constructor. 
    
   ExpEXP(ExpressionST lexp, Token op, ExpressionST rexp) 
   // Pre: op.getType() == addT | subT | mulT | divT | modT

   public void traverse()

This last method performs a depth first traversal of nodes of 
this class
   
HELPER METHODS:
   
There are no helper methods.
   
CLASS INVARIANT:  

     op.getType() == addT | subT | mulT | divT | modT
          
@author J. Mead -- September '08
*/

public class ExpEXP extends ExpressionST {

    // Class Invariant: 
    //       op.getType() == addT | subT | mulT | divT | modT

    // State

	private ExpressionST leftExp;
	private ExpressionST rightExp;
	private Token        op;
	
	// Interface

	// Constructor
	public ExpEXP(ExpressionST lexp, Token op, ExpressionST rexp) 
    // Pre: op.getType() == addT | subT | mulT | divT | modT
    {
        super();
		leftExp  = lexp;
		rightExp = rexp;
		this.op  = op;
	}

	public void traverse() {
	   debug.show(">>> Entering ExpEXP");
	   
	   leftExp.traverse();
	   rightExp.traverse();
	   System.out.println("ExpEXP");
	   
	   debug.show("<<< Leaving ExpEXP");
	}

	public void checkSemantics(SymbolTable st) {
		debugSC.show(">>> Entering AssignmentST.checkSemantics");

		debugSC.show("<<< Leaving AssignmentST.checkSemantics");
	}

    public String toString() {
	  debug.show(">>> Entering ExpEXP.toString()");
	  String str = "( " + leftExp.toString() + " " + op.getName() + " ";
	  str += rightExp.toString() + " )";
	  debug.show("<<< Leaving ExpEXP .toString()");
      return  str;
    }
}

