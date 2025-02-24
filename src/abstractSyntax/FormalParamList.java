/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class FormalParamList extends GrammarDef {
    public FormalParamList(TCparser tcp) {
        super(tcp);
    }
    String buffer;
    @Override
    void parseDefinition() {
        if (parser.tok.getTok().equals(TCscanner.Tokens.INT)) {
            buffer = "int";
            new Type(parser);
        } else if (parser. tok.getTok().equals(TCscanner.Tokens.CHAR)) {
            buffer = "char";
            new Type(parser);
        } else {
            logError("missing type");
        }

        if (parser.tok.getTok().equals(TCscanner.Tokens.ID)) {
            buffer = parser.tok.getLex() + ", " + buffer;
            parser.codegenerator.insert(new CGVarDef(parser.tok.getLex()));
            //consumes identifier
            parser.getNextToken();
        } else {
            logError("missing id token");
        }
        parser.printer.print("varDef(" + buffer + "), ");

        while (parser.tok.getTok().equals(TCscanner.Tokens.COMMA)) {
            buffer = "";
            if (parser.tok.getTok().equals(TCscanner.Tokens.COMMA)) {
                //consume comma
                parser.getNextToken();
            } else {
                logError("missing ','");
            }

            if (parser.tok.getTok().equals(TCscanner.Tokens.INT)) {
                buffer = "int";
                new Type(parser);
            } else if (parser.tok.getTok().equals(TCscanner.Tokens.CHAR)) {
                buffer = "char";
                new Type(parser);
            } else {
                logError("missing type");
            }

            if (parser.tok.getTok().equals(TCscanner.Tokens.ID)) {
                buffer = parser.tok.getLex() + ", " + buffer;
                parser.codegenerator.insert(new CGVarDef(parser.tok.getLex()));
                //consume ID
               parser. getNextToken();
            }
            parser.printer.print("varDef(" + buffer + "),");
        }
    }

    @Override
    public String toString()
    {
        return "reducing FormalParamList";
    }
}
