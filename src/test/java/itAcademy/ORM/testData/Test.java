package itAcademy.ORM.testData;


import itAcademy.ORM.annotations.Column;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.annotations.OneToOne;

import java.util.Objects;


@Entity(tableName = "test")
public class Test {

    @Id(autoIncremental = true, fieldName = "id")
    private int id;

    @Column(fieldName = "title")
    private String title;

    @OneToOne(fieldName = "user_id", toTable = "user", toTableFieldName = "id")
    private Integer userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id &&
                userId == test.userId &&
                Objects.equals(title, test.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, userId);
    }
}
