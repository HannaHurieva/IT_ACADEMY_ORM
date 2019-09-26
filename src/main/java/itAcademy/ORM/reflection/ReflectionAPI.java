package itAcademy.ORM.reflection;

import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.mapping.Column;
import itAcademy.ORM.mapping.Table;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
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

    public static Map<String, Object> getAnnotatedFields(Object obj) throws IllegalAccessException {
        Map<String, Object> fields = new LinkedHashMap<>();
        String pkField = null;
        Object pkFieldValue = null;
        for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation.annotationType().equals(Id.class)) {
                        pkField = field.getName();
                        field.setAccessible(true);
                        pkFieldValue = field.get(obj);
                    } else if (annotation.annotationType().equals(itAcademy.ORM.annotations.Column.class)) {
                        field.setAccessible(true);
                        fields.put(field.getName(), field.get(obj));
                    }
                }
            }
        }
        return fields;
    }

    public static List<Table> getTables() {
        return tables;
    }
}
