package itAcademy.ORM.reflection;

import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.annotations.ManyToOne;
import itAcademy.ORM.annotations.OneToOne;
import itAcademy.ORM.mapping.Column;
import itAcademy.ORM.mapping.Reference;
import itAcademy.ORM.mapping.Table;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
                if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    fillTableColumnFromField(tableColumns, field);
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

    private static void fillTableColumnFromField(List<Column> tableColumns, Field field) {
        Reference reference = null;
        String fieldName = "";
        boolean isPK = false;
        boolean isFK = false;
        boolean autoIncremental = false;
        if (field.isAnnotationPresent(itAcademy.ORM.annotations.Column.class)) {
            fieldName = field.getAnnotation(itAcademy.ORM.annotations.Column.class).fieldName();
            autoIncremental = field.getAnnotation(itAcademy.ORM.annotations.Column.class).autoIncremental();
        } else if (field.isAnnotationPresent(Id.class)) {
            fieldName = field.getAnnotation(Id.class).fieldName();
            autoIncremental = field.getAnnotation(Id.class).autoIncremental();
            isPK = true;
        } else if (field.isAnnotationPresent(OneToOne.class)) {
            reference = getReferenceOneToOne(field);
            fieldName = field.getAnnotation(OneToOne.class).fieldName();
            autoIncremental = field.getAnnotation(OneToOne.class).autoIncremental();
            isFK = true;
        } else if (field.isAnnotationPresent(ManyToOne.class)) {
            reference = getReferenceManyToOne(field);
            fieldName = field.getAnnotation(ManyToOne.class).fieldName();
            autoIncremental = field.getAnnotation(ManyToOne.class).autoIncremental();
            isFK = true;
        }
        tableColumns.add(new Column(field.getName(), fieldName, field.getType(),
                isPK, isFK, autoIncremental, reference));
    }

    private static Reference getReferenceOneToOne(Field field) {
        return new Reference(field.getAnnotation(OneToOne.class).fieldName(),
                field.getAnnotation(OneToOne.class).toTable(),
                field.getAnnotation(OneToOne.class).toTableFieldName());
    }

    private static Reference getReferenceManyToOne(Field field) {
        return new Reference(field.getAnnotation(ManyToOne.class).fieldName(),
                field.getAnnotation(ManyToOne.class).toTable(),
                field.getAnnotation(ManyToOne.class).toTableFieldName());
    }

}
