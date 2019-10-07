package itAcademy.ORM.mapping;

import java.lang.reflect.Type;

public class Column {
    private String fieldsName;
    private String fieldsType;
    private Type type;
    private boolean isPK;
    private boolean isFK;
    private boolean autoincrement;
    private Reference reference;

    public Column(String fieldsName, String fieldsType, Type type, boolean isPK, boolean isFK, boolean autoincrement, Reference reference) {
        this.fieldsName = fieldsName;
        this.fieldsType = fieldsType;
        this.type = type;
        this.isPK = isPK;
        this.isFK = isFK;
        this.autoincrement = autoincrement;
        this.reference = reference;
    }

    public String getFieldsName() {
        return fieldsName;
    }

    public void setFieldsName(String fieldsName) {
        this.fieldsName = fieldsName;
    }

    public String getFieldsType() {
        return fieldsType;
    }

    public void setFieldsType(String fieldsType) {
        this.fieldsType = fieldsType;
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

    public boolean isFK() {
        return isFK;
    }

    public void setFK(boolean FK) {
        isFK = FK;
    }

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
