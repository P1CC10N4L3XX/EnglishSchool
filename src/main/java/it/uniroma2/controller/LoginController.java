package it.uniroma2.controller;

import it.uniroma2.beans.CredentialsBean;
import it.uniroma2.database.LoginProcedureDAO;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.models.Credentials;
import it.uniroma2.view.LoginView;
import it.uniroma2.view.utils.GraphicUtils;

import java.io.IOException;

public class LoginController implements Controller{
    private Credentials credentials;

    @Override
    public void start() {
        CredentialsBean credentialsBean;
        while(true){
            try {
                credentialsBean = LoginView.getCredentials();
            } catch (IOException | NotCompatibleOsException e) {
                throw new RuntimeException(e);
            }
            try{
                credentials = new LoginProcedureDAO().execute(credentialsBean.getUsername(),credentialsBean.getPassword());
            }catch(DAOException e){
                throw new RuntimeException(e);
            }
            if(credentials.getRole()!=null){
                break;
            }else{
                GraphicUtils.showError("Credenziali non valide !!");
            }
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }
}
