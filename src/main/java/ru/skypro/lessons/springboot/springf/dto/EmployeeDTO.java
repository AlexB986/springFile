package ru.skypro.lessons.springboot.springf.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Position;

public class EmployeeDTO {
    //     private int employeeId;
    private String name;
    private int salary;
    private PositionDTO position;

    public EmployeeDTO(String name, int salary, PositionDTO position) {
//         this.employeeId=employeeId;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public EmployeeDTO() {
    }

//     public int getEmployeeId() {
//         return employeeId;
//     }
//
//     public void setEmployeeId(int employeeId) {
//         this.employeeId = employeeId;
//     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
//                 "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", position=" + position +
                '}';
    }
}
