package it.uniroma2.view;

import it.uniroma2.beans.LevelBean;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.view.utils.GraphicUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AdministrationView {
    public static int getCommand() throws NotCompatibleOsException, IOException {
        GraphicUtils.clear();
        GraphicUtils.showTitle("Administration View");
        GraphicUtils.showSpacing(1);
        System.out.println("1)Crea livello");
        System.out.println("2)Crea corso");
        System.out.println("3)Crea una lezione di corso");
        System.out.println("4)Aggiungi insegnante");
        System.out.println("5)Assegna insegnante ad un corso");
        System.out.println("6)Genera report insegnante");
        System.out.println("7)logout");

        Scanner input = new Scanner(System.in);
        int command = 0;
        while(true){
            System.out.print("Inserire la scelta: ");
            command = input.nextInt();
            if(command>=1 && command<=7){
                break;
            }
            System.out.println("comando non valido");
        }
        return command;
    }

    public static LevelBean getLevelInfo() throws IOException {
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(20);
        GraphicUtils.showSubTitle("Creazione Livello");
        GraphicUtils.showSpacing(1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserisci nome livello: ");
        String levelName = bufferedReader.readLine();
        System.out.print("Inserisci libro livello: ");
        String levelBook = bufferedReader.readLine();
        String levelExam;
        while(true) {
            System.out.print("Il livello include un esame? [Y-N]: ");
            levelExam = bufferedReader.readLine();
            if(levelExam.toLowerCase() == "y" || levelExam.toLowerCase() == "n"){
                break;
            }else{
                GraphicUtils.showError("Scegliere tra [Y-N]!!");
            }
        }
        return new LevelBean(levelName,levelBook,levelExam);
    }
}
