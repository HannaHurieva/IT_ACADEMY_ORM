package itAcademy.ORM.testData;

import itAcademy.ORM.annotations.Column;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;

import java.util.Date;

@Entity(tableName = "std")
public class Student {
    @Id(fieldName = "std_id", autoIncremental = true)
    private int id;
    @Column(fieldName = "last_name")
    private String lastName;
    @Column(fieldName = "first_name")
    private String firstName;
    @Column(fieldName = "date_of_registration")
    private Date registrationDate;
    @Column(fieldName = "gpa")
    private Double gpa;

    public Student() {
    }

    public Student(int id, String lastName, Date registrationDate, Double gpa) {
        this.id = id;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", registrationDate=" + registrationDate +
                ", gpa=" + gpa +
                '}';
    }
}
