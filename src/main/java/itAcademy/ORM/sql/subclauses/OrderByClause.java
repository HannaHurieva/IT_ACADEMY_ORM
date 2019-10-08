package itAcademy.ORM.sql.subclauses;

public class OrderByClause implements ISubclause {

    private static final String format = "ORDER BY %s";

    private enum ORDER_OPTIONS {
        ASC, DESC
    }

    private String[] orderByListValues;

    public OrderByClause(String... orderByListValues) {
        this.orderByListValues = orderByListValues;
    }

    @Override
    public String getClauseFormat() {
        return format;
    }

    public String prepareFieldOrderPolicy(String field) {
        field = field.trim();
        ORDER_OPTIONS policy = null;
        if (field.charAt(0) == '+') {
            policy = ORDER_OPTIONS.ASC;
            field = field.substring(1);
        } else if (field.charAt(0) == '-') {
            policy = ORDER_OPTIONS.DESC;
            field = field.substring(1);
        }
        return field + ((policy != null) ? " " + policy.toString() : "");
    }

    @Override
    public String toString() {
        StringBuilder order = new StringBuilder();
        for (int i = 0; i < orderByListValues.length; i++) {
            order.append(prepareFieldOrderPolicy(orderByListValues[i]));
            if (i != orderByListValues.length - 1) {
                order.append(", ");
            }
        }
        return String.format(getClauseFormat(), order.toString());
    }
}
