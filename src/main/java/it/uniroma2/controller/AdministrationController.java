package it.uniroma2.controller;

import it.uniroma2.beans.*;
import it.uniroma2.database.*;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.*;
import it.uniroma2.models.*;
import it.uniroma2.view.AdministrationView;
import it.uniroma2.view.utils.GraphicUtils;
import it.uniroma2.view.utils.TableCreator;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

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
            }catch(InputMismatchException e){
                GraphicUtils.showError("Inserire un numero !!");
                continue;
            }
            switch (command) {
                case 1 -> createLevel();
                case 2 -> createCourse();
                case 3 -> createTeacher();
                case 4 -> createLesson();
                case 5 -> generateReport();
                case 6 -> logout();
                case 7 -> System.exit(0);
                default -> GraphicUtils.showError("Scelta non valida !!");
            }
            if(command == 7){
                break;
            }
        }
    }

    private void generateReport() {
        List<ReportRowAdministration> reportRowAdministrationList = new ArrayList<>();
        List<ReportRowAdministrationBean> reportRowAdministrationBeanList = new ArrayList<>();
        try {
            while (true) {
                try {
                    ReportInputBean reportInputBean = AdministrationView.getReportInfo();
                    reportRowAdministrationList = new GenerateAdministrationReportProcedureDAO().execute(reportInputBean.getTeacher(),reportInputBean.getMonth());
                    String date,dayOfWeek,courseCode,courseLevel,startTime,endTime;
                    for(ReportRowAdministration reportRowAdministration : reportRowAdministrationList){
                        date = String.valueOf(reportRowAdministration.getDate());
                        dayOfWeek = reportRowAdministration.getDayOfWeek();
                        courseCode = String.valueOf(reportRowAdministration.getCourse().getCode());
                        courseLevel = reportRowAdministration.getCourse().getLevel().getName();
                        startTime = String.valueOf(reportRowAdministration.getStartTime());
                        endTime = String.valueOf(reportRowAdministration.getEndTime());
                        reportRowAdministrationBeanList.add(new ReportRowAdministrationBean(date,dayOfWeek,courseCode,courseLevel,startTime,endTime));
                    }
                    TableCreator.showTable(reportRowAdministrationBeanList);
                    break;
                }catch(InputMismatchException e){
                    GraphicUtils.showError("Il mese inserito non è valido");
                }
            }
        }catch(IOException | DAOException e){
            throw new RuntimeException(e);
        }catch(NullListException e){
            GraphicUtils.showError("Non esistono lezioni per l'insegnante inserito !!");
        }
    }

    private void logout(){
        try {
            ConnectionFactory.getInstance().changeRole(Role.LOGIN);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private void createLesson() {
        try {
            while (true) {
                LessonBean lessonBean = AdministrationView.getLessonInfo();
                try {
                    Lesson lesson = new CreateLessonProcedureDAO().execute(lessonBean.getCourseCode(),lessonBean.getCourseLevel(),lessonBean.getTeacher(),lessonBean.getDay(),lessonBean.getStartTime(),lessonBean.getEndTime());
                    String courseCode = Integer.toString(lesson.getCourse().getCode());
                    String courseLevel = lesson.getCourse().getLevel().getName();
                    String teacherCf = lesson.getTeacher().getCf();
                    String day = lesson.getDay().toString();
                    String startTime = lesson.getStartTime().toString();
                    String endTime = lesson.getEndTime().toString();
                    GraphicUtils.showSpacing(1);
                    GraphicUtils.showSuccess("Lezione creata con successo");
                    TableCreator.showRow(new LessonBean(courseCode,courseLevel,teacherCf,day,startTime,endTime));
                    GraphicUtils.showSpacing(1);
                    break;
                }catch(DataTooLongException | DuplicatedEntryDAOException | LessonTimeException | VirException e){
                    GraphicUtils.showSpacing(1);
                    GraphicUtils.showError(e.getMessage());
                    GraphicUtils.showSpacing(1);
                }
            }
        }catch(IOException | DAOException e){
            throw new RuntimeException(e);
        }
    }

    private void createTeacher() {
        while (true){
            try{
                TeacherBean teacherBean = AdministrationView.getTeacherInfo();
                Teacher teacher = new CreateTeacherProcedureDAO().execute(teacherBean.getCf(),teacherBean.getName(),teacherBean.getAddress(),teacherBean.getNationality());
                TableCreator.showRow(teacherBean);
                break;
            }catch(IOException|DAOException e){
                throw new RuntimeException(e);
            }catch(DuplicatedEntryDAOException | DataTooLongException e){
                GraphicUtils.showError(e.getMessage());
            }
        }
    }

    private void createLevel() {
        LevelBean levelBean;
        List<LevelBean> levelBeanList = new ArrayList<>();
        List<Level> levelList = new ArrayList<>();
        Level level;
        try {
            levelList = new LevelListProcedureDAO().execute();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        for(Level levelItem : levelList){
            levelBeanList.add(new LevelBean(levelItem.getName(),levelItem.getBook(),levelItem.getExam()?("yes"):("no")));
        }
        while(true) {
            try {
                levelBean = AdministrationView.getLevelInfo(levelBeanList);
                level = new CreateLevelProcedureDAO().execute(levelBean.getName(), levelBean.getBook(), levelBean.getExam().equals("y"));
                levelBean.setName(level.getName());
                levelBean.setBook(level.getBook());
                levelBean.setExam(level.getExam()?("yes"):("no"));
                GraphicUtils.showSuccess("Livello creato con successo !!");
                TableCreator.showRow(levelBean);
                break;
            } catch (DAOException | IOException e) {
                throw new RuntimeException(e);
            } catch (DuplicatedEntryDAOException | DataTooLongException e) {
                GraphicUtils.showError(e.getMessage());
            }
        }

    }

    private void createCourse(){
        CourseBean courseBean;
        List<LevelBean> levelBeanList = new ArrayList<>();
        List<Level> levelList;
        try{
            levelList = new LevelListProcedureDAO().execute();
            for(Level level : levelList){
                levelBeanList.add(new LevelBean(level.getName(),level.getBook(),level.getExam()?("yes"):("no")));
            }
            courseBean = AdministrationView.getCourseInfo(levelBeanList);
            String levelCourse = courseBean.getLevel();
            Date courseActivationDate = Date.valueOf(LocalDate.now());
            Course course = new CreateCourseProcedureDAO().execute(levelCourse,courseActivationDate);
            courseBean.setCode(Integer.toString(course.getCode()));
            courseBean.setActivationDate(courseActivationDate.toString());
            courseBean.setStudentsNumber("0");
            GraphicUtils.showSuccess("corso creato con successo !!");
            GraphicUtils.showSpacing(1);
            TableCreator.showRow(courseBean);
        }catch(DAOException | IOException e){
            throw new RuntimeException(e);
        }catch (NullListException e){
            GraphicUtils.showError("Non esistono ancora livelli quindi prima di creare un corso aggiungi almeno un livello!!");
            return;
        }
    }
}
