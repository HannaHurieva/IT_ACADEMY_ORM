package itAcademy.ORM.sql.subclauses;

import itAcademy.ORM.sql.subclauses.ISubclause;

public class GroupByClause implements ISubclause {

    private static final String format = "GROUP BY %s";

    private String[] by;

    public GroupByClause(String... by){
        this.by = by;
    }

    @Override
    public String getClauseFormat() {
        return format;
    }

    @Override
    public String toString() {
        return String.format(getClauseFormat(), concatNoEscape(this.by, ", "));
    }

    public static String concatNoEscape(String[] a, String union){
        if (a == null) return null;
        StringBuilder sb = new StringBuilder();
        for(int i  = 0 ; i < a.length; i++){
            sb.append(a[i]);
            if(i != a.length-1) sb.append(union);
        }
        return sb.toString();
    }
}
