package it.uniroma2.view;

import it.uniroma2.beans.*;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.exceptions.NullListException;
import it.uniroma2.view.utils.Color;
import it.uniroma2.view.utils.GraphicUtils;
import it.uniroma2.view.utils.TableCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdministrationView {
    public static int getCommand() throws NotCompatibleOsException, IOException {
        GraphicUtils.clear();
        GraphicUtils.showTitle("Administration View",Color.RED);
        GraphicUtils.showSpacing(1);
        System.out.println("1)Crea livello");
        System.out.println("2)Crea corso");
        System.out.println("3)Aggiungi insegnante");
        System.out.println("4)Assegna insegnante ad un corso");
        System.out.println("5)Crea una lezione di corso");
        System.out.println("6)Genera report insegnante");
        System.out.println("7)logout");
        System.out.println("8)Esci dal programma");
        Scanner input = new Scanner(System.in);
        int command;
        System.out.print("Inserire la scelta: ");
        command = input.nextInt();
        return command;
    }

    public static LevelBean getLevelInfo(List<LevelBean> levelBeanList) throws IOException {
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSubTitle("Creazione livello", Color.GREEN);
        GraphicUtils.showSpacing(1);
        try {
            System.out.println("Livelli gia presenti nel database: ");
            TableCreator.showTable(levelBeanList);
        } catch (NullListException e) {
            System.out.println("Non esistono altri livelli !!");

        }
        GraphicUtils.showSpacing(1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String levelName,levelBook,levelExam;
        while(true) {
            System.out.print("Inserisci nome livello: ");
            levelName = bufferedReader.readLine();
            System.out.print("Inserisci libro livello: ");
            levelBook = bufferedReader.readLine();
            while (true) {
                System.out.print("Il livello include un esame? [Y-N]: ");
                levelExam = bufferedReader.readLine().toLowerCase();
                if (levelExam.toLowerCase().equals("y") || levelExam.toLowerCase().equals("n")) {
                    break;
                } else {
                    GraphicUtils.showError("Scegliere tra [Y-N]!!");
                }
            }
            if(levelName.equals("") || levelBook.equals("")){
                GraphicUtils.showError("Compilare tutti i campi");
            }else{
                break;
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
    public static TeacherBean getTeacherInfo() throws IOException{
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSubTitle("Aggiungi insegnante",Color.GREEN);
        GraphicUtils.showSpacing(1);
        String cf,name,address,nationality;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.print("Inserire codice fiscale insegnante: ");
            cf = bufferedReader.readLine();
            System.out.print("Inserire nome insegnante: ");
            name = bufferedReader.readLine();
            System.out.print("Inserire indirizzo insegnante: ");
            address = bufferedReader.readLine();
            System.out.print("Inserire nazionalita insegnante: ");
            nationality = bufferedReader.readLine();
            if(cf.equals("") || name.equals("") || address.equals("") || nationality.equals("")){
                GraphicUtils.showError("Compilare tutti i campi !!");
            }else{
                break;
            }
        }
        return new TeacherBean(cf,name,address,nationality);
    }
    private static boolean isLevelNameValid(String levelName,List<LevelBean> levelBeanList){
        for(LevelBean levelBean : levelBeanList){
            if(levelBean.getName().equals(levelName)){
                return true;
            }
        }
        return false;
    }
    private static boolean isTeacherCfValid(String teacherCf, List<TeacherBean> teacherBeanList){
        for(TeacherBean teacherBean : teacherBeanList){
            if(teacherBean.getCf().toLowerCase().equals(teacherCf.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    private static boolean isCourseCodeAndLevelValid(String courseCode, String courseLevel, List<CourseBean> courseBeanList){
        for(CourseBean courseBean : courseBeanList){
            if(courseBean.getCode().toLowerCase().equals(courseCode.toLowerCase()) && courseBean.getLevel().toLowerCase().equals(courseLevel.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public static AssignationBean getAssignationInfo(List<CourseBean> courseBeanList, List<TeacherBean> teacherBeanList) throws IOException,NullListException{
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSubTitle("Assegna insegnante a corso",Color.GREEN);
        GraphicUtils.showSpacing(1);
        String teacherCf, courseCode, courseLevel;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Insegnanti:");
            TableCreator.showTable(teacherBeanList);
            System.out.print("Inserire codice fiscale insegnante: ");
            teacherCf = bufferedReader.readLine();
            System.out.println("Corsi:");
            TableCreator.showTable(courseBeanList);
            System.out.print("Inserire codice corso a cui assegnare l'insegnante: ");
            courseCode = bufferedReader.readLine();
            System.out.print("Inserire livello corso a cui assegnare l'insegnante: ");
            courseLevel = bufferedReader.readLine();
            if(isTeacherCfValid(teacherCf,teacherBeanList) && isCourseCodeAndLevelValid(courseCode,courseLevel,courseBeanList)){
                break;
            }else{
                GraphicUtils.showError("Controllare che esistano il corso e l'insegnante inseriti !!");
                GraphicUtils.showSpacing(1);
            }
        }
        return new AssignationBean(teacherCf, courseCode, courseLevel);
    }

    public static LessonBean getLessonInfo(List<AssignationBean> assignationBeanList) throws IOException,NullListException{
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSubTitle("Crea lezione di corso",Color.GREEN);
        GraphicUtils.showSpacing(1);
        String courseCode, courseLevel, teacherCf, lessonDay, startTime, endTime;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Assegnazioni:");
            TableCreator.showTable(assignationBeanList);
            System.out.print("Inserire codice corso per cui creare la lezione: ");
            courseCode = bufferedReader.readLine();
            System.out.print("Inserire livello del corso per cui creare la lezione: ");
            courseLevel = bufferedReader.readLine();
            System.out.print("Inserire codice fiscale dell' insegnante da assegnare alla lezione: ");
            teacherCf = bufferedReader.readLine();
            System.out.print("Inserire giorno della lezione [Lun, mar, mer, gio, ven, sab, dom]: ");
            lessonDay = bufferedReader.readLine();
            System.out.print("Inserire orario di inizio [hh:mm] : ");
            startTime = bufferedReader.readLine();
            System.out.print("Inserire orario di fine [hh:mm] : ");
            endTime = bufferedReader.readLine();
            if(isAssignationValid(teacherCf,courseCode,courseLevel,assignationBeanList) && isLessonDayValid(lessonDay) && isValidTimeFormat(startTime) && isValidTimeFormat(endTime)){
                break;
            }else{
                GraphicUtils.showError("Le informazioni inserite non sono corrette !!");
                GraphicUtils.showSpacing(1);
            }

        }
        return new LessonBean(courseCode,courseLevel,teacherCf,lessonDay,startTime,endTime);
    }
    private static boolean isValidTimeFormat (String time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try{
            LocalTime.parse(time, dateTimeFormatter);
            return true;
        }catch(DateTimeParseException e){
            return false;
        }
    }
    private static boolean isLessonDayValid(String lessonDay) {
        String[] dayArray = {"lun", "mar", "mer", "gio", "ven", "sab", "dom"};
        return Arrays.asList(dayArray).contains(lessonDay.toLowerCase());
    }

    private static boolean isAssignationValid(String teacherCf, String courseCode, String courseLevel, List<AssignationBean> assignationBeanList) {
        for(AssignationBean assignationBean : assignationBeanList){
            if(assignationBean.getTeacher().toLowerCase().equals(teacherCf.toLowerCase()) && assignationBean.getCourseCode().toLowerCase().equals(courseCode.toLowerCase()) && assignationBean.getCourseLevel().toLowerCase().equals(courseLevel.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
