package ru.skypro.lessons.springboot.springf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class InfoController {

    public InfoController() {
    }

    @Value("${app.env}")
    private String appInfo;

    @GetMapping(value = "/appInfo")
    public String resurectionApp() {
        return appInfo;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    @Override
    public String toString() {
        return "InfoController{" +
                "appInfo='" + appInfo + '\'' +
                '}';
    }
}
