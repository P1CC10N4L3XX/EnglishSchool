package it.uniroma2.beans;

public class CredentialsBean {
    private String username;
    private String password;

    public CredentialsBean(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
