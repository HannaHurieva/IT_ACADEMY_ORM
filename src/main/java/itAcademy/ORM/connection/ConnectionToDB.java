package itAcademy.ORM.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {

    // Блок объявления констант
    public static final String DB_URL = "jdbc:mysql://localhost:3306/database";
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "password";

    // Инициализация
    public ConnectionToDB() throws ClassNotFoundException {
        Class.forName(DB_Driver);
    }

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
}
