package itAcademy.ORM.connection.connectionpool;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DBCPDataSourceFactoryUnitTest {

    Connection connection;

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=EST5EDT";

    public static final String JDBC_USERNAME = "root";

    public static final String JDBC_PASSWORD = "password";

    public static final int DBCP_MIN_IDLE = 5;

    public static final int DBCP_MAX_IDLE = 10;

    public static final int DBCP_MAX_OPEN_PREPARED_STATEMENTS = 100;

    @Before
    public void setUp() throws SQLException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(JDBC_DRIVER);
        basicDataSource.setUrl(JDBC_URL);
        basicDataSource.setUsername(JDBC_USERNAME);
        basicDataSource.setPassword(JDBC_PASSWORD);
        basicDataSource.setMinIdle(DBCP_MIN_IDLE);
        basicDataSource.setMaxIdle(DBCP_MAX_IDLE);
        basicDataSource.setMaxOpenPreparedStatements(DBCP_MAX_OPEN_PREPARED_STATEMENTS);
        basicDataSource.setMaxWait(-1L);

        connection = basicDataSource.getConnection();
    }

    @Test
    public void shouldDBCPDataSourceFactoryClass_whenCalledGetConnection_thenCorrect() throws SQLException {
        assertTrue(connection.isValid(1));
    }

    @Test
    public void testIfConnectionNotNull() {
        assertNotNull(connection);
    }
}
