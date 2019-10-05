package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.reflection.ReflectionException;
import itAcademy.ORM.test.TestEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static itAcademy.ORM.mapping.StaticVariables.transaction;

public class CRUDOperations {

    private TestEntity entity = new TestEntity();

    //todo please add working implementation

    void insert() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException {
        entity.setId(5);
        entity.setUsername(12);
        entity.setTitle("insert");
        transaction.insert(entity);
        transaction.commit();
    }

    void update() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ReflectionException {
        entity.setUsername(111);
        entity.setTitle("update");
        transaction.update(entity, 3);
        transaction.commit();
    }

    void delete() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ReflectionException {
        entity.setTitle("delete");
        transaction.delete(entity);
        transaction.commit();
    }

    void selectAll() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ReflectionException, IOException, InstantiationException {
        entity.setTitle("Select all");
        transaction.findAll(entity);
        transaction.commit();
    }
}
