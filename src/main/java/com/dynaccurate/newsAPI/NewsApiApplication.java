package com.dynaccurate.newsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NewsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsApiApplication.class, args);
	}

}
