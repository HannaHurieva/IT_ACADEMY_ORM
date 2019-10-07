package itAcademy.ORM.transaction;

import itAcademy.ORM.mapping.CRUD.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static itAcademy.ORM.mapping.StaticVariables.*;

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
        transaction = new itAcademy.ORM.transaction.BaseTransaction(basicDataSource.getConnection());
    }

    @Test
    public void insertTest() {
        Command command = new InsertCommand();
        command.execute(crudOperations);
    }

    @Test
    public void updateTest() {
        Command command = new UpdateCommand();
        command.execute(crudOperations);
    }

    @Test
    public void deleteTest() {
        Command command = new DeleteCommand();
        command.execute(crudOperations);
    }

    @Test
    public void selectAllTest() {
        Command command = new SelectCommand();
        command.execute(crudOperations);
    }
}
