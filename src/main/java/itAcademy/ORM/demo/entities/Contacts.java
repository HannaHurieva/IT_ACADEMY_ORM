package itAcademy.ORM.demo.entities;

import itAcademy.ORM.annotations.Column;
import itAcademy.ORM.annotations.Entity;
import itAcademy.ORM.annotations.Id;

@Entity(tableName = "contacts")
public class Contacts {
    @Id()
    private int id;
    @Column(fieldName = "address")
    private String address;
    @Column(fieldName = "phone")
    private String phone;
    @Column(fieldName = "email")
    private String email;

    //@OneToOne(referencedColumnName = "std_id")
    private Student student;

    public Contacts() {
    }

    public Contacts(int id, String address, String phone, String email, Student student) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", student=" + student +
                '}';
    }
}
