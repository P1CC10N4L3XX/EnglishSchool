package it.uniroma2.beans;

public class TeacherBean {
    String cf;
    String name;
    String address;
    String nationality;

    public TeacherBean(String cf,String name,String address,String nationality){
        this.cf = cf;
        this.name = name;
        this.address = address;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCf() {
        return cf;
    }

    public String getNationality() {
        return nationality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
