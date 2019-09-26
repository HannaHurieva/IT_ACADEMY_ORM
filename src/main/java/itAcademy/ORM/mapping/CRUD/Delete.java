package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {

    private static void getDeleteAllFromTable(Table table, String dbName) throws SQLException {
        String user = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=EST5EDT";

        Connection connection = DriverManager.getConnection(url, user, password);
        try (Statement preparedStatement = connection.createStatement()) {
            StringBuilder sql = new StringBuilder("DELETE FROM `");
            sql.append(table.getTableName()).append("`");

            System.out.println(sql.toString());

            preparedStatement.addBatch(sql.toString());

            preparedStatement.executeBatch();
        }
    }
}
