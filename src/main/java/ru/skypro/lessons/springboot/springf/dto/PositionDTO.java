package ru.skypro.lessons.springboot.springf.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.skypro.lessons.springboot.springf.pojo.Position;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private Integer positionId;
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
        return "PositionDTO{" +
                "positionId=" + positionId +
                ", role='" + role + '\'' +
                '}';
    }
}

