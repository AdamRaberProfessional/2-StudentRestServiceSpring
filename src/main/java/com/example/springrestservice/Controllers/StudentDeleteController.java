package com.example.springrestservice.Controllers;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

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
        System.out.println("requested delete student "+ id);
        if(id != ""){
            String file = "./springrestservice/src/main/java/com/example/springrestservice/StudentDatabase.json";
            FileReader reader = new FileReader(file);
            JSONParser jsonParser = new JSONParser();
            JSONObject  studentDb = (JSONObject) jsonParser.parse(reader);
            reader.close();
            ArrayList<JSONObject> jsonArray = new ArrayList<JSONObject>();
            Iterator it =  studentDb.keySet().iterator();
            for(){
                
            }
            while (it.hasNext()){
                    String key = (String) it.next();
                    jsonArray.add((JSONObject) studentDb.get(key));
            }
            System.out.println("first");
            System.out.println(jsonArray.toString());
    
            if(Integer.parseInt(id) < studentDb.size() - 1 && Integer.parseInt(id) >= 0){
                jsonArray.set(Integer.parseInt(id), null);
            }else if(Integer.parseInt(id) == studentDb.size() - 1){
                jsonArray.remove(Integer.parseInt(id));   
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            JSONObject finalJsonObj = new JSONObject();
    
            for(int i=0; i<jsonArray.size(); i++){
                    finalJsonObj.put(Integer.toString(i), jsonArray.get(i));
            }
            String finalJsonStr = finalJsonObj.toJSONString().replace("},", "},\r");
            System.out.println("second");
            System.out.println(finalJsonStr);
            Files.write(Paths.get(file), finalJsonStr.getBytes());
           
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }
                 
   
}