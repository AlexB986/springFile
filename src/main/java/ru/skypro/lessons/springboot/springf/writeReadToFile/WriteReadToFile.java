package ru.skypro.lessons.springboot.springf.writeReadToFile;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springf.pojo.Employee;
import ru.skypro.lessons.springboot.springf.pojo.Position;
import ru.skypro.lessons.springboot.springf.pojo.Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteReadToFile {

    private Employee employee;
    private Report report;
    private Position position;

    public WriteReadToFile(Employee employee, Report report, Position position) {
        this.employee = employee;
        this.report = report;
        this.position = position;
    }

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

//    public static String readToFile(String filePath) {
//
//        Path path = Paths.get(filePath);
//        try {
//            return Files.lines(path)
//                    .collect(Collectors.joining());
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//            return null;
//        }
//    }
}

