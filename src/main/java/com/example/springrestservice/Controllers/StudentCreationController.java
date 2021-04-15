package com.example.springrestservice.Controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
        System.out.println("Request made");
        System.out.println(fname+lname+grade);

        if(!fname.equals("None") && !lname.equals("None") && !grade.equals("None")){
            String file = "./springrestservice/src/main/java/com/example/springrestservice/StudentDatabase.json";
            FileReader reader = new FileReader(file);
            JSONParser jsonParser = new JSONParser();
            JSONObject  studentDb = (JSONObject) jsonParser.parse(reader);
            reader.close();
            ArrayList<JSONObject> jsonArray = new ArrayList<JSONObject>();

            Iterator it =  studentDb.keySet().iterator();
                    while (it.hasNext()){
                            String key = (String) it.next();
                            jsonArray.add((JSONObject) studentDb.get(key));
                    }

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


            JSONObject finalJsonObj = new JSONObject();
            jsonArray.add(newStudent);
            for(int i=0; i<jsonArray.size(); i++){
                finalJsonObj.put(Integer.toString(i), jsonArray.get(i));
            }

            String finalJsonStr = finalJsonObj.toJSONString().replace("},", "},\r");
            Files.write(Paths.get(file), finalJsonStr.getBytes());
            
        }


		
	}
   
}

