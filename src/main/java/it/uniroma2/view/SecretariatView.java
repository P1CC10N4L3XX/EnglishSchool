package it.uniroma2.view;

import it.uniroma2.beans.AbsenceBean;
import it.uniroma2.beans.CourseBean;
import it.uniroma2.beans.StudentBean;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.exceptions.NullListException;
import it.uniroma2.view.utils.Color;
import it.uniroma2.view.utils.GraphicUtils;
import it.uniroma2.view.utils.TableCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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

    public static StudentBean getStudentInfo(List<CourseBean> courseBeanList) throws IOException, NullListException {
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSubTitle("Aggiungi allievo",Color.BLUE);
        GraphicUtils.showSpacing(1);
        String cf,name,telephonicNumber,courseCode,courseLevel;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Corsi:");
        TableCreator.showTable(courseBeanList);
        GraphicUtils.showSpacing(1);
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
        return new StudentBean(cf,name,"0",telephonicNumber,courseCode,courseLevel,null);
    }

    public static AbsenceBean getAbscenceInfo() throws IOException{
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSubTitle("Aggiungi assenza", Color.BLUE);
        GraphicUtils.showSpacing(1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String cfStudent,date,startTime,endTime;
        while(true){
            System.out.print("Inserire codice fiscale studente: ");
            cfStudent = bufferedReader.readLine();
            System.out.print("Inserire data di assenza [yyyy-mm-dd]: ");
            date = bufferedReader.readLine();
            System.out.print("Inserire orario di inizio lezione [hh:mm]: ");
            startTime = bufferedReader.readLine();
            System.out.print("Inserire orario di fine lezione [hh:mm]: ");
            endTime = bufferedReader.readLine();
            if(cfStudent.isEmpty() || date.isEmpty() || startTime.isEmpty() || endTime.isEmpty()){
                GraphicUtils.showError("Compilare tutti i campi !!");
            }else if(!validTime(startTime) || !validTime(endTime) || !validDate(date)){
                GraphicUtils.showError("Compilare correttamente date ed orari rispettando il formato !!");
            }else{
                break;
            }
        }
        return new AbsenceBean(cfStudent,date,startTime,endTime);
    }
    private static boolean validTime(String time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try{
            LocalTime.parse(time, dateTimeFormatter);
            return true;
        }catch(DateTimeParseException e){
            return false;
        }
    }

    private static boolean validDate(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate.parse(date, dateTimeFormatter);
            return true;
        }catch(DateTimeParseException e){
            return false;
        }
    }
}
