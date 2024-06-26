package it.uniroma2.beans;

public class StudentBean {
    private String cf;
    private String name;
    private String telephonicNumber;
    private String courseCode;
    private String courseLevel;
    private String registrationDate;

    public StudentBean(String cf, String name, String telephonicNumber, String courseCode, String courseLevel, String registrationDate){
        this.cf = cf;
        this.name = name;
        this.telephonicNumber = telephonicNumber;
        this.courseCode = courseCode;
        this.courseLevel = courseLevel;
        this.registrationDate = registrationDate;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseLevel(String levelCourse) {
        this.courseLevel = levelCourse;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setTelephonicNumber(String telephonicNumber) {
        this.telephonicNumber = telephonicNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCf() {
        return cf;
    }

    public String getName() {
        return name;
    }

    public String getcourseLevel() {
        return courseLevel;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getTelephonicNumber() {
        return telephonicNumber;
    }
}
