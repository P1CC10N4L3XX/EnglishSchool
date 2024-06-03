package it.uniroma2.beans;

import java.lang.String;

public class CredentialsBean {
    private String username;
    private String password;

    public CredentialsBean(String username,String password){
        this.username = username;
        this.password = password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
