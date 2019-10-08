package itAcademy.ORM.sql.subclauses;

public class LimitClause implements ISubclause {

    private static final String format = "LIMIT %d, %d";

    private int count;
    private int start;

    public LimitClause(int recordCount) {
        this(recordCount, 0);
    }

    public LimitClause(int recordCount, int startOffset) {
        this.count = recordCount;
        this.start = startOffset;
    }

    @Override
    public String getClauseFormat() {
        return format;
    }

    @Override
    public String toString() {
        return String.format(getClauseFormat(), start, count);
    }
}
