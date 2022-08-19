package com.example.springrestservice.Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.springrestservice.utils.ServiceUtils;
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
                              @RequestParam(value = "major", defaultValue = "None") String major) throws IOException, ParseException, SQLException  { 
        Connection conn = ServiceUtils.getConnection();
        PreparedStatement stmtCreateStudent = conn.prepareStatement("INSERT INTO STUDENTS "
                                                                    +"(firstname, lastname, grade, major, timecreated) "
                                                                    +"VALUES (?, ?, ?, ?, ?)");
        
        stmtCreateStudent.setString(1, fname);
        stmtCreateStudent.setString(2, lname);
        stmtCreateStudent.setString(3, grade);
        stmtCreateStudent.setString(4, major); 
        System.out.println(fname + " " + lname + " " + grade + " " + major);
        stmtCreateStudent.setTime(5, new java.sql.Time(Calendar.getInstance().getTime().getTime()));
        stmtCreateStudent.executeUpdate();                                                           
        stmtCreateStudent.close();
        conn.close();
	}
   
}

