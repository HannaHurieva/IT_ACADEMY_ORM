package itAcademy.ORM.connection.connectionpool;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static itAcademy.ORM.testData.Connection.*;
import static org.junit.Assert.*;

public class DBCPDataSourceFactoryUnitTest {

    private BasicDataSource basicDataSource;

    @Before
    public void setUp() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(JDBC_DRIVER);
        basicDataSource.setUrl(JDBC_URL);
        basicDataSource.setUsername(JDBC_USERNAME);
        basicDataSource.setPassword(JDBC_PASSWORD);
        basicDataSource.setMinIdle(DBCP_MIN_IDLE);
        basicDataSource.setMaxIdle(DBCP_MAX_IDLE);
        basicDataSource.setMaxOpenPreparedStatements(DBCP_MAX_OPEN_PREPARED_STATEMENTS);
        basicDataSource.setMaxWait(-1L);
    }

    @Test
    public void shouldDBCPDataSourceFactoryClass_whenCalledGetConnection_thenCorrect() {
        try (Connection connection = basicDataSource.getConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIfConnectionNotNull() {
        try (Connection connection = basicDataSource.getConnection()) {
            assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
