package com.example.springrestservice.Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestservice.POJOS.Student;
import com.example.springrestservice.utils.ServiceUtils;
import com.google.gson.Gson;


@RestController
public class StudentReturnController {

    @CrossOrigin
	@GetMapping("/getstudent")
	public String student(@RequestParam(value = "id", defaultValue = "None") String id) throws IOException, ParseException, SQLException {
        Connection conn = ServiceUtils.getConnection();
        ArrayList<Student> res = new ArrayList<>();
        PreparedStatement stmtGetStudents;
        ResultSet results;
        System.out.println("Get Student hit with id of " + id);
        if(id.equals("all")){
            stmtGetStudents = conn.prepareStatement("SELECT * FROM students");
            results = stmtGetStudents.executeQuery();
        }else{ 
            stmtGetStudents = conn.prepareStatement("SELECT * FROM students WHERE id = ?");
            stmtGetStudents.setString(1, id);
            results = stmtGetStudents.executeQuery();
        }
        while(results.next()){
            res.add(new Student(results.getInt("id"), results.getString("firstname"), 
                    results.getString("lastname"), results.getInt("grade"), 
                    results.getString("major"), results.getTime("timecreated")));
        }
        stmtGetStudents.close();
        results.close();
        String out = new Gson().toJson(res);
        System.out.println(out);
        return out;
	}
   
}
