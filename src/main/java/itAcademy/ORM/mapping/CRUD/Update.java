package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.Field;
import itAcademy.ORM.mapping.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Update {

    private static void getUpdateFieldsFromTable(Table table, List<Field> fields, String newValue, String dbName) throws SQLException {
        String user = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=EST5EDT";
        Connection connection = DriverManager.getConnection(url, user, password);

        try (Statement preparedStatement = connection.createStatement()) {
            StringBuilder sql = new StringBuilder("UPDATE `");
            sql.append("`").append(table.getTableName()).append("` ");
            sql.append("SET `");
            for (Field field : fields) {
                sql.append("`").append(field.getFieldName()).append(" = `");
                sql.append("`").append(newValue).append(",` ");
            }
            sql.deleteCharAt(sql.lastIndexOf(","));

            System.out.println(sql.toString());

            preparedStatement.addBatch(sql.toString());

            preparedStatement.executeBatch();
        }
    }
}
