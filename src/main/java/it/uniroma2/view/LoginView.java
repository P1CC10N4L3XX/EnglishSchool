package it.uniroma2.view;

import it.uniroma2.beans.CredentialsBean;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.view.utils.GraphicUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView{
    public static CredentialsBean getCredentials() throws IOException, NotCompatibleOsException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        GraphicUtils.showTitle("Login");
        GraphicUtils.showSpacing(1);
        System.out.print("username: ");
        String username = bufferedReader.readLine();
        System.out.print("password: ");
        String password = bufferedReader.readLine();
        return new CredentialsBean(username,password);
    }
}
