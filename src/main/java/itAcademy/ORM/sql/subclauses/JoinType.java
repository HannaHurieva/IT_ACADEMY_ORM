package itAcademy.ORM.sql.subclauses;

public enum JoinType {
    JOIN("JOIN"),
    INNER_JOIN("INNER JOIN"),
    CROSS_JOIN("CROSS JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),
    LEFT_OUTER_JOIN("LEFT OUTER JOIN"),
    RIGHT_OUTER_JOIN("RIGHT OUTER JOIN"),
    FULL_JOIN("FULL JOIN"),
    FULL_OUTER_JOIN("FULL OUTER JOIN");

    private String repr;

    JoinType(String s) {
        this.repr = s;
    }

    @Override
    public String toString() {
        return this.repr;
    }

}
