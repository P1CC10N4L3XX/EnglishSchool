package it.uniroma2.controller;

import it.uniroma2.beans.AbsenceBean;
import it.uniroma2.beans.CourseBean;
import it.uniroma2.beans.StudentBean;
import it.uniroma2.database.CourseListProcedureDAO;
import it.uniroma2.database.CreateAbsenceProcedureDAO;
import it.uniroma2.database.CreateStudentProcedureDAO;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.*;
import it.uniroma2.models.Absence;
import it.uniroma2.models.Course;
import it.uniroma2.models.Role;
import it.uniroma2.models.Student;
import it.uniroma2.view.SecretariatView;
import it.uniroma2.view.utils.GraphicUtils;
import it.uniroma2.view.utils.TableCreator;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class SecretariatController implements Controller{
    @Override
    public void start() {
        try{
            ConnectionFactory.getInstance().changeRole(Role.SEGRETERIA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int command;
        while(true) {
            try {
                command = SecretariatView.getCommand();
            } catch (NotCompatibleOsException | IOException e) {
                throw new RuntimeException(e);
            }catch(InputMismatchException e){
                GraphicUtils.showError("Inserire un numero !!");
                continue;
            }
            switch (command) {
                case 1 -> createStudent();
                case 2 -> createAbsence();
                case 3 -> logout();
                case 4 -> System.exit(0);
            }
            if(command == 3){
                break;
            }
        }
    }

    private void logout() {
        try{
            ConnectionFactory.getInstance().changeRole(Role.LOGIN);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void createAbsence() {
        try{
            while(true){
                try{
                    AbsenceBean absenceBean = SecretariatView.getAbscenceInfo();
                    Absence absence = new CreateAbsenceProcedureDAO().execute(absenceBean.getStudent(),absenceBean.getDate(),absenceBean.getStartTime(),absenceBean.getEndTime());
                    GraphicUtils.showSuccess("assenza creata con successo !!");
                    GraphicUtils.showSpacing(1);
                    TableCreator.showRow(absenceBean);
                    break;
                }catch(DuplicatedEntryDAOException | NotExistentLessonException e){
                    GraphicUtils.showError(e.getMessage());
                }
            }
        }catch(IOException | DAOException e ){
            throw new RuntimeException(e);
        }
    }

    private void createStudent() {
        try{
            while (true){
                try {
                    List<Course> courseList = new CourseListProcedureDAO().execute();
                    List<CourseBean> courseBeanList = new ArrayList<>();
                    String courseCode, courseLevel, courseActivationDate, courseStudentsNumber;
                    for (Course course : courseList) {
                        courseCode = Integer.toString(course.getCode());
                        courseLevel = course.getLevel().getName();
                        courseActivationDate = course.getActivationDate().toString();
                        courseStudentsNumber = Integer.toString(course.getStudentsNumber());
                        courseBeanList.add(new CourseBean(courseCode, courseLevel, courseActivationDate, courseStudentsNumber));
                    }
                    StudentBean studentBean = SecretariatView.getStudentInfo(courseBeanList);
                    Date registrationDate = Date.valueOf(LocalDate.now());
                    Student student = new CreateStudentProcedureDAO().execute(studentBean.getCf(),studentBean.getName(),studentBean.getAbsenceNumber(),studentBean.getTelephonicNumber(),studentBean.getCourseCode(),studentBean.getcourseLevel(),registrationDate);
                    studentBean.setRegistrationDate(student.getRegistrationDate().toString());
                    GraphicUtils.showSuccess("studente creato con successo !!");
                    GraphicUtils.showSpacing(1);
                    TableCreator.showRow(studentBean);
                    break;
                }catch(DuplicatedEntryDAOException | DataTooLongException e){
                    GraphicUtils.showError(e.getMessage());
                }
            }
        }catch(IOException | DAOException e){
            throw new RuntimeException(e);
        }catch(NullListException e){
            GraphicUtils.showError("Non sono stati creati corsi a cui assegnare l'allievo !!");
        }
    }

}
