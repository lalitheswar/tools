package com.akula.tools;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.akula.tools.parser.utils.CaseChangingCharStream;

/**
 * Hello world!
 *
 */
public class GetTableNames {
    
    public static void main( String[] args ) {
        String sql = "select cust_name from database..table where cust_name like 'Kash%'";
        CharStream charStream = new CaseChangingCharStream(CharStreams.fromString(sql), true);
        MySqlLexer mySqlLexer = new MySqlLexer(charStream);
        
        MySqlParser mySqlParser = new MySqlParser(new CommonTokenStream(mySqlLexer));
        
        ParseTree mySqlParseTree = mySqlParser.dmlStatement();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        QueryTableNameParserListener tableNameParserListener = new QueryTableNameParserListener();
        parseTreeWalker.walk(tableNameParserListener, mySqlParseTree);
    }
}
