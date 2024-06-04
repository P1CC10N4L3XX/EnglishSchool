package it.uniroma2.beans;

public class CourseBean {
    private String code;
    private String level;
    private String activationDate;
    private String studentsNumber;

    public CourseBean(String code,String level,String activationDate,String studentsNumber){
        this.code = code;
        this.level = level;
        this.activationDate = activationDate;
        this.studentsNumber = studentsNumber;
    }
    public CourseBean(String level){
        this.level = level;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStudentsNumber(String studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public String getCode() {
        return code;
    }

    public String getStudentsNumber() {
        return studentsNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
