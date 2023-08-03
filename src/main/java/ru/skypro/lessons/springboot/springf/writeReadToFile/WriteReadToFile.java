package ru.skypro.lessons.springboot.springf.writeReadToFile;


import jakarta.annotation.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Position;
import ru.skypro.lessons.springboot.springf.pojo.Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WriteReadToFile {


    /**
     * копирует фаил.
     */
    public static void writeToFile(MultipartFile file) {
        OutputStream outputStream;
        String filePath = "test.json";
        try {
            outputStream = new FileOutputStream(filePath);
            outputStream.write(file.getBytes());
            outputStream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();

        }

    }

    public static Resource dowloadFile(Optional<Report> reportsfindById) {
        String fileName = "responseTest.Json";
        Resource resource = (Resource) new ByteArrayResource(reportsfindById.get().getData().getBytes());
        return resource;
    }


}

