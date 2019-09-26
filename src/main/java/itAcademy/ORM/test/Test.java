package itAcademy.ORM.test;


import itAcademy.ORM.annotations.Constraint;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Column;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.mapping.Reference;

import java.util.Objects;


@Entity(tableName = "test")
public class Test {

    @Id(autoIncremental = true, fieldName = "id")
    private int id;

    @Column(fieldName = "title")
    private String title;

    @Constraint(referenceType = Reference.ONE_TO_MANY, fieldName = "user.id")
    @Column(fieldName = "username")
    private int username;

    public Test() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id &&
                username == test.username &&
                Objects.equals(title, test.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, username);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", username=" + username +
                '}';
    }
}
