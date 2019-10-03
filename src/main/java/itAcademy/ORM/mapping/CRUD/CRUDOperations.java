package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.reflection.ReflectionException;
import itAcademy.ORM.test.TestEntity;
import itAcademy.ORM.transaction.BaseTransaction;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static itAcademy.ORM.mapping.StaticVariables.basicDataSource;
import static itAcademy.ORM.mapping.StaticVariables.transaction;

public class CRUDOperations {

    private TestEntity entity;

    private void permanentDeleteChanges() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException {
        transaction.delete(entity);
        transaction.commit();
    }

    void insert() throws InvocationTargetException, SQLException, ReflectionException, NoSuchMethodException, IllegalAccessException {
        transaction = new BaseTransaction(basicDataSource.getConnection());
        entity = new TestEntity();
        entity.setId(2);
        entity.setUsername(12);
        entity.setTitle("insert");
        transaction.insert(entity);
        transaction.commit();
        permanentDeleteChanges();
    }

    public void update() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ReflectionException, IOException, InstantiationException {
        entity.setTitle("update");
        transaction.update(entity);
        transaction.commit();
        permanentDeleteChanges();
    }


}
