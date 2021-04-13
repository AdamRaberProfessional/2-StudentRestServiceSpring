package com.example.springrestservice.Controllers;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentEditController {

	@PostMapping("/editstudent")
	public void student(@RequestParam(value = "id", defaultValue = "") String id,
                          @RequestParam(value = "attributeChange", defaultValue = "") String attributeChange,
                          @RequestParam(value = "attributeVal", defaultValue = "") String attributeVal) throws IOException, ParseException {
        
                String file = "./springrestservice/src/main/java/com/example/springrestservice/StudentDatabase.json";
                FileReader reader = new FileReader(file);
                JSONParser jsonParser = new JSONParser();
                JSONObject  studentDb = (JSONObject) jsonParser.parse(reader);
                ArrayList<JSONObject> jsonArray = new ArrayList<JSONObject>();
                Iterator it =  studentDb.keySet().iterator();
                while (it.hasNext()){
                        String key = (String) it.next();
                        jsonArray.add((JSONObject) studentDb.get(key));
                }
                
                if(!id.equals("") && !attributeChange.equals("") && !attributeVal.equals("")){
                        JSONObject chosenStudent = jsonArray.get(Integer.parseInt(id));
                        chosenStudent.put(attributeChange, attributeVal);
                        if (attributeChange.equals("grade") && Integer.parseInt(attributeVal) <15){
                                chosenStudent.put("major", null);
                        }

                        JSONObject finalJsonObj = new JSONObject();

                        for(int i=0; i<jsonArray.size(); i++){
                                finalJsonObj.put(Integer.toString(i), jsonArray.get(i));
                        }
                        String finalJsonStr = finalJsonObj.toJSONString().replace("},", "},\r");
                        Files.write(Paths.get(file), finalJsonStr.getBytes());
                }
	}
   
}