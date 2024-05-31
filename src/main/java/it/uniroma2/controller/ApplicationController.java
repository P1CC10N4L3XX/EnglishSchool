package it.uniroma2.controller;


import java.io.IOException;

public class ApplicationController implements Controller{
    public void start(){
        LoginController loginController = new LoginController();
    }
}
