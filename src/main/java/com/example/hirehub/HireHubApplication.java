package com.example.hirehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HireHubApplication {

    public static void main(String[] args) {

        System.out.println("--- PROQRAM BASLADI ---");

       SpringApplication.run(HireHubApplication.class, args);
    }





}
