package it.uniroma2.beans;

public class ReportInputBean {
    String teacher;
    int month;

    public ReportInputBean(String teacher,int month){
        this.teacher = teacher;
        this.month = month;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getMonth() {
        return month;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
