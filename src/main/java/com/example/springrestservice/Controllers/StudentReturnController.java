package com.example.springrestservice.Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentReturnController {

    @CrossOrigin
	@GetMapping("/getstudent")
	public String student(@RequestParam(value = "id", defaultValue = "None") String id) throws IOException, ParseException {
        FileReader reader = new FileReader("./springrestservice/src/main/java/com/example/springrestservice/StudentDatabase.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject studentDb = (JSONObject) jsonParser.parse(reader);
        reader.close();
        System.out.println(id);
        if(! id.equals("None") && ! id.equals("all")){
            try{
                JSONObject singleStudent = (JSONObject) jsonParser.parse(studentDb.get(id).toString());
                return singleStudent.toJSONString();
            }catch(NullPointerException e){
                JSONObject studentNotExist = new JSONObject();
                studentNotExist.put("content", "Student does not exist");
                return studentNotExist.toJSONString();
            }
            
        }else if (id.equals("all")){
            return studentDb.toJSONString();
        }else{
            return "{'content': 'Error parsing string'}";
        }
            
       

		
	}
   
}
