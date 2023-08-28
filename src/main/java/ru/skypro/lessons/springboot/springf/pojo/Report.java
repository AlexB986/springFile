package ru.skypro.lessons.springboot.springf.pojo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "report")
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @Lob
    @Column(name = "data", columnDefinition = "text")
    private String data;

    private String filePath;

    @CreationTimestamp(source = SourceType.DB)
    @Column(updatable = false, nullable = false)
    private Instant createOn;

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", data='" + data + '\'' +
                ", filePath='" + filePath + '\'' +
                ", createOn=" + createOn +
                '}';
    }
}

