package com.example.springrestservice;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springrestservice.utils.ServiceUtils;

@SpringBootApplication
public class SpringrestserviceApplication {

	public static void main(String[] args) throws SQLException, IOException {
		// Fail fast method - if this can't get a JDBC connection, I don't want this running at all.
		Connection conn = ServiceUtils.getConnection();
		PreparedStatement stmtCreateTable = conn.prepareStatement("CREATE TABLE IF NOT EXISTS 'STUDENTS' "
																 +"('id' INTEGER PRIMARY KEY AUTOINCREMENT, "
																 +"'firstname' TEXT NOT NULL, "
																 +"'lastname' TEXT NOT NULL, "
																 +"'grade' INT NOT NULL, "
																 +"'major' TEXT, "
																 +"'timecreated' TIME NOT NULL)");
		stmtCreateTable.execute();
		stmtCreateTable.close();
		conn.close();
		SpringApplication.run(SpringrestserviceApplication.class, args);
		
		
	}
}
