package itAcademy.ORM.mapping;

public class Column {
    private static final int DEFAULT_LENGTH = 255;

    private String name;
    private int length = DEFAULT_LENGTH;
    private boolean nullable = true;
    private boolean unique;

    private String checkConstraint;
    private String defaultValue;

    /*
    private Value value;
    */

    public Column() {
    }

    public Column(String columnName) {
        setName( columnName );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }


    public String getCheckConstraint() {
        return checkConstraint;
    }

    public void setCheckConstraint(String checkConstraint) {
        this.checkConstraint = checkConstraint;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /*
    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column column = (Column) o;

        if (length != column.length) return false;
        if (nullable != column.nullable) return false;
        if (unique != column.unique) return false;
        if (name != null ? !name.equals(column.name) : column.name != null) return false;
        if (checkConstraint != null ? !checkConstraint.equals(column.checkConstraint) : column.checkConstraint != null)
            return false;
        return defaultValue != null ? defaultValue.equals(column.defaultValue) : column.defaultValue == null;
    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nullable ? 1 : 0);
        result = 31 * result + (unique ? 1 : 0);
        result = 31 * result + (checkConstraint != null ? checkConstraint.hashCode() : 0);
        result = 31 * result + (defaultValue != null ? defaultValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", nullable=" + nullable +
                ", unique=" + unique +
                ", checkConstraint='" + checkConstraint + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}
