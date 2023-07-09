package com.example.pdfsplitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdfsplitterApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/dinev");
        SpringApplication.run(PdfsplitterApplication.class, args);
    }

}
