package itAcademy.ORM.transaction;

import itAcademy.ORM.mapping.CRUD.Command;
import itAcademy.ORM.mapping.CRUD.InsertCommand;
import itAcademy.ORM.mapping.util.Util;
import itAcademy.ORM.reflection.ReflectionException;
import itAcademy.ORM.test.TestEntity;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TransactionTest {

    @Test
    public void insertTest() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException, IOException, InstantiationException {
        Command command = new InsertCommand();
        command.execute();
    }
}
