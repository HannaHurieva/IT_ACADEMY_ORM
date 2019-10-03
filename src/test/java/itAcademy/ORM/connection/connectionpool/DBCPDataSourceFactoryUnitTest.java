package itAcademy.ORM.connection.connectionpool;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static itAcademy.ORM.testData.Connection.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DBCPDataSourceFactoryUnitTest {

    private Connection connection;


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
