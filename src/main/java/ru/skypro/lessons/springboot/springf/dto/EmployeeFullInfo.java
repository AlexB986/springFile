package ru.skypro.lessons.springboot.springf.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.skypro.lessons.springboot.springf.pojo.Position;

@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFullInfo {
    private Integer id;
    private String name;
    private Integer salary;
    private Integer position;


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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }



    @Override
    public String toString() {
        return "EmployeeFullInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", positionRole=" + position +
                '}';
    }
}
