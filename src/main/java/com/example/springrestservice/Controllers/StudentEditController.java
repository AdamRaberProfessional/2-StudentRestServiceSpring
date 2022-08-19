package com.example.springrestservice.Controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.example.springrestservice.utils.ServiceUtils;
import com.example.util.FileHandler;

import org.json.simple.JSONObject;
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
                          @RequestParam(value = "attributeVal", defaultValue = "") String attributeVal) throws IOException, ParseException, SQLException {
        
                HashSet<String> validAttributes = new HashSet<> ();
                validAttributes.add("firstname");
                validAttributes.add("lastname");
                validAttributes.add("grade");
                validAttributes.add("major");
            if (!validAttributes.contains(attributeChange)) //or id is not integer or id < 0
            {
                // send bad request response.
            }
            if(attributeChange == "grade")
            {
                // Make sure it's a number  bad request response if not
            } 
            else if(attributeChange == "major")
            {
                // Make sure it's <= 4 characters - bad request response if not
            }
            Connection conn = ServiceUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE STUDENTS SET " + attributeChange + " = ? WHERE id = ?");
            stmt.setString(1, attributeVal);
            stmt.setString(2, id);
            stmt.executeUpdate(); 
            stmt.close();
            conn.close();
	}
   
}