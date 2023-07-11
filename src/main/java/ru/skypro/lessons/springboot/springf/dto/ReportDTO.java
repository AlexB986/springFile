package ru.skypro.lessons.springboot.springf.dto;


import ru.skypro.lessons.springboot.springf.pojo.Report;

public class ReportDTO {
    private String position;
    private long count;
    private int maxSalary;
    private int minSalary;
    private double averageSalary;

    public ReportDTO(String position,long count,int maxSalary,int minSalary,double averageSalary){
        this. position = position;
        this.count = count;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.averageSalary = averageSalary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}