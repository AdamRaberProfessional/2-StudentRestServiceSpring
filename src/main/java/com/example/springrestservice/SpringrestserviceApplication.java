package com.example.springrestservice;


import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringrestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestserviceApplication.class, args);
		try {
			writeJsonSimpleDemo("./src/otherfiles/HelloWorld.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void writeJsonSimpleDemo(String filename) throws Exception {
		JSONObject sampleObject = new JSONObject();
		sampleObject.put("name", "Stackabuser");
		sampleObject.put("age", 35);
	
		JSONArray messages = new JSONArray();
		messages.add("Hey!");
		messages.add("What's up?!");
	
		sampleObject.put("messages", messages);
		Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());
	}


}
