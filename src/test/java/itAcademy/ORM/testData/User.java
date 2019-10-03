package itAcademy.ORM.testData;

import itAcademy.ORM.annotations.Constraint;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Column;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.mapping.Reference;

@Entity(tableName = "user")
public class User {

    @Id(autoIncremental = true, fieldName = "id")
    private int id;

    @Column(fieldName = "name")
    private String name;

    @Constraint(referenceType = Reference.ONE_TO_MANY, fieldName = "test.id")
    @Column(fieldName = "test")
    private int test;

    public User() {
    }

    public User(int id, String name, int test) {
        this.id = id;
        this.name = name;
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
