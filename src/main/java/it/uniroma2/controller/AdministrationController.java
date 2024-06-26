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
                case 4 -> assignTeacher();
                case 5 -> createLesson();
                case 6 -> System.out.println("Bravo hai generato un report");
                case 7 -> logout();
                case 8 -> System.exit(0);
                default -> GraphicUtils.showError("Scelta non valida !!");
            }
            if(command == 7){
                break;
            }
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
        List<Assignation> assignationList = new ArrayList<>();
        List<AssignationBean> assignationBeanList = new ArrayList<>();
        try{
            assignationList = new AssignationListProcedureDAO().execute();
            String teacherCf, courseCode, courseLevel;
            for(Assignation assignation : assignationList){
                teacherCf = assignation.getTeacher().getCf();
                courseCode = Integer.toString(assignation.getCourse().getCode());
                courseLevel = assignation.getCourse().getLevel().getName();
                assignationBeanList.add(new AssignationBean(teacherCf,courseCode,courseLevel));
            }
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        try {
            while (true) {
                LessonBean lessonBean = AdministrationView.getLessonInfo(assignationBeanList);
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
                }catch(DAOException e){
                    throw new RuntimeException(e);
                }catch(DataTooLongException | DuplicatedEntryDAOException | LessonTimeException e){
                    GraphicUtils.showSpacing(1);
                    GraphicUtils.showError(e.getMessage());
                    GraphicUtils.showSpacing(1);
                }

            }
        }catch(NullListException e){
            GraphicUtils.showError("Potrebbero non esistere corsi o insegnanti per cui creare lezioni !!");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private void assignTeacher(){
        List<CourseBean> courseBeanList = new ArrayList<>();
        List<TeacherBean> teacherBeanList = new ArrayList<>();
        List<Teacher> teacherList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        Assignation assignation;
        try {
            teacherList = new TeacherListProcedureDAO().execute();
            courseList = new CourseListProcedureDAO().execute();
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        String courseCode, courseLevel, courseActivationDate, courseStudentsNumber;
        for (Course course : courseList) {
            courseCode = Integer.toString(course.getCode());
            courseLevel = course.getLevel().getName();
            courseActivationDate = course.getActivationDate().toString();
            courseStudentsNumber = Integer.toString(course.getStudentsNumber());
            courseBeanList.add(new CourseBean(courseCode, courseLevel, courseActivationDate, courseStudentsNumber));
        }
        String teacherCf, teacherName, teacherAddress, teacherNationality;
        for (Teacher teacher : teacherList) {
            teacherCf = teacher.getCf();
            teacherName = teacher.getName();
            teacherAddress = teacher.getAddress();
            teacherNationality = teacher.getNationality();
            teacherBeanList.add(new TeacherBean(teacherCf, teacherName, teacherAddress, teacherNationality));
        }
        try {
            while (true) {
                AssignationBean assignationBean = AdministrationView.getAssignationInfo(courseBeanList, teacherBeanList);
                try {
                    assignation = new CreateAssignationProcedureDAO().execute(assignationBean.getTeacher(), assignationBean.getCourseCode(), assignationBean.getCourseLevel());
                }catch(DuplicatedEntryDAOException e){
                    GraphicUtils.showError(e.getMessage());
                    continue;
                }
                assignationBean.setTeacher(assignation.getTeacher().getCf());
                assignationBean.setCourseCode(Integer.toString(assignation.getCourse().getCode()));
                assignationBean.setCourseLevel(assignation.getCourse().getLevel().getName());
                GraphicUtils.showSpacing(1);
                GraphicUtils.showSuccess("Assegnazione effettuata con successo !!");
                TableCreator.showRow(assignationBean);
                GraphicUtils.showSpacing(1);
                break;
            }
        }catch(DAOException | IOException e){
            throw new RuntimeException(e);
        }catch(NullListException e){
            GraphicUtils.showError("Potrebbero non esistere corsi o insegnanti sulla base di dati !!");
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

            TableCreator.showRow(courseBean);
        }catch(DAOException | IOException e){
            throw new RuntimeException(e);
        }catch (NullListException e){
            GraphicUtils.showError("Non esistono ancora livelli quindi prima di creare un corso aggiungi almeno un livello!!");
            return;
        }
    }
}
