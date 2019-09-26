package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.Field;
import itAcademy.ORM.mapping.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Insert {

    private static void getInsert(List<Table> entities, List<Field> fields, String valueOfField, String dbName) throws SQLException {
        String user = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=EST5EDT";
        Connection connection = DriverManager.getConnection(url, user, password);

        try (Statement preparedStatement = connection.createStatement()) {
            for (Table table : entities) {
                StringBuilder sql = new StringBuilder("INSERT INTO `");
                sql.append(table.getTableName()).append("`(");
                for (Field field : fields) {
                    sql.append("`").append(field.getFieldName()).append(")` ");
                    sql.append("`VALUES (`").append(valueOfField).append(")`");
                }

                System.out.println(sql.toString());

                preparedStatement.addBatch(sql.toString());
            }
            preparedStatement.executeBatch();
        }
    }
}
