package itAcademy.ORM.test;

import itAcademy.ORM.annotations.Constraint;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Field;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.mapping.Reference;

@Entity(tableName = "user")
public class User {

    @Id(autoIncremental = true, fieldName = "id")
    private int id;

    @Constraint(referenceType = Reference.ONE_TO_MANY, fieldName = "test.id")
    @Field(fieldName = "name")
    private int name;

    public User(int id, int name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
