package ru.skypro.lessons.springboot.springf.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.springf.dto.ReportDTO;
import ru.skypro.lessons.springboot.springf.pojo.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    /**
     * GET самой высокой зарплатой
     */
    @Query(value = "SELECT * FROM employee WHERE salary > :lowBoard",
            nativeQuery = true)
    List<Employee> employeeUserHighSalary(@Param("lowBoard") Integer lowBoard);

    /**
     * GET возвращать полную информацию о сотруднике
     */

    @Query("SELECT new ru.skypro.lessons.springboot.springf.dto. " +
            "EmployeeFullInfo(e.id,e.name , e.salary , p.role) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> getFullEmployee();

    /**
     * GET возвращать информацию о сотруднике с переданным position
     */

    @Query("SELECT new ru.skypro.lessons.springboot.springf.dto. " +
            "EmployeeFullInfo(e.id,e.name , e.salary , p.role) " +
            "FROM Employee e JOIN FETCH Position p " +
            "WHERE e.position= p AND p.role = :role")
    List<EmployeeFullInfo> buPositionToEmployee(String role);

    /**
     * GET возвращать информацию о сотруднике с переданным id
     */
    @Query("SELECT new ru.skypro.lessons.springboot.springf.dto." +
            "EmployeeFullInfo(e.id,e.name , e.salary , p.role) " +
            "FROM Employee e  JOIN FETCH Position p " +
            "WHERE e.position = p AND e.id = :id")
    List<EmployeeFullInfo> buIdEmployeeINfo(int id);

//    /**
//     * POST формировать  статистикой по отделам:
//     */
//    @Query("SELECT new ru.skypro.lessons.springboot.springf.dto. " +
//            "ReportDTO(e.position,count (e.id),max(e.salary),min(e.salary),avg (e.salary))FROM Employee  e GROUP BY e .position")
//    List<ReportDTO> buildReport();
}
//        Название отдела
//        Кол-во сотрудников
//        Максимальная зарплата
//        Минимальная зарплата
//        Средняя зарплата

//
//        POST-запрос localhost:8080/report. Он должен формировать отчет по отделам. Отчет содержит в себе:
//        Название отдела
//        Кол-во сотрудников
//        Максимальная зарплата
//        Минимальная зарплата
//        Средняя зарплата
//        и сохранять этот отчет в виде массива байтов в базу данных. Метод возвращает целочисленный идентификатор отчета, сохраненного в базе данных .
