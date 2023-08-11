package ru.skypro.lessons.springboot.springf.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;
import ru.skypro.lessons.springboot.springf.pojo.Report;
import ru.skypro.lessons.springboot.springf.service.ReportService;

@RestController
@RequestMapping("/")
public class ReportController {

  private final ReportService reportService;


  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }


  /**
   * POST формировать JSON-файл со статистикой по отделам:
   */
//  @PostMapping
//  public int report() {
//    return reportService.generateReport();
//  }
  @PostMapping
  public int reportFile() {
    return reportService.saveJsonPath();
  }

  /**
   * GET находить и возвращать созданный ранее файл в формате JSON по идентификатору.
   */

//  @GetMapping(value = "/{id}")
//  public ResponseEntity<?> dowloadReportId(@PathVariable Integer id) {
//    String fileName = "test.json";
//    Resource resource = new ByteArrayResource(
//        reportService.generateReportId(id)
//            .map(Report::getData)
//            .map(String::getBytes)
//            .orElseThrow(() -> new NotFoundException("Report not found"))
//    );
//
//    return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//        .body(resource);
//  }
  @GetMapping(value = "/{id}")
  public ResponseEntity<?> dowloadReportId(@PathVariable Integer id) {
    String fileName = "test.json";

    Resource resource = new FileSystemResource(
        reportService.generateReportId(id)
            .map(Report::getFilePath)
            .orElseThrow(() -> new NotFoundException("Report not found"))
    );

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(resource);
  }
}

