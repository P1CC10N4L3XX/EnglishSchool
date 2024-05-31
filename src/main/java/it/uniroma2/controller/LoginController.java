package it.uniroma2.controller;

import it.uniroma2.beans.CredentialsBean;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.models.Credentials;
import it.uniroma2.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller{
    private Credentials credentials;

    @Override
    public void start() {
        CredentialsBean credentialsBean = null;
        try {
            credentialsBean = LoginView.getCredentials();
        } catch (IOException | InterruptedException | NotCompatibleOsException e) {
            throw new RuntimeException(e);
        }
        credentials = new Credentials()
    }

    public Credentials getCredentials() {
        return credentials;
    }
}
