package ru.skypro.lessons.springboot.springf.service;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.springf.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.repository.EmployeeRepository;

//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    Logger logger = LoggerFactory .getLogger(EmployeeServiceImpl.class);

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * GET возвращать информацию о сотруднике с переданным id
     */

    @Override
    public List<EmployeeFullInfo> getBuIdEmployee(int id) {
        logger.info("Вызов  работника номер "+id);
        return employeeRepository.buIdEmployeeINfo(id);
    }

    /**
     * GET самой высокой зарплатой
     */
    @Override
    public List<Employee> withHighestSalary(Integer lowBoard) {
        logger.info("Вызов работников с зарплатой больше" + lowBoard);
        return employeeRepository.employeeUserHighSalary(lowBoard);
    }


    /**
     * GET возвращать информацию о сотруднике с переданным position
     */
    @Override
    public List<EmployeeFullInfo> getBuIdEmployeePosition(String role) {
        return employeeRepository.buPositionToEmployee(role);
    }

    /**
     * GET возвращать полную информацию о сотруднике
     */
    @Override
    public List<EmployeeFullInfo> getFull() {

        logger.info("Вызов всех работников");
        return employeeRepository.getFullEmployee();
    }


    /**
     * GET возвращать информацию о сотрудниках на странице.
     */
    @Override
    public List<Employee> getEmployeesPaging(int page, int size) {
        Pageable employeeOfConcretePage = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAll(employeeOfConcretePage);
        return employeePage.stream().toList();
    }






}

