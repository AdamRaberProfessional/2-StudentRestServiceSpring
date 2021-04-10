package com.example.springrestservice.Controllers;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentReturnController {

	@GetMapping("/getstudent")
	public String student(@RequestParam(value = "id", defaultValue = "None") String id) throws IOException, ParseException {

        
        //String grade, String name, String major, String timeCreated
        FileReader reader = new FileReader("./src/main/java/com/example/springrestservice/StudentDatabase.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject studentDb = (JSONObject) jsonParser.parse(reader);
        System.out.println(id);
        if(! id.equals("None") && ! id.equals("all")){
            JSONObject singleStudent = (JSONObject) jsonParser.parse(studentDb.get(id).toString());
            System.out.println(singleStudent);
            return singleStudent.toJSONString();
        }else if (id.equals("all")){
            return studentDb.toJSONString();
        }else{
            return "{'content': 'Error parsing string'}";
        }
            
       

		
	}
   
}
