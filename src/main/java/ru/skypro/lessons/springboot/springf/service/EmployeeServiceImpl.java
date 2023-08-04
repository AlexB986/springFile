package ru.skypro.lessons.springboot.springf.service;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springf.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.springf.dto.ReportDTO;
import ru.skypro.lessons.springboot.springf.mapper.EmployeeMaper;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Report;
import ru.skypro.lessons.springboot.springf.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.springf.repository.PagingEmployeeRepository;
import ru.skypro.lessons.springboot.springf.repository.ReportRepository;
import ru.skypro.lessons.springboot.springf.writeReadToFile.WriteReadToFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

//import static ru.skypro.lessons.springboot.springf.writeReadToFile.WriteReadToFile.dowloadFile;
import static ru.skypro.lessons.springboot.springf.writeReadToFile.WriteReadToFile.saveStatisticJsonFile;
import static ru.skypro.lessons.springboot.springf.writeReadToFile.WriteReadToFile.writeToFile;

//@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PagingEmployeeRepository pagingEmployeeRepository;



    /**
     * GET возвращать информацию о сотруднике с переданным id
     */

    @Override
    public List<EmployeeFullInfo> getBuIdEmployee(int id) {
        return employeeRepository.buIdEmployeeINfo(id);
    }

    /**
     * GET самой высокой зарплатой
     */
    @Override
    public List<Employee> withHighestSalary(Integer lowBoard) {
        return employeeRepository.employeeUserHighSalary(lowBoard);
    }


    /**
     * GET возвращать информацию о сотруднике с переданным position
     */
    @Override
    public List<EmployeeFullInfo> getBuIdEmployeePosition(String role) {
        return employeeRepository.buPositionToEmployee(role);
    }

    /**
     * GET возвращать полную информацию о сотруднике
     */
    @Override
    public List<EmployeeFullInfo> getFull() {

        return employeeRepository.getFullEmployee();
    }


    /**
     * GET возвращать информацию о сотрудниках на странице.
     */
    @Override
    public List<Employee> getEmployeesPaging(int page, int size) {
        Pageable employeeOfConcretePage = PageRequest.of(page, size);
        Page<Employee> employeePage = pagingEmployeeRepository.findAll(employeeOfConcretePage);
        return employeePage.stream().toList();
    }






}

