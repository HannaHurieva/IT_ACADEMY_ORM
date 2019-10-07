package itAcademy.ORM.sql;

import itAcademy.ORM.connection.transaction.BaseTransaction;
import itAcademy.ORM.connection.transaction.Transaction;
import itAcademy.ORM.mapping.util.Util;
import itAcademy.ORM.sql.subclauses.SubclauseType;
import itAcademy.ORM.sql.subclauses.WhereClause;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import static itAcademy.ORM.testData.Connection.*;
import static junit.framework.TestCase.assertEquals;

public class QuerySelectWithWhereTest {
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

        Query q = new Query(QueryType.INSERT).addTable("std");
        q.setField("last_name", "'Hurieva'");
        q.setField("first_name", "'Hanna'");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = dateFormat.parse("2019-09-06 12:00:00");
        String dateSQL = dateFormat.format(date);
        q.setField("date_of_registration", "'" + dateSQL + "'");
        q.setField("gpa", 8.0d);
        statement.execute(q.getExecutableSql());
        System.out.println(q.getExecutableSql());

        Query q1 = new Query(QueryType.INSERT).addTable("std");
        q1.setField("last_name", "'Dyominov'");
        q1.setField("first_name", "'Ivan'");
        date = dateFormat.parse("2019-09-06 14:00:00");
        dateSQL = dateFormat.format(date);
        q1.setField("date_of_registration", "'" + dateSQL + "'");
        q1.setField("gpa", 10.0d);
        statement.execute(q1.getExecutableSql());
        System.out.println(q1.getExecutableSql());

        Query q2 = new Query(QueryType.INSERT).addTable("std");
        q2.setField("last_name", "'Dyominov'");
        q2.setField("first_name", "'Yulia'");
        date = dateFormat.parse("2019-09-06 10:00:00");
        dateSQL = dateFormat.format(date);
        q2.setField("date_of_registration", "'" + dateSQL + "'");
        q2.setField("gpa", 10.0d);
        statement.execute(q2.getExecutableSql());
        System.out.println(q2.getExecutableSql());
        transaction.commit();
    }

    @Test
    public void shouldSelectWithWhereFromTable() {
        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        query.addField(new DataField("last_name"));
        query.addSubclause(SubclauseType.WHERE, new WhereClause(new DataField("gpa < 10.0")));
        System.out.println(query.getExecutableSql());

        try {
            ResultSet result = statement.executeQuery(query.getExecutableSql());
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            result.last();
            assertEquals(result.getRow(), 1);

            String sql = "TRUNCATE TABLE std ";
            statement.execute(sql);
            transaction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
