package com.example.springrestservice;

import java.io.File;
import java.io.FileWriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringrestserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringrestserviceApplication.class, args);
		try {
			 String fileName = "./src/main/java/com/example/springrestservice/StudentDatabase.json";
			File dbFile = new File(fileName);
			dbFile.createNewFile();
			if(dbFile.length() == 0) {
				 FileWriter writer = new FileWriter(fileName);
				 writer.write("{}");
				 writer.close();
			}
		}catch(Exception e) {
			System.out.println("Problem creating file");
		}
	}
}
