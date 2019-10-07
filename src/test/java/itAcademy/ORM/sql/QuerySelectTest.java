package itAcademy.ORM.sql;

import itAcademy.ORM.connection.transaction.BaseTransaction;
import itAcademy.ORM.connection.transaction.Transaction;
import itAcademy.ORM.crud.CrudOperations;
import itAcademy.ORM.crud.CrudOperationsImpl;
import itAcademy.ORM.mapping.util.Util;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import static itAcademy.ORM.testData.Connection.*;
import static junit.framework.TestCase.assertEquals;

public class QuerySelectTest {
    private Transaction transaction;

    private itAcademy.ORM.testData.Student student;
    private itAcademy.ORM.testData.Student student1;
    private itAcademy.ORM.testData.Student student2;

    private CrudOperations crudOperations;

    @Before
    public void setUp() throws Exception {
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

        student = new itAcademy.ORM.testData.Student();
        student.setFirstName("Hanna");
        student.setLastName("Hurieva");
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        student.setRegistrationDate(date.parse("06/09/2019"));
        student.setGpa(8.00);

        student1 = new itAcademy.ORM.testData.Student();
        student1.setFirstName("Ivan");
        student1.setLastName("Dyominov");
        student1.setRegistrationDate(date.parse("06/09/2019"));
        student1.setGpa(10.0);

        student2 = new itAcademy.ORM.testData.Student();
        student2.setFirstName("Yulia");
        student2.setLastName("Dolenko");
        student2.setRegistrationDate(date.parse("06/09/2019"));
        student2.setGpa(10.0);
    }

    @Test
    public void shouldSelectDataFromTable() throws Exception {
        Statement statement = transaction.open().createStatement();
        String sql = "TRUNCATE TABLE std ";
        statement.execute(sql);

        transaction.open();
        crudOperations.insert(student);
        transaction.commit();
        crudOperations.insert(student1);
        transaction.commit();
        crudOperations.insert(student2);
        transaction.commit();

        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        System.out.println(query.getExecutableSql());

        ResultSet result = statement.executeQuery(query.getExecutableSql());
        while (result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2) +
                    " " + result.getString(3) + " " + result.getString(4)
                    + " " + result.getString(5));
        }

        result.last();
        assertEquals(result.getRow(), 3);
        transaction.close();
    }

    @Test
    public void shouldSelectOneColumnFromTable() throws Exception {
        Statement statement = transaction.open().createStatement();
        String sql = "TRUNCATE TABLE std ";
        statement.execute(sql);

        transaction.open();
        crudOperations.insert(student);
        transaction.commit();
        crudOperations.insert(student1);
        transaction.commit();
        crudOperations.insert(student2);
        transaction.commit();

        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        DataField field = new DataField("last_name");
        query.addField(field);
        System.out.println(query.getExecutableSql());

        ResultSet result = statement.executeQuery(query.getExecutableSql());
        while (result.next()) {
            System.out.println(result.getString(1));
        }
        result.last();
        assertEquals(result.getRow(), 3);
        transaction.close();
    }
}
