package itAcademy.ORM.mapping;

import java.lang.reflect.Type;

public class Field {
    private String fieldName;
    private String dbName;
    private Type type;
    private boolean isPK;
    private boolean autoincrement;

    public Field(String javaName, String dbName, Type type, boolean isPK, boolean autoincrement) {
        this.fieldName = javaName;
        this.dbName = dbName;
        this.type = type;
        this.isPK = isPK;
        this.autoincrement = autoincrement;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isPK() {
        return isPK;
    }

    public void setPK(boolean PK) {
        isPK = PK;
    }

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }
}
