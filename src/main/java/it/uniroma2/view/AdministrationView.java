package it.uniroma2.view;

import it.uniroma2.beans.CourseBean;
import it.uniroma2.beans.LevelBean;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.exceptions.NullListException;
import it.uniroma2.view.utils.Color;
import it.uniroma2.view.utils.GraphicUtils;
import it.uniroma2.view.utils.TableCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class AdministrationView {
    public static int getCommand() throws NotCompatibleOsException, IOException {
        GraphicUtils.clear();
        GraphicUtils.showTitle("Administration View",Color.RED);
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
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSubTitle("Creazione livello", Color.GREEN);
        GraphicUtils.showSpacing(1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserisci nome livello: ");
        String levelName = bufferedReader.readLine();
        System.out.print("Inserisci libro livello: ");
        String levelBook = bufferedReader.readLine();
        String levelExam;
        while(true) {
            System.out.print("Il livello include un esame? [Y-N]: ");
            levelExam = bufferedReader.readLine().toLowerCase();
            if(levelExam.toLowerCase().equals("y") || levelExam.toLowerCase().equals("n")){
                break;
            }else{
                GraphicUtils.showError("Scegliere tra [Y-N]!!");
            }
        }
        return new LevelBean(levelName,levelBook,levelExam);
    }

    public static CourseBean getCourseInfo(List<LevelBean> levelBeanList) throws IOException, NullListException {
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSubTitle("Creazione corso", Color.GREEN);
        GraphicUtils.showSpacing(1);
        System.out.println("Livelli");
        TableCreator.showTable(levelBeanList);
        GraphicUtils.showSpacing(1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String levelName;
        while(true) {
            System.out.print("Inserire a quale livello appartiene il nuovo corso: ");
            levelName = bufferedReader.readLine();
            if(isLevelNameValid(levelName,levelBeanList)) {
                break;
            }
            GraphicUtils.showError("Il livello inserito non esiste");
        }
        return new CourseBean(levelName);
    }
    private static boolean isLevelNameValid(String levelName,List<LevelBean> levelBeanList){
        for(LevelBean levelBean : levelBeanList){
            if(levelBean.getName().equals(levelName)){
                return true;
            }
        }
        return false;
    }
}
