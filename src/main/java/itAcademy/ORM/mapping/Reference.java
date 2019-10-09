package itAcademy.ORM.mapping;

public class Reference {
    private String fieldName;
    private String toTable;
    private String toTableFieldName;

    public Reference(String fieldName, String toTable, String toTableFieldName) {
        this.fieldName = fieldName;
        this.toTable = toTable;
        this.toTableFieldName = toTableFieldName;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getToTable() {
        return toTable;
    }

    public void setToTable(String toTable) {
        this.toTable = toTable;
    }

    public String getToTableFieldName() {
        return toTableFieldName;
    }

    public void setToTableFieldName(String toTableFieldName) {
        this.toTableFieldName = toTableFieldName;
    }
}
