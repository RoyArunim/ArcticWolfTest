package com.project.directorymonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DirectorymonitoringApplication {
	static FileProcessor processor = FileProcessor.getInstance();


	public static void main(String[] args) {
		String directoryPath = "Filestore";
		processor.processFiles(directoryPath);
		SpringApplication.run(DirectorymonitoringApplication.class, args);
	}

}
