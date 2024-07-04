package it.uniroma2.models;

import java.sql.Date;

public class Student {
    private String cf;
    private String name;
    private int absenceNumber;
    private String telephonicNumber;
    private Course course;
    private Date registrationDate;
    public Student(String cf, String name,int absenceNumber, String telephonicNumber, Course course, Date registrationDate){
        this.cf = cf;
        this.name = name;
        this.absenceNumber = absenceNumber;
        this.telephonicNumber = telephonicNumber;
        this.course = course;
        this.registrationDate = registrationDate;
    }

    public Student(String cf) {
        this.cf = cf;
    }

    public String getCf() {
        return cf;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public String getTelephonicNumber() {
        return telephonicNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephonicNumber(String telephonicNumber) {
        this.telephonicNumber = telephonicNumber;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
