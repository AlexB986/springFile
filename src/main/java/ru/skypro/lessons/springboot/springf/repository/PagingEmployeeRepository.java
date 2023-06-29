package ru.skypro.lessons.springboot.springf.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.lessons.springboot.springf.pojo.Employee;

public interface PagingEmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {

}
