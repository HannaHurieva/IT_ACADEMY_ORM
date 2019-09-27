package itAcademy.ORM.transaction;

import itAcademy.ORM.mapping.util.Util;
import itAcademy.ORM.reflection.ReflectionException;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TransactionTest {
    private Transaction transaction;

    private itAcademy.ORM.test.Test test;

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=EST5EDT";

    private static final String JDBC_USERNAME = "root";

    private static final String JDBC_PASSWORD = "root";

    private static final int DBCP_MIN_IDLE = 5;

    private static final int DBCP_MAX_IDLE = 10;

    private static final int DBCP_MAX_OPEN_PREPARED_STATEMENTS = 100;

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
        Util.generateTables();
        transaction = new BaseTransaction(basicDataSource.getConnection());
        test = new itAcademy.ORM.test.Test();
        test.setId(1);
        test.setUsername(12);
        test.setTitle("insert");

    }

    @Test
    public void insertTest() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException {
        transaction.insert(test);
        transaction.commit();
        ArrayList<Object> list = transaction.findAll(test.getClass());
        transaction.commit();
        assertEquals(list.get(0), test);
        transaction.delete(test);
        transaction.commit();
    }

    @Test
    public void removeTest() throws SQLException, IllegalAccessException, InstantiationException, IOException, ReflectionException, NoSuchMethodException, InvocationTargetException {
        transaction.insert(test);
        transaction.commit();
        ArrayList<Object> list = transaction.findAll(test.getClass());
        assertEquals(list.size(), 1);
        transaction.delete(test);
        transaction.commit();
        list = transaction.findAll(test.getClass());
        transaction.commit();
        assertEquals(list.size(), 0);
    }

    @Test
    public void updateTest() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ReflectionException, IOException, InstantiationException {
        transaction.insert(test);
        ArrayList<Object> list = transaction.findAll(test.getClass());
        assertEquals(((itAcademy.ORM.test.Test) list.get(0)).getTitle(), "insert");
        test.setTitle("update");
        transaction.update(test);
        transaction.commit();
        list = transaction.findAll(test.getClass());
        transaction.commit();
        assertEquals(((itAcademy.ORM.test.Test) list.get(0)).getTitle(), "update");
        transaction.delete(test);
        transaction.commit();
    }


}
