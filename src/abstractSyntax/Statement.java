/*EGRE 591 -- Compiler Construction
 *By Mark Johnston and George Constantine
 */
package abstractSyntax;

import parser.*;
import codeGen.JVM.*;

public class Statement extends GrammarDef
{
    public Statement(TCparser tcp)
    {
        super(tcp);
    }

    @Override
    void parseDefinition()
    {
        if(parser.tok.getTok().equals(TCscanner.Tokens.BREAK))
        {
            new BreakStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.ID))
        {
            new ExpressionStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.LCURLY) )
        {
            new CompoundStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.IF))
        {
            new IfStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.SEMICOLON))
        {
            new NullStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.RETURN))
        {
            new ReturnStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.WHILE))
        {
            new WhileStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.READ))
        {
            new ReadStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.WRITE))
        {
            new WriteStatement(parser);
        }
        else if(parser.tok.getTok().equals(TCscanner.Tokens.NEWLINE))
        {
            new NewLineStatement(parser);
        }
        else
        {
            logError("No statement");
        }

    }

    @Override
    public String toString()
    {
        return "reducing Statement";
    }
}
