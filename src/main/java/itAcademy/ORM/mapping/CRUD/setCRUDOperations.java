package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.util.Util;
import itAcademy.ORM.reflection.ReflectionException;
import itAcademy.ORM.test.TestEntity;
import itAcademy.ORM.transaction.BaseTransaction;
import itAcademy.ORM.transaction.Transaction;
import org.apache.commons.dbcp.BasicDataSource;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

class setCRUDOperations {

    private Transaction transaction;

    private static TestEntity test;

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=EST5EDT";

    private static final String JDBC_USERNAME = "root";

    private static final String JDBC_PASSWORD = "1234";

    private static final int DBCP_MIN_IDLE = 5;

    private static final int DBCP_MAX_IDLE = 10;

    private static final int DBCP_MAX_OPEN_PREPARED_STATEMENTS = 100;

    private void setUp() throws SQLException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(JDBC_DRIVER);
        basicDataSource.setUrl(JDBC_URL);
        basicDataSource.setUsername(JDBC_USERNAME);
        basicDataSource.setPassword(JDBC_PASSWORD);
        basicDataSource.setMinIdle(DBCP_MIN_IDLE);
        basicDataSource.setMaxIdle(DBCP_MAX_IDLE);
        basicDataSource.setMaxOpenPreparedStatements(DBCP_MAX_OPEN_PREPARED_STATEMENTS);
        basicDataSource.setMaxWait(-1L);
        Util.generateTables();
        transaction = new BaseTransaction(basicDataSource.getConnection());
        test = new TestEntity();
    }

    private void permanentDeleteChanges() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException {
        transaction.delete(test);
        transaction.commit();
    }

    void insert() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException {
        setUp();
        test.setId(1);
        test.setUsername(12);
        test.setTitle("insert");
        transaction.insert(test);
        transaction.commit();
        permanentDeleteChanges();
    }


}
