package itAcademy.ORM.mapping.util;

import itAcademy.ORM.connection.BaseDataSourceFactory;
import itAcademy.ORM.connection.DataSourceFactory;
import itAcademy.ORM.mapping.Field;
import itAcademy.ORM.mapping.Table;
import itAcademy.ORM.reflection.ReflectionAPI;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Util {


    private static Connection connection;

    static {
        DataSourceFactory dataSourceFactory = new BaseDataSourceFactory();
        try {
            connection = dataSourceFactory.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void generateTables() throws SQLException {
        createDB("test");
        generateTables(ReflectionAPI.getAllEntities(""), "test");
    }

    private static void createDB(String dbName) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
    }

    private static void generateTables(List<Table> entities, String dbName) throws SQLException {
        try (Statement preparedStatement = connection.createStatement()) {
            for (Table table : entities) {
                StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS `");
                sql.append(table.getTableName()).append("` (\n");
                for (Field field : table.getFields()) {
                    if (field.isPK()) {
                        sql.append("`").append(field.getDbName()).append("` ").append(convertType(field.getType()));
                        if (field.isAutoincrement()) sql.append(" AUTO_INCREMENT");
                        sql.append(",\n");
                        sql.append("PRIMARY KEY (`").append(field.getDbName()).append("`)");
                        sql.append(",");
                        sql.append("\n");
                    } else {
                        sql.append("`").append(field.getDbName()).append("` ").append(convertType(field.getType()));
                        if (field.isAutoincrement()) sql.append(" AUTO_INCREMENT");
                        sql.append(",\n");
                    }
                }
                sql.deleteCharAt(sql.lastIndexOf(","));
                sql.append(")");
                System.out.println(sql.toString());
                preparedStatement.addBatch(sql.toString());
            }
            preparedStatement.executeBatch();
        }
    }

    private static String convertType(Type type) {
        String DatabaseType;
        switch (type.toString()) {
            case "class java.math.BigDecimal":
                DatabaseType = "decimal(65)";
                break;
            case "class java.lang.Long":
            case "long":
                DatabaseType = "BIGINT(255)";
                break;
            case "class java.lang.Short":
            case "short":
                DatabaseType = "SMALLINT(255)";
                break;
            case "class java.lang.Float":
            case "float":
                DatabaseType = "FLOAT";
                break;
            case "class java.util.Date":
            case "class java.sql.Date":
                DatabaseType = "DATETIME";
                break;
            case "class java.lang.Boolean":
            case "boolean":
                DatabaseType = "BINARY(1)";
                break;
            case "class java.lang.Integer":
            case "int":
                DatabaseType = "INT";
                break;
            case "class java.sql.Time":
                DatabaseType = "TIME(6)";
                break;
            default:
                DatabaseType = "varchar(255)";
                break;
        }
        return DatabaseType;
    }
}
