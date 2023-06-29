package ru.skypro.lessons.springboot.springf.dto;


import ru.skypro.lessons.springboot.springf.pojo.Report;

public class ReportDTO {
    private Integer id;
    private String data;

    public static ReportDTO fromReport(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setData(report.getData());
        return reportDTO;
    }

    public Report toReport() {
        Report report = new Report();
        report.setId(this.getId());
        report.setData(this.getData());
        return report;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
