package ru.skypro.lessons.springboot.springf.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private int positionId;
    private String role;

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
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

