package itAcademy.ORM.mapping;

import java.lang.reflect.Type;

public class Field {
    private String javaName;
    private String dbName;
    private Type type;
    private boolean isPrimaryKey;
    private boolean isForeignKey;
    private boolean autoincrement;

    public Field(String javaName, String dbName, Type type, boolean isPrimaryKey, boolean isForeignKey, boolean autoincrement) {
        this.javaName = javaName;
        this.dbName = dbName;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.isForeignKey = isForeignKey;
        this.autoincrement = autoincrement;
    }

    public Field(String name, String s, Class<?> type, boolean b, boolean b1) {
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    public boolean isForeignKey() {
        return isForeignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        isForeignKey = foreignKey;
    }
}
