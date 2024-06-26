package it.uniroma2.beans;

public class LessonBean {
    private String courseCode;
    private String courseLevel;
    private String teacher;
    private String day;
    private String startTime;
    private String endTime;

    public LessonBean(String courseCode, String courseLevel, String teacher, String day, String startTime, String endTime){
        this.courseCode = courseCode;
        this.courseLevel = courseLevel;
        this.teacher = teacher;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
