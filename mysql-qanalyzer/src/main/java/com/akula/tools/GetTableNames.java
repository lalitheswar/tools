package com.akula.tools;

import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.akula.tools.parser.pojo.TableSource;
import com.akula.tools.parser.utils.CaseChangingCharStream;

/**
 * Hello world!
 *
 */
public class GetTableNames {
    
    public static void main( String[] args ) {
        simpleQueryParse();
        System.out.println("");
        System.out.println("");
        joinQueryParse();
    }
    
    public static void simpleQueryParse() {
        String sql = "select cust_name from CUSTOMERS where cust_name like 'Kash%'";
        CharStream charStream = new CaseChangingCharStream(CharStreams.fromString(sql), true);
        MySqlLexer mySqlLexer = new MySqlLexer(charStream);
        
        MySqlParser mySqlParser = new MySqlParser(new CommonTokenStream(mySqlLexer));
        
        ParseTree mySqlParseTree = mySqlParser.dmlStatement();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        QueryTableNameParserListener tableNameParserListener = new QueryTableNameParserListener();
        parseTreeWalker.walk(tableNameParserListener, mySqlParseTree);
        
        List<TableSource> tableList = tableNameParserListener.getTableNameList();
        System.out.println("Tables extracted from given query - ");
        System.out.println("------------------------------------");
        for (TableSource tableSource : tableList) {
            System.out.println(tableSource.getTableName());
        }
    }
    
    public static void joinQueryParse() {
        String sql = "SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate\r\n" + 
                "FROM Orders\r\n" + 
                "INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;";
        CharStream charStream = new CaseChangingCharStream(CharStreams.fromString(sql), true);
        MySqlLexer mySqlLexer = new MySqlLexer(charStream);
        
        MySqlParser mySqlParser = new MySqlParser(new CommonTokenStream(mySqlLexer));
        
        ParseTree mySqlParseTree = mySqlParser.dmlStatement();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        QueryTableNameParserListener tableNameParserListener = new QueryTableNameParserListener();
        parseTreeWalker.walk(tableNameParserListener, mySqlParseTree);
        
        List<TableSource> tableList = tableNameParserListener.getTableNameList();
        System.out.println("Tables extracted from given join query - ");
        System.out.println("-----------------------------------------");
        for (TableSource tableSource : tableList) {
            System.out.println(tableSource.getTableName());
        }
    }
}
