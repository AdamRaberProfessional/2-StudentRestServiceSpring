package com.example.util;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileHandler {

    public static ArrayList<JSONObject> getJArrayFromFile(String file) throws IOException, ParseException{
        /* 
        const file: String - the location of the JSON file.
        
        Returns an ArrayList of the JSON objects that are stored at the file location.
        */
    	
        FileReader reader = new FileReader(file);
        JSONParser jsonParser = new JSONParser();
        JSONObject studentDb = (JSONObject) jsonParser.parse(reader);
        reader.close();
        ArrayList<JSONObject> jsonArray = new ArrayList<JSONObject>();

        for(int i=0; i<studentDb.size(); i++){
            jsonArray.add((JSONObject) studentDb.get(Integer.toString(i)));
        }

        return jsonArray;
    }

    public static void writeJArrayToFile(String file, ArrayList<JSONObject> jsonArray) throws IOException{
         /** 
        const file: String - the location of the JSON file.
        const jsonArray: ArrayList<JSONObject> - the jsonArray that will be written to the file

        Replaces the contents of the JSON object stored in the location of 'file' with the contents of jsonArray.
        */
        JSONObject finalJsonObj = new JSONObject();
            for(int i=0; i<jsonArray.size(); i++){
                finalJsonObj.put(Integer.toString(i), jsonArray.get(i));
            }

            String finalJsonStr = finalJsonObj.toJSONString().replace("},", "},\r").replace(":null,", ":null,\r");
            Files.write(Paths.get(file), finalJsonStr.getBytes());
    }
}
