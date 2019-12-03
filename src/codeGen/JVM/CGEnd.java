package codeGen.JVM;

import symTable.Symbol;
import symTable.SymbolAlreadyDeclared;

public class CGEnd extends CGInstruction {

    void codeGen()
    {
        target.code.add("ireturn");
        target.code.add(".end method");
    }
}
