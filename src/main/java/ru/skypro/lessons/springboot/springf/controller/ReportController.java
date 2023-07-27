package ru.skypro.lessons.springboot.springf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.springf.service.EmployeeService;

import java.io.IOException;

@RestController
@RequestMapping("report")
public class ReportController {
    private static EmployeeService employeeService;
    public ReportController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * POST формировать JSON-файл со статистикой по отделам:
     */
    @PostMapping(value = "")
    public int report(){
        return employeeService.generateReport();
    }
    /**
     *GET
     *  находить и возвращать созданный ранее файл в формате JSON по идентификатору.
     */
//    @GetMapping("/report/{id}")
}
