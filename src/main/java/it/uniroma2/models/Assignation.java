package it.uniroma2.models;

public class Assignation {
    private Teacher teacher;
    private Course course;

    public Assignation(Teacher teacher,Course course){
        this.teacher = teacher;
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
