package itAcademy.ORM.reflection;

import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.annotations.OneToOne;
import itAcademy.ORM.mapping.Field;
import itAcademy.ORM.mapping.Table;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.*;

public class ReflectionAPI {

    private static List<Table> tables = new ArrayList<>();
    private static Map<Object, Object> oneToOneReference = new HashMap<Object, Object>();

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
                    } else if (annotation.annotationType().equals(itAcademy.ORM.annotations.Field.class)) {
                        field.setAccessible(true);
                        fields.put(field.getName(), field.get(obj));
                    }
                }
            }
        }
        return fields;
    }

    public static Map<Object, Object> getOneToOneDependency(String path, Object obj) {

        Map<Object, Object> tablesReference = new LinkedHashMap<>();
        Reflections reflection = new Reflections(path);
        String primaryKey = null;
        Object primaryKeyValue = null;
        for (Class<?> valueAnotherTable : reflection.getTypesAnnotatedWith(OneToOne.class)) {
            OneToOne findable = valueAnotherTable.getAnnotation(OneToOne.class);
            for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
                if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    for (Annotation annotation : field.getDeclaredAnnotations()) {
                        if (annotation.annotationType().equals(Id.class)) {
                            primaryKey = field.getName();
                            field.setAccessible(true);
                            try {
                                primaryKeyValue = field.get(obj);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else if (annotation.annotationType().equals(itAcademy.ORM.annotations.Field.class)) {
                            field.setAccessible(true);
                            try {

                                ReflectionAPI.oneToOneReference.put(field.get(obj), field.get(obj));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return tablesReference;
    }

    public static List<Table> getTables() {
        return tables;
    }
}
