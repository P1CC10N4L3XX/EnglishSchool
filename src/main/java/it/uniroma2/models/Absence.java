package it.uniroma2.models;

import java.sql.Date;
import java.sql.Time;

public class Absence {
    private Student student;
    private Date date;
    private Time startTime;
    private Time endTime;
    public Absence(Student student, Date date, Time startTime, Time endTime){
        this.student = student;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Student getStudent() {
        return student;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
