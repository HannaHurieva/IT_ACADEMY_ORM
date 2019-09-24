package itAcademy.ORM.test;


import itAcademy.ORM.annotations.Constraint;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Field;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.mapping.Reference;

@Entity(tableName = "test")
public class Test {

    @Id(autoIncremental = true, fieldName = "id")
    private int id;

    @Field(fieldName = "title")
    private String title;

    @Constraint(referenceType = Reference.ONE_TO_MANY, fieldName = "user.id")
    @Field(fieldName = "username")
    private int username;

    public Test() {
    }

    public Test(int id, String title, int username) {
        this.id = id;
        this.title = title;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }
}
