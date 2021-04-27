package com.example.springrestservice.Controllers;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.example.OtherFiles.FileHandler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentDeleteController {

    @CrossOrigin
	@PostMapping("/deletestudent")
	public ResponseEntity<Object> student(@RequestParam(value = "id", defaultValue = "") String id) throws IOException, org.json.simple.parser.ParseException {
       
        /* Deletes the student with the ID matching the id RequestParam. */
        
        System.out.println("requested delete student "+ id);
        if(id != ""){
            String file =  "./springrestservice/src/main/java/com/example/springrestservice/StudentDatabase.json";
            ArrayList<JSONObject> jsonArray = FileHandler.getJArrayFromFile(file);
    
            if(Integer.parseInt(id) < jsonArray.size() - 1 && Integer.parseInt(id) >= 0){
                jsonArray.set(Integer.parseInt(id), null);
            }else if(Integer.parseInt(id) == jsonArray.size() - 1){
                jsonArray.remove(Integer.parseInt(id));   
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            FileHandler.writeJArrayToFile(file, jsonArray);
            
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }
                 
   
}