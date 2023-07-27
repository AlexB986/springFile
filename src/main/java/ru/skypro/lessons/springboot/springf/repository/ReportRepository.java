package ru.skypro.lessons.springboot.springf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.springf.dto.ReportDTO;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Report;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report,Integer> {

    /**
     * POST формировать JSON-файл со статистикой по отделам:
     */
    @Query("SELECT new ru.skypro.lessons.springboot.springf.dto. " +
            "ReportDTO(e.position,count (e.id),max(e.salary),min(e.salary),avg (e.salary))FROM Employee  e GROUP BY e .position")
    List<ReportDTO> buildReport();
}