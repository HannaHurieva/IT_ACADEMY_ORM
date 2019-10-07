package itAcademy.ORM.testData;

import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Column;
import itAcademy.ORM.annotations.Id;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "user")
public class User implements Serializable {

    @Id(autoIncremental = true, fieldName = "id")
    private int id;

    @Column(fieldName = "name")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                test == user.test &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, test);
    }
}
