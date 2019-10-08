package itAcademy.ORM.testData;

import itAcademy.ORM.annotations.Column;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.annotations.ManyToOne;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {

    @Id(autoIncremental = false, fieldName = "id")
    private String id;

    @Column(fieldName = "name")
    private String name;

    @Column(fieldName = "test")
    private int test;

    @ManyToOne(fieldName = "db_id", toTable = "db", toTableFieldName = "id")
    private String dbId;

    public User() {
    }

    public User(String id, String name, int test, String usersId) {
        this.id = id;
        this.name = name;
        this.test = test;
        this.dbId = usersId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getUsersId() {
        return dbId;
    }

    public void setUsersId(String usersId) {
        this.dbId = usersId;
    }
}
