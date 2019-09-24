package itAcademy.ORM.mapping;

import java.lang.reflect.Type;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;

public class OneToOne {

    private Type Entity;

    private Map<Object, Object> TableNames;

    private Integer primaryKey;

    private Integer foreignKey;

    public OneToOne(Type entity, Map<Object, Object> tableNames, Integer primaryKey, Integer foreignKey) {
        Entity = entity;
        TableNames = tableNames;
        this.primaryKey = primaryKey;
        this.foreignKey = foreignKey;
    }

    public Type getEntity() {
        return Entity;
    }

    public Map<Object, Object> getTableNames() {
        return TableNames;
    }

    public Integer getPrimaryKey() {
        return primaryKey;
    }

    public Integer getForeignKey() {
        return foreignKey;
    }

    public void setEntity(Type entity) {
        Entity = entity;
    }

    public void setTableNames(Map<Object, Object> tableNames) {
        TableNames = tableNames;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setForeignKey(Integer foreignKey) {
        this.foreignKey = foreignKey;
    }
}
