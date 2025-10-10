package org.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String department;
    private double salary; // 🟢 thêm dòng này

    @Column(name = "image_url")
    private String imageUrl; // chỉ lưu URL ảnh (https)

    public Employee() {}

    public Employee(int id, String name, String department, double salary, String imageUrl) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.imageUrl = imageUrl;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
