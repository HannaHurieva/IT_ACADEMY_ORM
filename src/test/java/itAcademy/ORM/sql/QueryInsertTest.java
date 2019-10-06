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

import static itAcademy.ORM.testData.Connection.*;
import static junit.framework.TestCase.assertEquals;

public class QueryInsertTest {
    private Transaction transaction;

    private itAcademy.ORM.testData.Student student;

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
    }

    @Test
    public void shouldInsertDataIntoTable() throws Exception {
        Statement statement = transaction.open().createStatement();
        String sql = "TRUNCATE TABLE std ";
        statement.execute(sql);

        Query q = new Query(QueryType.INSERT).addTable("std");
        q.setField("last_name", "'Hurieva'");
        q.setField("first_name", "'Hanna'");
        q.setField("gpa", 10.0d);
        System.out.println(q.getExecutableSql());
        statement.execute(q.getExecutableSql());

        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        DataField field = new DataField("*");
        query.addField(field);

        ResultSet result = statement.executeQuery(query.getExecutableSql());
        result.last();
        assertEquals(result.getRow(), 1);
        transaction.close();
    }
}
