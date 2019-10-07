package itAcademy.ORM.sql.subclauses;

import itAcademy.ORM.sql.Criterion;
import itAcademy.ORM.sql.Table;

public class JoinClause implements ISubclause {

    private static final String format = "%s %s %s"; // "{JOIN_TYPE} {TABLE} {ON}
    private static final String ON_FORMAT = "ON %s";

    private Criterion on;
    private Table table;
    private JoinType type;

    public JoinClause(JoinType type, String table, Criterion on) {
        this(type, new Table(table), on);
    }

    public JoinClause(JoinType type, Table table, Criterion on) {
        this.type = type;
        this.table = table;
        this.on = on;
    }

    @Override
    public String getClauseFormat() {
        return format;
    }

    @Override
    public String toString() {
        String joinOnClause = on.toString();
        if (joinOnClause != null)
            joinOnClause = String.format(ON_FORMAT, joinOnClause);

        String typeRepr = (this.type != null) ? this.type.toString() : "";
        String tableRepr = table.toString();
        return String.format(getClauseFormat(), typeRepr, tableRepr, joinOnClause);
    }
}
