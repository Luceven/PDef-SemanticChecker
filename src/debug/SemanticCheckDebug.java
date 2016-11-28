package debug;

/**
 * Created by Kate on 11/19/2016.
 */
public class SemanticCheckDebug extends Debug {
    //State
    private int regPsn;

    //Constructor

    public SemanticCheckDebug()
    // Post: regPsn == registration value returned by Debug.registerObject AND
    //       regPsn >= 0
    {
        regPsn = registerObject('q');
    }

    public void show(String msg) { super.show(regPsn, msg); }
}
