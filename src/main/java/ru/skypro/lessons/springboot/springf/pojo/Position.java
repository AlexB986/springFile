package ru.skypro.lessons.springboot.springf.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

//@NoArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor

@Entity
@Table(name = "position")
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer positionId;

    private String role;

    @OneToMany(mappedBy = "position")
    private List<Employee> employeeList;
}

