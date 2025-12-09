package com.braymond.summarizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@Async
@SpringBootApplication
public class SummarizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummarizerApplication.class, args);
	}

}
