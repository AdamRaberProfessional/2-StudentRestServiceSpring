package com.example.springrestservice;

public class Student {
    private String id;
    private String grade;
    private String name;
    private String major;
    private String timeCreated;
    
    public String getId() {return this.id;}

    public void setId(String id) {this.id = id;}

    public String getGrade() {return this.grade;}

    public void setGrade(String grade) {this.grade = grade;}

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public String getMajor() {return this.major;}

    public void setMajor(String major) {this.major = major;}

    public String getTimeCreated() {return this.timeCreated;}

    public void setTimeCreated(String timeCreated) {this.timeCreated = timeCreated;}

    public Student(String id, String grade, String name, String major, String timeCreated){
        this.setId(id);
        this.setGrade(grade);
        this.setName(name);
        this.setMajor(major);
        this.setTimeCreated(timeCreated);
    }

    public Student() {
    }
    
}
