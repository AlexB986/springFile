package ru.skypro.lessons.springboot.springf.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.springf.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springf.dto.PositionDTO;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Position;

@Component
public class EmployeeMaper {
    public Employee toEntity(EmployeeDTO employeeDTO) {


        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setPosition_id_employee_id(employeeDTO.getPosition_id_employee_id());


        return employee;
    }


    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setPosition_id_employee_id(employee.getPosition_id_employee_id());


        return employeeDTO;
    }

}