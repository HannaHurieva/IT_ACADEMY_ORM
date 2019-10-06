package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.exception.CRUDException;
import itAcademy.ORM.reflection.ReflectionException;
import itAcademy.ORM.test.TestEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static itAcademy.ORM.mapping.StaticVariables.transaction;

public class CRUDOperations {

    private TestEntity entity = new TestEntity();

    void insert() throws CRUDException {
        try {
            entity.setUsername(23);
            entity.setTitle("Insert");
            transaction.insert(entity, "test");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CRUDException();
        }
    }

    void update() throws CRUDException {
        try {
            entity.setUsername(233);
            entity.setTitle("Update");
            transaction.update(entity, 5, "test");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CRUDException();
        }
    }

    void delete() throws CRUDException {
        try {
            entity.setTitle("Delete");
            transaction.delete(entity, 9, "test");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CRUDException();
        }
    }

    void selectAll() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ReflectionException, IOException, InstantiationException {
        entity.setTitle("Select all");
        transaction.findAll(entity);
        transaction.commit();
    }
}
