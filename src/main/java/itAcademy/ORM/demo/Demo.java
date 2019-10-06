package itAcademy.ORM.demo;

import itAcademy.ORM.connection.connectionpool.DBCPDataSourceFactory;
import itAcademy.ORM.connection.support.DaoSupport;
import itAcademy.ORM.connection.transaction.BaseTransaction;
import itAcademy.ORM.crud.CrudOperationsImpl;
import itAcademy.ORM.demo.entities.Student;
import itAcademy.ORM.mapping.util.Util;
import itAcademy.ORM.sql.DataField;
import itAcademy.ORM.sql.Query;
import itAcademy.ORM.sql.QueryBuilder;
import itAcademy.ORM.sql.QueryType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Demo {
    public static void main(String[] args) throws ParseException, SQLException {
        DBCPDataSourceFactory dataSource = new DBCPDataSourceFactory();
        Connection connection = DaoSupport.getConnection(dataSource.getDataSource());
        BaseTransaction transaction = new BaseTransaction(connection);
        transaction.open();

        Util.generateTables();
        CrudOperationsImpl crud = new CrudOperationsImpl();

        /*Student student = new Student();
        student.setFirstName("Hanna");
        student.setLastName("Hurieva");
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        student.setRegistrationDate(date.parse("06/09/2019"));
        student.setGpa(8.00);
        crud.insert(student);
        transaction.commit();

        Student student1 = new Student();
        student1.setFirstName("Ivan");
        student1.setLastName("Dyominov");
        student1.setRegistrationDate(date.parse("06/09/2019"));
        student1.setGpa(10.0);
        crud.insert(student1);
        transaction.commit();

        Student student2 = new Student();
        student2.setFirstName("Yulia");
        student2.setLastName("Dolenko");
        student2.setRegistrationDate(date.parse("06/09/2019"));
        student2.setGpa(10.0);
        crud.insert(student2);
        transaction.commit();*/

        /*Statement statement = connection.createStatement();
        Query query = new Query(QueryType.SELECT);
        query.addTable("std");
        DataField field = new DataField("*");
        query.addField(field);

        System.out.println(query.getExecutableSql());

        ResultSet result = statement.executeQuery(query.getExecutableSql());
        while (result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2) +
                    " " + result.getString(3) + " " + result.getString(4)
                    + " " + result.getString(5));
        }*/

        /*DataField field2 = new DataField("first_name");
        Query q = new Query(QueryType.INSERT).addTable("std");
        q.setField("last_name", "'Hurieva'");
        q.setField("first_name","'Hanna'");
        q.setField("gpa", 10.0);
        System.out.println(q.getExecutableSql());
        statement.execute(q.getExecutableSql());*/

        transaction.close();
    }
}
