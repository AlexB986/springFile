package ru.skypro.lessons.springboot.springf.service;


import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Report;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {


    /**
     * GET возвращать информацию о сотруднике с переданным id
     */
    List<EmployeeFullInfo> getBuIdEmployee(int id);


    /**
     * GET  возвращать самой высокой зарплатой
     */
    List<Employee> withHighestSalary(Integer salary);

    /**
     * GET возвращать информацию о сотруднике с переданным position
     */
    List<EmployeeFullInfo> getBuIdEmployeePosition(String role);

    /**
     * GET возвращать информацию о сотрудниках на странице.
     */
    List<Employee> getEmployeesPaging(int page, int size);

    /**
     * GET возвращать полную информацию о сотруднике
     */
    List<EmployeeFullInfo> getFull();



}
