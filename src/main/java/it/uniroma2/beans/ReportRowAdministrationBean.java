package it.uniroma2.beans;

public class ReportRowAdministrationBean {
    private String date;
    private String dayOfWeek;
    private String courseCode;
    private String courseLevel;
    private String startTime;
    private String endTime;

    public ReportRowAdministrationBean(String date, String dayOfWeek, String courseCode, String courseLevel, String startTime, String endTime){
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.courseCode = courseCode;
        this.courseLevel = courseLevel;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
