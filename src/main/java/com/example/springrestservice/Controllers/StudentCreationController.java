package com.example.springrestservice.Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.util.FileHandler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCreationController {

    @CrossOrigin
	@PostMapping("/createstudent")
	public void createstudent(@RequestParam(value = "fname", defaultValue = "None") String fname,
                              @RequestParam(value = "lname", defaultValue = "None") String lname,
                              @RequestParam(value = "grade", defaultValue = "None") String grade,
                              @RequestParam(value = "major", defaultValue = "None") String major) throws IOException, ParseException  { 

        /* Creates a student JSON object using the input params and appends it to the StudentDatabase JSON file. */

        if(!fname.equals("None") && !lname.equals("None") && !grade.equals("None")){
        	System.out.println("FILEPATH");
        	System.out.println(new File(".").getAbsolutePath());
            String file = "./src/main/java/com/example/springrestservice/StudentDatabase.json";

            ArrayList<JSONObject> jsonArray = FileHandler.getJArrayFromFile(file);    

            JSONObject newStudent = new JSONObject();
        
            newStudent.put("firstname", fname);
            newStudent.put("lastname", lname);
            newStudent.put("grade", grade);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();  
            String formattedDate = formatter.format(date);
            formattedDate = formattedDate.replace("/", "-");
            newStudent.put("timecreated", formattedDate);

            if(!major.equals("None")){
                newStudent.put("major", major);
            }else{
                newStudent.put("major", null);
            }
           
            jsonArray.add(newStudent);

            FileHandler.writeJArrayToFile(file, jsonArray);          
        }


		
	}
   
}

