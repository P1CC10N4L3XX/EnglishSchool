package it.uniroma2.beans;

public class LevelBean {
    private String name;
    private String book;
    private String exam;

    public LevelBean(String name, String book, String exam){
        this.name = name;
        this.book = book;
        this.exam = exam;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getName() {
        return name;
    }

    public String getBook() {
        return book;
    }

    public String getExam() {
        return exam;
    }
}
