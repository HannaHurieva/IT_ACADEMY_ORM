package itAcademy.ORM.mapping.util;

import itAcademy.ORM.connection.connectionpool.DBCPDataSourceFactory;
import itAcademy.ORM.connection.connectionpool.DataSourceFactory;
import itAcademy.ORM.mapping.Field;
import itAcademy.ORM.mapping.Table;
import itAcademy.ORM.reflection.ReflectionAPI;
import itAcademy.ORM.support.DaoSupport;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Util {


    private static Connection connection;

    static {
        DataSourceFactory dataSourceFactory = new DBCPDataSourceFactory();
        connection = DaoSupport.getConnection(dataSourceFactory.getDataSource());
    }

    public static void generateTables() throws SQLException {
        generateTables(ReflectionAPI.getAllEntities(""));
    }


    private static void generateTables(List<Table> entities) throws SQLException {
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
