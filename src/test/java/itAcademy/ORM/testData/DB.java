package itAcademy.ORM.testData;

import itAcademy.ORM.annotations.Column;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;
import itAcademy.ORM.annotations.OneToMany;

import java.util.List;

@Entity(tableName = "db")
public class DB {
    @Id(autoIncremental = false)
    private String id;


    @Column(fieldName = "name")
    private String name;

    @OneToMany(fieldName = "users", toTable = "user", toTableFieldName = "id")
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
