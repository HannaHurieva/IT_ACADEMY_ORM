package itAcademy.ORM.reflection;

import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.mapping.Column;
import itAcademy.ORM.mapping.Table;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionAPI {

    private static List<Table> tables = new ArrayList<>();

    public static List<Table> getAllEntities(String path) {
        Reflections ref = new Reflections(path);
        for (Class<?> cl : ref.getTypesAnnotatedWith(Entity.class)) {
            Entity findable = cl.getAnnotation(Entity.class);
            Table table = new Table(cl, findable.tableName(), getAnnotatedFields(cl));
            tables.add(table);
        }
        return tables;
    }

    private static List<Column> getAnnotatedFields(final Class<?> clazz) {
        List<Column> tableColumns = new ArrayList<>();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            try {
                if (!java.lang.reflect.Modifier.isStatic(field.getModifiers()))
                    if (field.isAnnotationPresent(itAcademy.ORM.annotations.Column.class)) {
                        tableColumns.add(new Column(field.getName(), field.
                                getAnnotation(itAcademy.ORM.annotations.Column.class).
                                fieldName(), field.getType(), false, field.
                                getAnnotation(itAcademy.ORM.annotations.Column.class).autoIncremental()));
                    } else if (field.isAnnotationPresent(Id.class)) {
                        tableColumns.add(new Column(field.getName(), field.
                                getAnnotation(Id.class).
                                fieldName(), field.getType(), true, field.
                                getAnnotation(Id.class).autoIncremental()));
                    }
            } catch (SecurityException ignored) {
            }

        }
        return tableColumns;
    }


    public static void setField(Object object, String fieldName, Object value) throws IllegalAccessException, ReflectionException {
        boolean noField = true;
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);
                field.set(object, value);
                noField = false;
            }
        }
        if (noField) {
            throw new ReflectionException("No field");
        }
    }

    public static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException, ReflectionException {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);
                return field.get(object);
            }
        }
        throw new ReflectionException("No field");
    }

    public static List<Table> getTables() {
        return tables;
    }
}
