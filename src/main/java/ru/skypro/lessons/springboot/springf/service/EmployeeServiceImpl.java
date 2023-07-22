package ru.skypro.lessons.springboot.springf.service;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springf.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Position;
import ru.skypro.lessons.springboot.springf.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.springf.repository.PagingEmployeeRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ru.skypro.lessons.springboot.springf.writeReadToFile.WriteReadToFile.writeToFile;

//@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private final PagingEmployeeRepository pagingEmployeeRepository;
    private final EmployeeMaper employeeMaper;


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

    /**
     * POST  принимать на вход файл JSON,
     * Все сотрудники из загружаемого файла должны быть сохранены в базе данных.
     */


    @Override
    public void postJsonFileEmployeeRead(MultipartFile file)  {
        writeToFile(file);
        String filePath = "test.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<EmployeeDTO> listEmployeeDto = objectMapper.readValue(Paths.get(filePath).toFile(), new TypeReference<>() {});
            System.out.println(listEmployeeDto);
            listEmployeeDto.stream()
                    .map(employeeMaper::toEntity)
                    .forEach(employeeRepository::save);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int generateReport() {
        return 0;
    }
}

