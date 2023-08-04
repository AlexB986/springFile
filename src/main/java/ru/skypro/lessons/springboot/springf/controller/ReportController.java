package ru.skypro.lessons.springboot.springf.controller;

import jakarta.annotation.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.springf.pojo.Report;
import ru.skypro.lessons.springboot.springf.service.EmployeeService;
import ru.skypro.lessons.springboot.springf.service.ReportService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;


    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    /**
     * POST формировать JSON-файл со статистикой по отделам:
     */
    @PostMapping
//    public int report() {
//        return employeeService.generateReport();
//    }
    public int report() {
        return reportService.saveJsonPath();
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
        Optional<Report> reportsfindById = reportService.generateReportId(id);
        try {
            File resource = new ClassPathResource(reportsfindById.get().getFilePath()).getFile();
//        ByteArrayResource resource = new ByteArrayResource(employeeService.generateReportId(id).get().getFilePath().getBytes());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(resource);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return null;
    }
}

