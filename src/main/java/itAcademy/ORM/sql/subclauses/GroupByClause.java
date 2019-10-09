package itAcademy.ORM.sql.subclauses;

public class GroupByClause implements ISubclause {

    private static final String format = "GROUP BY %s";

    private String[] groupByListValues;

    public GroupByClause(String... groupByListValues) {
        this.groupByListValues = groupByListValues;
    }

    @Override
    public String getClauseFormat() {
        return format;
    }

    @Override
    public String toString() {
        return String.format(getClauseFormat(), concatWithSeparators(this.groupByListValues, ", "));
    }

    private static String concatWithSeparators(String[] groupByListValues, String separator) {
        if (groupByListValues == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < groupByListValues.length; i++) {
            sb.append(groupByListValues[i]);
            if (i != groupByListValues.length - 1) sb.append(separator);
        }
        return sb.toString();
    }
}
