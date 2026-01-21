package com.example.SummarizerAI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@AutoConfiguration
@EnableAutoConfiguration
@SpringBootApplication
public class SummarizerAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummarizerAiApplication.class, args);
	}

}
