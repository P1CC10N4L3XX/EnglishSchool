package it.uniroma2.models;

import java.sql.Time;

public class Lesson {
    private Course course;
    private Teacher teacher;

    private String day;
    private Time startTime;
    private Time endTime;

    public Lesson(Course course, Teacher teacher,String day, Time startTime, Time endTime){
        this.course = course;
        this.teacher = teacher;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Lesson(Course course,String day, Time startTime, Time endTime){
        this.course = course;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Course getCourse() {
        return course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getDay() {
        return day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
