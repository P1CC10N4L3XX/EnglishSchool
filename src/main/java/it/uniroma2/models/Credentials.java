package it.uniroma2.models;

public class Credentials {
    private String username;
    private String password;
    private Role role;

    public Credentials(String username,String password,Role role){
        this.username = username;
        this.password = password;
        this.role= role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
