package ru.skypro.lessons.springboot.springf.controller;

import jakarta.annotation.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.springf.pojo.Report;
import ru.skypro.lessons.springboot.springf.service.EmployeeService;

import java.io.IOException;
import java.util.Optional;

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
//    public int report() {
//        return employeeService.generateReport();
//    }
    public int report() {
        return employeeService.saveJsonPath();
    }

    /**
     * GET находить и возвращать созданный ранее файл в формате JSON по идентификатору.
     */

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<?> dowloadReportId(@RequestParam Integer id) {
//        String filePath = "test.json";
//        ByteArrayResource resource = new ByteArrayResource(employeeService.generateReportId(id).get().getData().getBytes());
//
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath + "\"")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(resource);
//    }
    ///////////////////////var2/////////////////////////////////////
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> dowloadReportId(@RequestParam Integer id) {
        String filePath = "testStatistics.json";
        Optional<Report> reportsfindById = employeeService.generateReportId(id);
        ByteArrayResource resource = new ByteArrayResource();
//        ByteArrayResource resource = new ByteArrayResource(employeeService.generateReportId(id).get().getFilePath().getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}

