package ru.skypro.lessons.springboot.springf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springf.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springf.dto.ReportDTO;
import ru.skypro.lessons.springboot.springf.mapper.EmployeeMaper;
import ru.skypro.lessons.springboot.springf.pojo.Report;
import ru.skypro.lessons.springboot.springf.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.springf.repository.ReportRepository;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static ru.skypro.lessons.springboot.springf.writeReadToFile.WriteReadToFile.saveStatisticJsonFile;
import static ru.skypro.lessons.springboot.springf.writeReadToFile.WriteReadToFile.writeToFile;
@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService{
    private final EmployeeMaper employeeMaper;
    private final ObjectMapper objectMapper;
    private final ReportRepository reportRepository;
    private final EmployeeRepository employeeRepository;


    /**
     * POST  принимать на вход файл JSON,
     * Все сотрудники из загружаемого файла должны быть сохранены в базе данных.
     */


    @Override
    public void postJsonFileEmployeeRead(MultipartFile file) {
        writeToFile(file);
        String filePath = "test.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<EmployeeDTO> listEmployeeDto = objectMapper.readValue(Paths.get(filePath).toFile(), new TypeReference<>() {
            });
            System.out.println(listEmployeeDto);
            listEmployeeDto.stream()
                    .map(employeeMaper::toEntity)
                    .forEach(employeeRepository::save);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Сформированный отчет необходимо далее сохранить в виде файла на вашем компьютере.
     * В базу данных вы должны сохранять путь к сформированному отчету.
     * Метод возвращает целочисленный идентификатор строки в таблице report
     */
    @Override
    public int saveJsonPath() {
        List<ReportDTO> report = reportRepository.buildReport();
        String filePath = "testStatistic.json";

        try {
            String statistic = objectMapper.writeValueAsString(report);
            System.out.println(statistic);
            saveStatisticJsonFile(statistic,filePath);

            Report reportPath = new Report();

            reportPath.setFilePath(String.valueOf(Paths.get(filePath)));

            return reportRepository.save(reportPath).getReportId();
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error", e);
        }
    }
    /**
     * GET-запрос
     * localhost:8080/report/{id}
     *  должен находить и возвращать созданный ранее файл в формате json по переданному уникальному идентификатору,
     *  используя сохраненный ранее в базу данных путь в файловой системе.
     */
    @Override
    public Optional<Report> generateReportId(Integer id) {
        Optional<Report> reportsfindById = reportRepository.findById(id);
        return reportsfindById;
    }



    /**
     * POST формировать  статистикой по отделам:
     */
//    public int generateReport() {
//        List<ReportDTO> report = reportRepository.buildReport();
//        try {
//            String statistic = objectMapper.writeValueAsString(report);
//
//            Report reportEntity = new Report();
//            reportEntity.setData(statistic);
//            return reportRepository.save(reportEntity).getReportId();
//        } catch (JsonProcessingException e) {
//            throw new IllegalStateException("Error", e);
//        }
//
//    }


    /**
     * GET
     * находить и возвращать созданный ранее файл в формате JSON по идентификатору.
     */
//    @Override
//    public Optional<Report> generateReportId(Integer id) {
//        Optional<Report> reportsfindById = reportRepository.findById(id);
//        return reportsfindById;
//    }
}
