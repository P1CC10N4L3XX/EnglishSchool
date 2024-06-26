package it.uniroma2.controller;

import it.uniroma2.beans.CourseBean;
import it.uniroma2.beans.StudentBean;
import it.uniroma2.beans.TeacherBean;
import it.uniroma2.database.CourseListProcedureDAO;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DataTooLongException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.models.Course;
import it.uniroma2.models.Role;
import it.uniroma2.view.SecretariatView;
import it.uniroma2.view.utils.GraphicUtils;

import java.io.IOException;
import java.sql.SQLException;
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

    }

    private void createStudent() {
        while (true){
            try{
                List<Course> courseList = new CourseListProcedureDAO().execute();
                List<CourseBean> courseBeanList = new ArrayList<>();
                String courseCode,courseLevel,courseActivationDate,courseStudentsNumber;
                for(Course course : courseList){
                    courseCode = Integer.toString(course.getCode());
                    courseLevel = course.getLevel().getName();
                    courseActivationDate = course.getActivationDate().toString();
                    courseStudentsNumber = Integer.toString(course.getStudentsNumber());
                    courseBeanList.add(new CourseBean(courseCode,courseLevel,courseActivationDate,courseStudentsNumber));
                }
                StudentBean studentBean = SecretariatView.getStudentInfo();

            }catch(IOException | DAOException e){
                throw new RuntimeException(e);
            }catch(DuplicatedEntryDAOException | DataTooLongException e){
                GraphicUtils.showError(e.getMessage());
            }
        }
    }

}
