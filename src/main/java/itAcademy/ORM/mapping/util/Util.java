package itAcademy.ORM.mapping.util;

import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.mapping.Field;
import itAcademy.ORM.mapping.Table;
import itAcademy.ORM.reflection.ReflectionAPI;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class Util {

    public static void generateTables() throws SQLException {
        createDB("test");
        generateTables(ReflectionAPI.getAllEntities(""), "test");
        generateOneToOneDependency((Map<Table, Table>) ReflectionAPI.getAllEntities(""),"test");
    }

    private static void createDB(String dbName) throws SQLException {
        String user = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/?serverTimezone=EST5EDT";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
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

    private static void generateTables(List<Table> entities, String dbName) throws SQLException {
        String user = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=EST5EDT";
        Connection connection = DriverManager.getConnection(url, user, password);
        try (Statement preparedStatement = connection.createStatement()) {
            for (Table table : entities) {
                StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS `");
                sql.append(table.getTableName()).append("` (\n");
                for (Field field : table.getFields()) {
                    if (field.isPrimaryKey()) {


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

    private static void generateOneToOneDependency(Map<Table, Table> targetEntities, String dbName) throws SQLException {
        String user = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=EST5EDT";
        Connection connection = DriverManager.getConnection(url, user, password);
        try (Statement preparedStatement = connection.createStatement()) {
            for (Map.Entry<Table, Table> entry : targetEntities.entrySet()) {
                Object firstTable = entry.getKey();
                Object secondTable = entry.getValue();
                StringBuilder sql = new StringBuilder("ALTER TABLE ");
                sql.append(((Table) firstTable).getTableName());
                for (Field field : ((Table) firstTable).getFields()) {
                    if (field.isForeignKey()) {
                        sql.append("`ADD FOREIGN KEY (`").append(((Table) secondTable).getTableName()).append("`) REFERENCES(`").append(((Table) firstTable).getFields()).append(")`");

                    }
                }
                System.out.println(sql.toString());
                preparedStatement.addBatch(sql.toString());
            }
            preparedStatement.executeBatch();
        }

    }
}
