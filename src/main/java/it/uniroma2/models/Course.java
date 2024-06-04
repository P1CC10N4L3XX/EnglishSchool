package it.uniroma2.models;


import java.util.Date;

public class Course {
    private int code;
    private Level level;
    private Date activationDate;
    private int studentsNumber;

    public Course(int code,Level level,Date activationDate,int studentsNumber){
        this.code = code;
        this.level = level;
        this.activationDate = activationDate;
        this.studentsNumber = studentsNumber;
    }

    public int getCode() {
        return code;
    }

    public Level getLevel() {
        return level;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }
}
