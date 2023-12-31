package ru.skypro.lessons.springboot.springf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.service.EmployeeService;

import java.io.IOException;
import java.util.List;
import ru.skypro.lessons.springboot.springf.service.ReportService;

@RestController
@RequestMapping("/admin")
public class AdminEmployeeController {
    private final EmployeeService employeeService;
    private final ReportService reportService;

    public AdminEmployeeController(EmployeeService employeeService, ReportService reportService) {
        this.employeeService = employeeService;
        this.reportService = reportService;
    }

    /**
     * GET  возвращать самой высокой зарплатой
     */
    @GetMapping("/withHighestSalary")
    public List<Employee> withHighestSalary(@RequestParam(value = "salary", required = false) Integer salary) {
        return employeeService.withHighestSalary(salary);
    }

    /**
     * GET возвращать информацию о сотруднике с переданным position
     */
    @GetMapping
    public List<EmployeeFullInfo> getBuIdEmployeePosition(@RequestParam("position") String position) {
        try {
            return employeeService.getBuIdEmployeePosition(position);
        } catch (Throwable t) {
            String message = "Нет такого position";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    /**
     * GET возвращать полную информацию о сотруднике
     */
    @GetMapping("/full")
    public List<EmployeeFullInfo> getFull() {
        return employeeService.getFull();
    }

    /**
     * GET возвращать информацию о сотруднике с переданным id
     */
    @GetMapping("/{id}/fullinfo")

    public List<EmployeeFullInfo> getBuIdEmployee(@PathVariable int id) {
        try {
            return employeeService.getBuIdEmployee(id);
        } catch (Throwable t) {
            String message = "Нет такого id";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    /**
     * GET возвращать информацию о сотрудниках на странице.
     */

    @GetMapping("/{page}")
    public List<Employee> getEmployeesPaging(@PathVariable int page) {
        int size = 2;
        return employeeService.getEmployeesPaging(page, size);
    }

    /**
     * POST  принимать на вход файл JSON,
     * Все сотрудники из загружаемого файла должны быть сохранены в базе данных.
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void postJsonFileEmployeeRead(@RequestParam("file") MultipartFile file) {
        try {
            reportService.postJsonFileEmployeeRead(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
