package itAcademy.ORM.sql;

/**
 * Holds enumeration for table constraints with default string representations
 * according to SQL standard.
 */
public enum TableConstraintType {

    FOREIGN_KEY("FOREIGN KEY (%s) REFERENCES %s (%s)"),
    UNIQUE("UNIQUE (%s)"),
    PRIMARY_KEY("PRIMARY KEY (%s)"),
    AUTO_INCREMENT("AUTO INCREMENT"),
    USING("USING (%s)"),
    NOT_NULL("NOT NULL");

    private String template;

    private TableConstraintType(String tpl) {
        this.template = tpl;
    }

    public String getTemplate() {
        return this.template;
    }
}
