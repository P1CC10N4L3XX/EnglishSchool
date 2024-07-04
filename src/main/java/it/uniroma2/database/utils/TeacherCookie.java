package it.uniroma2.database.utils;

public class TeacherCookie {
    private String loggedUser;

    private static TeacherCookie teacherCookieInstance;

    private TeacherCookie(){
        this.loggedUser = null;
    }

    public static TeacherCookie getInstance(){
        if(teacherCookieInstance == null){
            teacherCookieInstance = new TeacherCookie();
        }
        return teacherCookieInstance;
    }

    public void setLoggedUser(String loggedUser){
        this.loggedUser = loggedUser;
    }
    public String getLoggedUser(){
        return loggedUser;
    }
    public void logout(){
        this.loggedUser = null;
    }


}
