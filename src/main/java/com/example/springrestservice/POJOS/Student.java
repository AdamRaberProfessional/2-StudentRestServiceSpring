package com.example.springrestservice.POJOS;

import java.sql.Time;
import java.util.Calendar;

public class Student { 
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer grade;
    private String major;
    private Time timeCreated;

    public Student(int id, String fName, String lName, int grade, String major, Time timeCreated){
        this.id = id;
        this.firstname = fName;
        this.lastname = lName;
        this.grade = grade;
        this.major = major;
        this.timeCreated = timeCreated;
    }

    public Student(int id, String fName, String lName, int grade, String major){
        this.id = id;
        this.firstname = fName;
        this.lastname = lName;
        this.grade = grade;
        this.major = major;
        this.timeCreated = new java.sql.Time(Calendar.getInstance().getTime().getTime());
    }

    public Student(String fName, String lName, int grade, String major){
        this.id = null;
        this.firstname = fName;
        this.lastname = lName;
        this.grade = grade;
        this.major = major;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Time getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Time timeCreated) {
        this.timeCreated = timeCreated;
    }

}
