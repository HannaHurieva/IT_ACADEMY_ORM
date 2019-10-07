package itAcademy.ORM.mapping;

import java.util.LinkedHashMap;
import java.util.Map;

public class Update {

    private Map primaryKey = new LinkedHashMap();
    private Map columns = new LinkedHashMap();
    private Table tableName;

    public Table getTableName() {
        return tableName;
    }

    public Update setTableName(Table tableName) {
        this.tableName = tableName;
        return this;
    }

    public Update setPrimaryKeyColumnNames(String[] columnNames) {
        this.primaryKey.clear();
        addPrimaryKeyColumns(columnNames);
        return this;
    }

    public Update addPrimaryKeyColumns(String[] columnNames) {
        for (String columnName : columnNames) {
            addPrimaryKeyColumn(columnName, "?");
        }
        return this;
    }

    public Update addPrimaryKeyColumns(String[] columnNames, boolean[] includeColumns, String[] valueExpressions) {
        for (int i = 0; i < columnNames.length; i++) {
            if (includeColumns[i]) {
                addPrimaryKeyColumn(columnNames[i], valueExpressions[i]);
            }
        }
        return this;
    }

    public Update addPrimaryKeyColumns(String[] columnNames, String[] valueExpressions) {
        for (int i = 0; i < columnNames.length; i++) {
            addPrimaryKeyColumn(columnNames[i], valueExpressions[i]);
        }
        return this;
    }

    public Update addPrimaryKeyColumn(String columnName, String valueExpression) {
        this.primaryKey.put(columnName, valueExpression);
        return this;
    }

    private Update addColumn(String columnName, String valueExpression) {
        columns.put(columnName, valueExpression);
        return this;
    }


}
