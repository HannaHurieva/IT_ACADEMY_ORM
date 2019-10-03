package itAcademy.ORM.mapping;

import itAcademy.ORM.transaction.Transaction;
import org.apache.commons.dbcp.BasicDataSource;

public class StaticVariables {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=EST5EDT";

    public static final String JDBC_USERNAME = "root";

    public static final String JDBC_PASSWORD = "1234";

    public static final int DBCP_MIN_IDLE = 5;

    public static final int DBCP_MAX_IDLE = 10;

    public static final int DBCP_MAX_OPEN_PREPARED_STATEMENTS = 100;

    public static BasicDataSource basicDataSource = new BasicDataSource();

    public static Transaction transaction;
}
