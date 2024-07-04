package it.uniroma2.controller;


import it.uniroma2.database.utils.TeacherCookie;
import it.uniroma2.models.Credentials;

import java.io.IOException;

public class ApplicationController implements Controller{
    public void start(){
        while(true){
            LoginController loginController = new LoginController();
            loginController.start();
            Credentials credentials = loginController.getCredentials();

            switch (credentials.getRole()) {
                case AMMINISTRAZIONE:
                    new AdministrationController().start();
                    break;
                case SEGRETERIA:
                    new SecretariatController().start();
                    break;
                case INSEGNANTE:
                    TeacherCookie.getInstance().setLoggedUser(credentials.getUsername());
                    new TeacherController().start();
            }
        }
    }
}
