package it.uniroma2.beans;

public class ReportRowTeacherBean {
    String lessonDay;
    String courseCode;
    String courseLevel;
    String startTime;
    String endTime;

    public ReportRowTeacherBean(String courseCode,String courseLevel,String lessonDay,String startTime, String endTime){
        this.courseCode = courseCode;
        this.courseLevel = courseLevel;
        this.lessonDay = lessonDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public String getLessonDay() {
        return lessonDay;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public void setLessonDay(String lessonDay) {
        this.lessonDay = lessonDay;
    }
}
