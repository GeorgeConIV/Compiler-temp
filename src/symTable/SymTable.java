/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package symTable;

import javax.swing.plaf.synth.SynthButtonUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymTable{

    HashMap<String, Symbol> localSymTable = new HashMap<>();

    public static List<SymTable> symbolTables = new ArrayList<SymTable>();

    SymTable owner;
    boolean hasOwner;
    String labelName;
    static Integer offsetCount = 1;
    static SymTable previousTable;
    Integer localOffset;
    private Symbol lastaccessedsym;
    // Constructor
    public SymTable()
    {
        labelName = "main";
        hasOwner = false;
        localOffset = offsetCount;
    }

    public SymTable(SymTable owner, String labelName)
    {
        this.owner = owner;
        this.labelName = labelName;
        hasOwner = true;
        localOffset = offsetCount;
    }
    public String getLabel() {
        return labelName;
    }
    public SymTable getOwner()
    {
        return owner;
    }
    // Instance Methods
    public Symbol insert(String id, String type) throws SymbolAlreadyDeclared
    {

        if(!localSymTable.containsKey(id))
        {
            lastaccessedsym = new Symbol(id, offsetCount, type);
            localSymTable.put(lastaccessedsym.getName(), lastaccessedsym);
            offsetCount++;
            return lastaccessedsym;
        }
        else
            {
            String error = "SYMBOL ALREADY DECLARED";
            System.out.println(error);
            throw new SymbolAlreadyDeclared(error);
        }
    }

    public Symbol insert(String id) throws SymbolAlreadyDeclared
    {
        if(!localSymTable.containsKey(id))
        {
            lastaccessedsym = new Symbol(id, offsetCount);
            localSymTable.put(lastaccessedsym.getName(), lastaccessedsym);
            offsetCount++;
            return lastaccessedsym;
        }
        else
        {
            String error = "SYMBOL ALREADY DECLARED";
            System.out.println(error);
            throw new SymbolAlreadyDeclared(error);
        }
    }

    public SymTable getSymTable(String lbl) throws SymbolNotFound
    {
        previousTable = this;
        for(SymTable symt : symbolTables)
        {
            if(symt.labelName.equals(lbl))
            {
                return symt;
            }
        }
        throw new SymbolNotFound("Table not found!");
    }

    public Symbol getFuncParam(int i) throws SymbolNotFound
    {
        if(localSymTable.size() > 0)
            for(Map.Entry<String, Symbol> sym : localSymTable.entrySet())
            {
                if ((sym.getValue().getOffset() - localOffset) == i)
                    return sym.getValue();
            }
        throw new SymbolNotFound("SYMBOL NOT FOUND");
    }

    public Symbol find(String id) throws SymbolNotFound
    {
        if(localSymTable.containsKey(id))
            lastaccessedsym = localSymTable.get(id);
        else if(hasOwner)
            lastaccessedsym = owner.find(id);
        else {
            System.out.println("Symbol with ID: " + id + " does not exist in symbol table");
            lastaccessedsym = new Symbol();
        }
        return lastaccessedsym;
    }

    public Symbol getLastAccessedSym()
    {
        return lastaccessedsym;
    }
    public SymTable addScope(String labelName)
    {
        SymTable local = new SymTable(this, labelName);
        symbolTables.add(local);
        return local;
    }

    public SymTable goUp()
    {
        return owner;
    }

    public String toString()
    {
        return "";
    }
}