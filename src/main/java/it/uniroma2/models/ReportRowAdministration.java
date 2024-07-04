package it.uniroma2.models;


import java.sql.Date;
import java.sql.Time;

public class ReportRowAdministration {
    Date date;
    String dayOfWeek;
    Course course;
    Time startTime;
    Time endTime;

    public ReportRowAdministration(Date date, String dayOfWeek, Course course, Time startTime, Time endTime){
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.course = course;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Date getDate() {
        return date;
    }

    public Course getCourse() {
        return course;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
