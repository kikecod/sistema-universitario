package com.example.sistemauniversitario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SistemauniversitarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemauniversitarioApplication.class, args);
    }

}
