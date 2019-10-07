package itAcademy.ORM.testData;

public class Connection {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=EST5EDT";

    public static final String JDBC_USERNAME = "root";

    public static final String JDBC_PASSWORD = "password";

    public static final int DBCP_MIN_IDLE = 5;

    public static final int DBCP_MAX_IDLE = 10;

    public static final int DBCP_MAX_OPEN_PREPARED_STATEMENTS = 100;
}
