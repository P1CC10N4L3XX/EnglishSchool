package it.uniroma2.controller;

import it.uniroma2.beans.LevelBean;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.models.Role;
import it.uniroma2.view.AdministrationView;

import java.io.IOException;
import java.sql.SQLException;

public class AdministrationController implements Controller{
    @Override
    public void start() {
        try{
            ConnectionFactory.getInstance().changeRole(Role.AMMINISTRAZIONE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int command;
        while(true) {
            try {
                command = AdministrationView.getCommand();
            } catch (NotCompatibleOsException | IOException e) {
                throw new RuntimeException(e);
            }
            switch (command) {
                case 1 -> createLevel();
                case 2 -> System.out.println("Bravo hai creato un corso");
                case 3 -> System.out.println("Bravo hai creato una lezione");
                case 4 -> System.out.println("Bravo hai aggiunto un insegnante");
                case 5 -> System.out.println("Bravo hai assegnato un insegnante ad un corso");
                case 6 -> System.out.println("Bravo hai generato un report");
                case 7 -> System.out.println("Bravo hai fatto logout");
                default -> throw new RuntimeException("Scelta non valida !!");
            }
        }
    }

    private void createLevel(){
        LevelBean levelBean;
        try{
            levelBean = AdministrationView.getLevelInfo();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
