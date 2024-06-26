package it.uniroma2.beans;

public class AssignationBean {
    String teacher;
    String courseCode;
    String courseLevel;

    public AssignationBean(String teacher, String courseCode, String courseLevel){
        this.teacher = teacher;
        this.courseCode = courseCode;
        this.courseLevel = courseLevel;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }
}
