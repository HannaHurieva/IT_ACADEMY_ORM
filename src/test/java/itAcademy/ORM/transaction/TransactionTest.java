package itAcademy.ORM.transaction;

import itAcademy.ORM.mapping.CRUD.*;
import itAcademy.ORM.mapping.util.Util;
import itAcademy.ORM.reflection.ReflectionException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static itAcademy.ORM.mapping.StaticVariables.*;
import static itAcademy.ORM.mapping.StaticVariables.DBCP_MAX_IDLE;
import static itAcademy.ORM.mapping.StaticVariables.DBCP_MAX_OPEN_PREPARED_STATEMENTS;
import static itAcademy.ORM.mapping.StaticVariables.DBCP_MIN_IDLE;
import static itAcademy.ORM.mapping.StaticVariables.JDBC_DRIVER;
import static itAcademy.ORM.mapping.StaticVariables.JDBC_PASSWORD;
import static itAcademy.ORM.mapping.StaticVariables.JDBC_URL;
import static itAcademy.ORM.mapping.StaticVariables.JDBC_USERNAME;

public class TransactionTest {

    private CRUDOperations crudOperations = new CRUDOperations();

    @Before
    public void getConnection() throws SQLException {
        basicDataSource.setDriverClassName(JDBC_DRIVER);
        basicDataSource.setUrl(JDBC_URL);
        basicDataSource.setUsername(JDBC_USERNAME);
        basicDataSource.setPassword(JDBC_PASSWORD);
        basicDataSource.setMinIdle(DBCP_MIN_IDLE);
        basicDataSource.setMaxIdle(DBCP_MAX_IDLE);
        basicDataSource.setMaxOpenPreparedStatements(DBCP_MAX_OPEN_PREPARED_STATEMENTS);
        basicDataSource.setMaxWait(-1L);
//        Util.generateTables();
        transaction = new itAcademy.ORM.transaction.BaseTransaction(basicDataSource.getConnection());
    }

    @Test
    public void insertTest() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException {
        Command command = new InsertCommand();
        command.execute(crudOperations);
    }

    @Test
    public void updateTest() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException {
        Command command = new UpdateCommand();
        command.execute(crudOperations);
    }

    @Test
    public void deleteTest() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException {
        Command command = new DeleteCommand();
        command.execute(crudOperations);
    }

    @Test
    public void selectAllTest() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException {
        Command command = new SelectCommand();
        command.execute(crudOperations);
    }
}
