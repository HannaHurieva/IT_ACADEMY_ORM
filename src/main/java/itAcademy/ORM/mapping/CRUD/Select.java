package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.Field;
import itAcademy.ORM.mapping.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Select {

    private static void getSelectOneFieldFromTable(List<Table> entities, List<Field> fields, String dbName) throws SQLException {
        String user = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=EST5EDT";
        Connection connection = DriverManager.getConnection(url, user, password);

        try (Statement preparedStatement = connection.createStatement()) {
            StringBuilder sql = new StringBuilder("SELECT `");
            for (Field field : fields) {
                sql.append(field.getFieldName()).append("`");
                for (Table table : entities) {
                    sql.append("FROM `").append(table.getTableName()).append("`");
                }

                System.out.println(sql.toString());

                preparedStatement.addBatch(sql.toString());
            }
            preparedStatement.executeBatch();
        }
    }
}
