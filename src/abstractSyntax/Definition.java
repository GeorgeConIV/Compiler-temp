package abstractSyntax;

import parser.*;

public class Definition extends GrammarDef {
    public Definition(TCparser tcp) {
        super(tcp);
    }
String buffer;
    @Override
    void parseDefinition() {
        if(parser.tok.getTok().equals(TCscanner.Tokens.INT))
        {
            buffer = "int";
            new Type(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.CHAR))
        {
            buffer = "char";
            new Type(parser);
        }
        else
        {
            buffer = "NULL";
            logError("");
        }

        if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            //consumes ID
            parser.getNextToken();
        }
        else
        {
            logError();
        }


        if(parser.tok.getTok().equals(TCscanner.Tokens.LPAREN))
        {
            parser.printer.printspaces("funcDef(");
            new Definition(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            parser.printer.printspaces("funcDef(");
            //Consumes semicolon
            parser.getNextToken();
            return;
        }
        else
        {
            logError();
        }
    }
}
