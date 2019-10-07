package itAcademy.ORM.sql.subclauses;

import itAcademy.ORM.sql.Criterion;
import itAcademy.ORM.sql.subclauses.ISubclause;

public class HavingClause implements ISubclause {

    private static final String format = "HAVING %s";

    private Criterion conditional;

    public HavingClause(Criterion criterion){
        this.conditional = criterion;
    }

    @Override
    public String getClauseFormat() {
        return format;
    }

    @Override
    public String toString() {
        String conditionalRepresentation = conditional.toString();
        return String.format(getClauseFormat(), conditionalRepresentation);
    }
}
