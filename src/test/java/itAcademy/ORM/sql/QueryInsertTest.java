package itAcademy.ORM.sql;

import itAcademy.ORM.connection.transaction.BaseTransaction;
import itAcademy.ORM.connection.transaction.Transaction;
import itAcademy.ORM.mapping.util.Util;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import static itAcademy.ORM.testData.Connection.*;
import static junit.framework.TestCase.assertEquals;

public class QueryInsertTest {
    private Transaction transaction;
    private Statement statement;

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
        statement = transaction.open().createStatement();
        String sql = "TRUNCATE TABLE std ";
        statement.execute(sql);
    }

    @Test
    public void shouldInsertDataIntoTable() throws Exception {
        Query q = new Query(QueryType.INSERT).addTable("std");
        q.setField("last_name", "'Hurieva'");
        q.setField("first_name", "'Hanna'");
        q.setField("gpa", 8.0d);
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

    @Test
    public void shouldInsertObjectIntoTable() throws Exception {
        Query q = new Query(QueryType.INSERT).addTable("std");
        q.setField("last_name", "'LastName'");
        q.setField("first_name", "'FirstName'");

        java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = dateFormat.parse("2019-09-06 00:00:00");
        String dateSQL = dateFormat.format(date);
        q.setField("date_of_registration", "'" + dateSQL + "'");
        q.setField("gpa", 10.0d);
        System.out.println(q.getExecutableSql());
        statement.execute(q.getExecutableSql());

        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        ResultSet result = statement.executeQuery(query.getExecutableSql());
        result.last();
        assertEquals(result.getRow(), 1);
        transaction.close();
    }

    @Test
    public void shouldInsertSomeObjectsIntoTable() throws Exception {
        Query q1 = new Query(QueryType.INSERT).addTable("std");
        q1.setField("last_name", "'LastNameStd1'");
        q1.setField("first_name", "'FirstNameStd1'");

        java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = dateFormat.parse("2019-09-06 12:00:00");
        String dateSQL = dateFormat.format(date);
        q1.setField("date_of_registration", "'" + dateSQL + "'");
        q1.setField("gpa", 8.0d);
        System.out.println(q1.getExecutableSql());
        statement.execute(q1.getExecutableSql());

        Query q2 = new Query(QueryType.INSERT).addTable("std");
        q2.setField("last_name", "'LastNameStd2'");
        q2.setField("first_name", "'FirstNameStd2'");
        date = dateFormat.parse("2019-10-06 08:00:00");
        dateSQL = dateFormat.format(date);
        q2.setField("date_of_registration", "'" + dateSQL + "'");
        q2.setField("gpa", 10.0d);
        System.out.println(q2.getExecutableSql());
        statement.execute(q2.getExecutableSql());

        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        ResultSet result = statement.executeQuery(query.getExecutableSql());
        result.last();
        assertEquals(result.getRow(), 2);
        transaction.close();
    }
}
