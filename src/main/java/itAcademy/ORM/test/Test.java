package itAcademy.ORM.test;

import itAcademy.ORM.annotations.*;
import itAcademy.ORM.mapping.Reference;

@Entity(tableName = "test")
public class Test {

    @Id(autoIncremental = true, fieldName = "id")
    private int id;

    @Constraint(referenceType = Reference.ONE_TO_MANY, fieldName = "user.id")
    @Field(fieldName = "name")
    private int name;

    public Test(int id, int name) {
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
