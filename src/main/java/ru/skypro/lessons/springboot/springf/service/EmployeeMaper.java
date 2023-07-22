package ru.skypro.lessons.springboot.springf.service;

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
//        employee.setPosition(new Position().getPositionId());
//        PositionDTO positionDTO = employeeDTO.getPosition();
//        Position position = new Position();
//        position.setPositionId(positionDTO.getPositionId());
//        position.setPositionId(positionDTO.getPositionId());
//        employee.setPosition(position);


        return employee;
    }
    

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
//        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());

        Position position = employee.getPosition();
//        PositionDTO positionDTO = new PositionDTO(position.getPositionId(), position.getRole());
//        PositionDTO positionDTO = new PositionDTO(position.getRole());

//        employeeDTO.setPosition(positionDTO);
        return employeeDTO;
    }

}
