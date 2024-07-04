package it.uniroma2.beans;

public class AbsenceBean {
    private String student;
    private String date;
    private String startTime;
    private String endTime;

    public AbsenceBean(String student, String date, String startTime, String endTime){
        this.student = student;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStudent() {
        return student;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
