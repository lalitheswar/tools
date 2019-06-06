package com.akula.tools;

import java.util.ArrayList;
import java.util.List;

import com.akula.tools.MySqlParser.RootContext;
import com.akula.tools.MySqlParser.TableNameContext;
import com.akula.tools.parser.pojo.TableSource;

public class QueryTableNameParserListener extends MySqlParserBaseListener {
    private List<TableSource> tableList = new ArrayList<>();    
    
    @Override
    public void enterRoot(RootContext ctx) {
        // TODO Auto-generated method stub
        super.enterRoot(ctx);
    }
    
    @Override
    public void exitRoot(RootContext ctx) {
        // TODO Auto-generated method stub
        super.exitRoot(ctx);
    }
    
    
    @Override
    public void enterTableName(TableNameContext ctx) {
        super.enterTableName(ctx);
        tableList.add(new TableSource(ctx.getText()));
    }
    
    public List<TableSource> getTableNameList() {
        return tableList;
    }
}
