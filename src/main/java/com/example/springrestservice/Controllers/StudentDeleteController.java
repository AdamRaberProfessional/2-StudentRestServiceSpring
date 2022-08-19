package com.example.springrestservice.Controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.springrestservice.utils.ServiceUtils;
import com.example.util.FileHandler;

import org.json.simple.JSONObject;
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
	public ResponseEntity<Object> student(@RequestParam(value = "id", defaultValue = "") String id) throws IOException, org.json.simple.parser.ParseException, SQLException {
       
        /* Deletes the student with the ID matching the id RequestParam. */
        
        System.out.println("requested delete student "+ id);
        if(!id.equals("")){ 
            Connection conn = ServiceUtils.getConnection();
            PreparedStatement stmtDelete = conn.prepareStatement("DELETE FROM students WHERE id = ?");
            stmtDelete.setString(1, id);
            stmtDelete.executeUpdate();
            stmtDelete.close();
            conn.close();
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }
                 
   
}