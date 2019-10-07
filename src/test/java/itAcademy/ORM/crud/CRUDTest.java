package itAcademy.ORM.crud;

import itAcademy.ORM.connection.transaction.BaseTransaction;
import itAcademy.ORM.connection.transaction.Transaction;
import itAcademy.ORM.mapping.util.Util;
import itAcademy.ORM.testData.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static itAcademy.ORM.testData.Connection.*;
import static junit.framework.TestCase.assertEquals;

public class CRUDTest {

    private Transaction transaction;

    private itAcademy.ORM.testData.Test test;
    private User user;

    private CrudOperations crudOperations;

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
        crudOperations = new CrudOperationsImpl();
        test = new itAcademy.ORM.testData.Test();
        user = new User();
        user.setId(12);
        user.setName("user");
        user.setTest(1);
        test.setId(1);
        test.setUserId(user.getId());
        test.setTitle("insert");
    }

    @Test
    public void insertTest() throws Exception {
        transaction.open();
        crudOperations.insert(user);
        crudOperations.insert(test);
        transaction.commit();
        ArrayList<Object> list = crudOperations.findAll(test.getClass());
        transaction.commit();
        assertEquals(list.size(), 1);
        crudOperations.delete(test);
        crudOperations.delete(user);
        transaction.commit();
        transaction.close();
    }

    @Test
    public void removeTest() throws Exception {
        transaction.open();
        crudOperations.insert(user);
        crudOperations.insert(test);
        transaction.commit();
        ArrayList<Object> list = crudOperations.findAll(test.getClass());
        assertEquals(list.size(), 1);
        crudOperations.delete(test);
        crudOperations.delete(user);
        transaction.commit();
        list = crudOperations.findAll(test.getClass());
        transaction.commit();
        transaction.close();
        assertEquals(list.size(), 0);

    }

    @Test
    public void updateTest() throws Exception {
        transaction.open();
        crudOperations.insert(user);
        crudOperations.insert(test);
        ArrayList<Object> list = crudOperations.findAll(test.getClass());
        assertEquals(((itAcademy.ORM.testData.Test) list.get(0)).getTitle(), "insert");
        test.setTitle("update");
        crudOperations.update(test);
        transaction.commit();
        list = crudOperations.findAll(test.getClass());
        transaction.commit();
        assertEquals(((itAcademy.ORM.testData.Test) list.get(0)).getTitle(), "update");
        crudOperations.delete(test);
        crudOperations.delete(user);
        transaction.commit();
        transaction.close();
    }


}
