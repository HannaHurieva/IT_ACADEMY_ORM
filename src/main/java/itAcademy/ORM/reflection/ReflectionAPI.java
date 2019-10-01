package itAcademy.ORM.reflection;

import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.mapping.Field;
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

    private static List<Field> getAnnotatedFields(final Class<?> clazz) {
        List<Field> tableFields = new ArrayList<>();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            try {
                if (!java.lang.reflect.Modifier.isStatic(field.getModifiers()))
                    if (field.isAnnotationPresent(itAcademy.ORM.annotations.Field.class)) {
                        tableFields.add(new Field(field.getName(), field.
                                getAnnotation(itAcademy.ORM.annotations.Field.class).
                                fieldName(), field.getType(), false, field.
                                getAnnotation(itAcademy.ORM.annotations.Field.class).autoIncremental()));
                    } else if (field.isAnnotationPresent(Id.class)) {
                        tableFields.add(new Field(field.getName(), field.
                                getAnnotation(Id.class).
                                fieldName(), field.getType(), true, field.
                                getAnnotation(Id.class).autoIncremental()));
                    }
            } catch (SecurityException ignored) {
            }

        }
        return tableFields;
    }

    public static List<Table> getTables() {
        return tables;
    }
}
