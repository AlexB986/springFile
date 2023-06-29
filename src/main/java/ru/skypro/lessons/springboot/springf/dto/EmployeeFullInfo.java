package ru.skypro.lessons.springboot.springf.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFullInfo {
    private Integer id;
    private String name;
    private Integer salary;
    private Integer positionRole;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getPositionRole() {
        return positionRole;
    }

    public void setPositionRole(Integer positionRole) {
        this.positionRole = positionRole;
    }

    @Override
    public String toString() {
        return "EmployeeFullInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", positionRole=" + positionRole +
                '}';
    }
}
