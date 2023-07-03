package ru.skypro.lessons.springboot.springf.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springf.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Position;
import ru.skypro.lessons.springboot.springf.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.springf.repository.PagingEmployeeRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static ru.skypro.lessons.springboot.springf.writeReadToFile.WriteReadToFile.writeToFile;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private final PagingEmployeeRepository pagingEmployeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PagingEmployeeRepository pagingEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.pagingEmployeeRepository = pagingEmployeeRepository;
    }


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
    public void postJsonFileEmployeeRead(MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "test.json";
        writeToFile(file, filePath);
        try {
//          EmployeeFullInfo employeeFullInfo = objectMapper.readValue(new File(filePath), EmployeeFullInfo.class); // массив более 1го Ошибка(com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize value of type `ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo` from Array value (token `JsonToken.START_ARRAY`) at [Source: (File); line: 2, column: 1])
         List<EmployeeFullInfo>list = objectMapper.readValue(new File(filePath), new TypeReference<List<EmployeeFullInfo>>() {}); //массив 1го Ошибка(com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize value of type `java.util.ArrayList<ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo>` from Object value (token `JsonToken.START_OBJECT`) at [Source: (File); line: 1, column: 1])

            System.out.println(list);
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            int sizeEmployee = jsonNode.size();
            Employee employee = new Employee(jsonNode.get(1).get("id"),jsonNode.get(1).get("name"),jsonNode.get(1).get("salary"),new Position(jsonNode.get(1).get("position"));
//            System.out.println(jsonNode);





        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

