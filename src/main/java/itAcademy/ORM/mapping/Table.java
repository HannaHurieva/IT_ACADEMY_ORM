package itAcademy.ORM.mapping;


import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private Type Entity;
    private String TableName;
    private List<Field> fields;
    private Map<String, String> mapping;

    public Table(Type entity, String tableName, List<Field> fields) {
        Entity = entity;
        TableName = tableName;
        this.fields = fields;
        mapping = new LinkedHashMap<>();
        for(Field f: fields)
            mapping.put(f.getJavaName(),f.getDbName());
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
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

}
