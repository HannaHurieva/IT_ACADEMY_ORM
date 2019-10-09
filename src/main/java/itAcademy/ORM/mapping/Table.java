package itAcademy.ORM.mapping;


import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private Type Entity;
    private String TableName;
    private List<Column> columns;
    private Map<String, String> mapping;

    public Table() {
    }

    public Table(Type entity, String tableName, List<Column> columns) {
        Entity = entity;
        TableName = tableName;
        this.columns = columns;
        mapping = new LinkedHashMap<>();
        for(Column f: columns)
            mapping.put(f.getFieldsName(),f.getFieldsType());
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public Type getEntity() {
        return Entity;
    }

    public void setEntity(Type entity) {
        Entity = entity;
    }

    public Map<String, String> getMapping(){
        return mapping;
    }

    @Override
    public String toString() {
        return "Table{" +
                "Entity=" + Entity +
                ", TableName='" + TableName + '\'' +
                ", columns=" + columns +
                ", mapping=" + mapping +
                '}';
    }
}
