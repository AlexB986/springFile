package ru.skypro.lessons.springboot.springf.pojo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


//@NoArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "position")
public class Position {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer positionId;

    @Column(name = "role")
    private String role;



    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", role='" + role + '\'' +
                '}';
    }
}

