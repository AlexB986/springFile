package ru.skypro.lessons.springboot.springf.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springf.pojo.Report;

import java.io.IOException;
import java.util.Optional;

public interface ReportService {
    /**
     * POST  принимать на вход файл JSON
     */
    void postJsonFileEmployeeRead(MultipartFile file) throws IOException;
    /**
     * POST формировать  статистикой по отделам:
     */
    int generateReport() ;

    /**
     *GET
     *  находить и возвращать созданный ранее файл в формате JSON по идентификатору.
     */
    Optional<Report> generateReportId(Integer id);
    public int saveJsonPath() ;
}
