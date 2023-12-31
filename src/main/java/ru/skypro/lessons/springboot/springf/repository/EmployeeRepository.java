package ru.skypro.lessons.springboot.springf.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.springf.dto.ReportDTO;
import ru.skypro.lessons.springboot.springf.pojo.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /**
     * GET самой высокой зарплатой
     */
    @Query(value = "SELECT * FROM employee WHERE salary > :lowBoard",
            nativeQuery = true)
    List<Employee> employeeUserHighSalary(@Param("lowBoard") Integer lowBoard);

    /**
     * GET возвращать полную информацию о сотруднике
     */

    @Query(value = "SELECT new ru.skypro.lessons.springboot.springf.dto. " +
            "EmployeeFullInfo(e.employeeId, e.name , e.salary , p.role) " +
            "FROM Employee e join e.position p")
    List<EmployeeFullInfo> getFullEmployee();

    /**
     * GET возвращать информацию о сотруднике с переданным position
     */

    @Query("SELECT new ru.skypro.lessons.springboot.springf.dto. " +
            "EmployeeFullInfo(e.employeeId,e.name , e.salary , p.role) " +
            "FROM Employee e JOIN e.position p " +
            "WHERE p.role = :role")
    List<EmployeeFullInfo> buPositionToEmployee(String role);

    /**
     * GET возвращать информацию о сотруднике с переданным id
     */
    @Query("SELECT new ru.skypro.lessons.springboot.springf.dto." +
            "EmployeeFullInfo(e.employeeId,e.name , e.salary , p.role) " +
            "FROM Employee e  JOIN e.position p " +
            "WHERE e.employeeId = :id")
    List<EmployeeFullInfo> buIdEmployeeINfo(int id);

}