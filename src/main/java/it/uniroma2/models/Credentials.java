package it.uniroma2.models;

public class Credentials {
    private String username;
    private String password;
    private byte type;

    public Credentials(String username,String password,byte type){
        this.username = username;
        this.password = password;
        this.type= type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public byte getType() {
        return type;
    }
}
