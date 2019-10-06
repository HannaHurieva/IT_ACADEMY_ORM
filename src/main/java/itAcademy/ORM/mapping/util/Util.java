package itAcademy.ORM.mapping.util;

import itAcademy.ORM.connection.connectionpool.DBCPDataSourceFactory;
import itAcademy.ORM.connection.connectionpool.DataSourceFactory;
import itAcademy.ORM.mapping.Column;
import itAcademy.ORM.mapping.Table;
import itAcademy.ORM.reflection.ReflectionAPI;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static itAcademy.ORM.crud.SQLCommands.*;

public class Util {


    private static Connection connection;

    static {
        DataSourceFactory dataSourceFactory = new DBCPDataSourceFactory();
        try {
            connection = dataSourceFactory.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void generateTables()  {
            generateTables(ReflectionAPI.getAllEntities(""));
    }

    private static void generateTables(List<Table> entities) {
        try (Statement preparedStatement = connection.createStatement()) {
            for (Table table : entities) {
                StringBuilder sql = new StringBuilder(CREATE_TABLE);
                sql.append(table.getTableName()).append("` (\n");
                for (Column column : table.getColumns()) {
                    if (column.isPK()) {
                        sql.append("`").append(column.getDbName()).append("` ")
                                .append(convertType(column.getType()));
                        if (column.isAutoincrement()) sql.append(AUTO_INCREMENT);
                        sql.append(",\n");
                        sql.append(PRIMARY_KEY)
                                .append(" (`").append(column.getDbName()).append("`)");
                        sql.append(",");
                        sql.append("\n");
                    } else {
                        sql.append("`").append(column.getDbName()).append("` ")
                                .append(convertType(column.getType()));
                        if (column.isAutoincrement()) sql.append(AUTO_INCREMENT);
                        sql.append(",\n");
                    }
                }
                sql.deleteCharAt(sql.lastIndexOf(","));
                sql.append(")");
                preparedStatement.addBatch(sql.toString());
            }
            preparedStatement.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
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
            case "class java.crud.Date":
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
            case "class java.crud.Time":
                DatabaseType = "TIME(6)";
                break;
            default:
                DatabaseType = "varchar(255)";
                break;
        }
        return DatabaseType;
    }
}
