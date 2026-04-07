package org.javaSchool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApp {
    /*
    Default API Endpoint :: http://localhost:8080/
     */
    public static void main(String[] args){
        SpringApplication.run(StudentApp.class, args);
    }
}
