package it.uniroma2.view;

import it.uniroma2.beans.StudentBean;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.view.utils.Color;
import it.uniroma2.view.utils.GraphicUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;

public class SecretariatView {
    public static int getCommand() throws NotCompatibleOsException, IOException {
        GraphicUtils.clear();
        GraphicUtils.showTitle("Secretariat View", Color.RED);
        GraphicUtils.showSpacing(1);
        System.out.println("1)Aggiungi studente");
        System.out.println("2)Aggiungi assenza");
        System.out.println("3)logout");
        System.out.println("4)Esci dal programma");
        Scanner input = new Scanner(System.in);
        int command;
        System.out.print("Inserire la scelta: ");
        command = input.nextInt();
        return command;
    }

    public static StudentBean getStudentInfo() throws IOException{
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSubTitle("Aggiungi allievo",Color.BLUE);
        GraphicUtils.showSpacing(1);
        String cf,name,telephonicNumber,courseCode,courseLevel,registrationDate;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.print("Inserire codice fiscale allievo: ");
            cf = bufferedReader.readLine();
            System.out.print("Inserire nome allievo: ");
            name = bufferedReader.readLine();
            System.out.print("Inserire numero di telefono allievo: ");
            telephonicNumber = bufferedReader.readLine();
            System.out.print("Inserire codice corso a cui iscrivere allievo: ");
            courseCode = bufferedReader.readLine();
            System.out.print("Inserire livello corso a cui iscrivere allievo: ");
            courseLevel = bufferedReader.readLine();
            if(cf.isEmpty() || name.isEmpty() || telephonicNumber.isEmpty() || courseCode.isEmpty() || courseLevel.isEmpty()){
                GraphicUtils.showError("Compilare tutti i campi !!");
            }else{
                break;
            }
        }
        registrationDate = LocalDate.now().toString();
        return new StudentBean(cf,name,telephonicNumber,courseCode,courseLevel,registrationDate);
    }
}
