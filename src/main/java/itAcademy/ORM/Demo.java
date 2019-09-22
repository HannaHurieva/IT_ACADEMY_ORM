package itAcademy.ORM;

import itAcademy.ORM.mapping.util.Util;

import java.sql.SQLException;

public class Demo {

    public static void main(String[] args) throws SQLException {
        Util.generateTables();
    }
}
