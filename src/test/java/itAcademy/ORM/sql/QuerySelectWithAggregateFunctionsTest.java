package itAcademy.ORM.sql;

import itAcademy.ORM.connection.transaction.BaseTransaction;
import itAcademy.ORM.connection.transaction.Transaction;
import itAcademy.ORM.mapping.util.Util;
import itAcademy.ORM.sql.operations.QueryFieldOperation;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import static itAcademy.ORM.testData.Connection.*;
import static junit.framework.TestCase.assertEquals;

public class QuerySelectWithAggregateFunctionsTest {
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

        Query q3 = new Query(QueryType.INSERT).addTable("std");
        q3.setField("last_name", "'Dolenko'");
        q3.setField("first_name", "'Yulia'");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = dateFormat.parse("2019-10-06 12:00:00");
        String dateSQL = dateFormat.format(date);
        q3.setField("date_of_registration", "'" + dateSQL + "'");
        q3.setField("gpa", 7.0d);
        statement.execute(q3.getExecutableSql());
        System.out.println(q3.getExecutableSql());
        transaction.commit();

        Query q = new Query(QueryType.INSERT).addTable("std");
        q.setField("last_name", "'Hurieva'");
        q.setField("first_name", "'Hanna'");
        date = dateFormat.parse("2019-09-06 12:00:00");
        dateSQL = dateFormat.format(date);
        q.setField("date_of_registration", "'" + dateSQL + "'");
        q.setField("gpa", 8.0d);
        statement.execute(q.getExecutableSql());
        System.out.println(q.getExecutableSql());

        Query q2 = new Query(QueryType.INSERT).addTable("std");
        q2.setField("last_name", "'Dolenko'");
        q2.setField("first_name", "'Yulia'");
        date = dateFormat.parse("2019-09-06 10:00:00");
        dateSQL = dateFormat.format(date);
        q2.setField("date_of_registration", "'" + dateSQL + "'");
        q2.setField("gpa", 9.0d);
        statement.execute(q2.getExecutableSql());
        System.out.println(q2.getExecutableSql());
        transaction.commit();

    }

    @Test
    public void shouldSelectCOUNT_FromTable() {
        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        new QueryBuilder(query).fieldOp(QueryFieldOperation.COUNT, "last_name");
        System.out.println(query.getExecutableSql());

        try {
            ResultSet result = statement.executeQuery(query.getExecutableSql());
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            result.last();
            assertEquals(result.getRow(), 1);
            assertEquals(Integer.parseInt(result.getString(1)), 3);
            transaction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSelectMIN_FromTable() {
        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        new QueryBuilder(query).fieldOp(QueryFieldOperation.MIN, "gpa");
        query.addField(new DataField("last_name"));
        System.out.println(query.getExecutableSql());

        try {
            ResultSet result = statement.executeQuery(query.getExecutableSql());
            while (result.next()) {
                System.out.println(result.getString(1) + " " + result.getString(2));
            }
            result.last();
            assertEquals(result.getRow(), 1);
            String actual = result.getString(1) + " " + result.getString(2);
            String expected = "7.0 Dolenko";
            assertEquals(actual, expected);
            transaction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSelectMAX_FromTable() {
        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        new QueryBuilder(query).fieldOp(QueryFieldOperation.MAX, "gpa");
        query.addField(new DataField("last_name"));
        System.out.println(query.getExecutableSql());

        try {
            ResultSet result = statement.executeQuery(query.getExecutableSql());
            while (result.next()) {
                System.out.println(result.getString(1) + " " + result.getString(2));
            }
            result.last();
            assertEquals(result.getRow(), 1);
            String actual = result.getString(1) + " " + result.getString(2);
            String expected = "9.0 Dolenko";
            assertEquals(actual, expected);
            transaction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSelectSUM_FromTable() {
        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        new QueryBuilder(query).fieldOp(QueryFieldOperation.SUM, "gpa");
        System.out.println(query.getExecutableSql());

        try {
            ResultSet result = statement.executeQuery(query.getExecutableSql());
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            result.last();
            assertEquals(result.getRow(), 1);

            String actual = result.getString(1);
            String expected = "24.0";
            assertEquals(actual, expected);

            transaction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSelectAVG_FromTable() {
        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        new QueryBuilder(query).fieldOp(QueryFieldOperation.AVG, "gpa");
        query.addField(new DataField("last_name"));
        System.out.println(query.getExecutableSql());

        try {
            ResultSet result = statement.executeQuery(query.getExecutableSql());
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            result.last();
            assertEquals(result.getRow(), 1);

            String actual = result.getString(1);
            String expected = "8.0";
            assertEquals(actual, expected);

            transaction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
