package com.example.springrestservice.Controllers;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.example.OtherFiles.FileHandler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentEditController {

        @CrossOrigin
	@PostMapping("/editstudent")
	public void student(@RequestParam(value = "id", defaultValue = "") String id,
                          @RequestParam(value = "attributeChange", defaultValue = "") String attributeChange,
                          @RequestParam(value = "attributeVal", defaultValue = "") String attributeVal) throws IOException, ParseException {
        
                /* Changes the attribute specified in the RequestParams to attributeVal and writes to the StudetnDb */
                String file =  "./springrestservice/src/main/java/com/example/springrestservice/StudentDatabase.json";
        
                ArrayList<JSONObject> jsonArray = FileHandler.getJArrayFromFile(file);
                
                if(!id.equals("") && !attributeChange.equals("") && !attributeVal.equals("")){
                        JSONObject chosenStudent = jsonArray.get(Integer.parseInt(id)); // chosenStudent directly changes the student in jsonArray
                        chosenStudent.put(attributeChange, attributeVal);
                        if (attributeChange.equals("grade") && Integer.parseInt(attributeVal) <12){
                                chosenStudent.put("major", null);
                        }

                        FileHandler.writeJArrayToFile(file, jsonArray);
                }
	}
   
}