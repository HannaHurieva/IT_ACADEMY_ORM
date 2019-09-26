package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.connection.BaseDataSourceFactory;
import itAcademy.ORM.connection.DataSourceFactory;
import itAcademy.ORM.mapping.Field;
import itAcademy.ORM.mapping.Table;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Select {

    private static Connection connection;

    static {
        DataSourceFactory dataSourceFactory = new BaseDataSourceFactory();
        try {
            connection = dataSourceFactory.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getSelectAllFromTable(List<Table> entities, List<Field> fields, String dbName) throws SQLException {

        try (Statement preparedStatement = connection.createStatement()) {
            StringBuilder sql = new StringBuilder("SELECT *`");
            for (Table table : entities) {
                sql.append("FROM `").append(table.getTableName()).append("`");
            }

            System.out.println(sql.toString());

            preparedStatement.addBatch(sql.toString());

            preparedStatement.executeBatch();
        }
    }

    private static void getSelectFieldsFromTable(Table table, List<Field> fields, String dbName) throws SQLException {

        try (Statement preparedStatement = connection.createStatement()) {
            StringBuilder sql = new StringBuilder("SELECT `");

            for (Field field : fields) {
                sql.append(field.getFieldName()).append(", `");
            }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append("FROM `").append(table.getTableName()).append("`");

            System.out.println(sql.toString());

            preparedStatement.addBatch(sql.toString());

            preparedStatement.executeBatch();
        }
    }
}
