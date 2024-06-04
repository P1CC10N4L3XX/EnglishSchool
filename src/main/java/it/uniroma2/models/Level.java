package it.uniroma2.models;

public class Level {
    private String name;
    private String book;
    private boolean exam;

    public Level(String name, String book, boolean exam){
        this.name = name;
        this.book = book;
        this.exam = exam;
    }
    public Level(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBook() {
        return book;
    }
    public boolean getExam(){
        return exam;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExam(boolean exam) {
        this.exam = exam;
    }
}
