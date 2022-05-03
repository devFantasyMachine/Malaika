package com.enspy.malaika.streaming;

import com.enspy.malaika.streaming.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StreamingServiceApplication implements CommandLineRunner {


	@Autowired
	private FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(StreamingServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		fileStorageService.init();

	}

}

