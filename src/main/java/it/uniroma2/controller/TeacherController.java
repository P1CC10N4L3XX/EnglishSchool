package it.uniroma2.controller;

import it.uniroma2.beans.ReportRowTeacherBean;
import it.uniroma2.database.GenerateWeekReportProcedureDAO;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.database.utils.TeacherCookie;
import it.uniroma2.exceptions.*;
import it.uniroma2.models.Lesson;
import it.uniroma2.models.Role;
import it.uniroma2.view.TeacherView;
import it.uniroma2.view.utils.GraphicUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class TeacherController implements Controller{

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
                command = TeacherView.getCommand();
            } catch (NotCompatibleOsException | IOException e) {
                throw new RuntimeException(e);
            }catch(InputMismatchException e){
                GraphicUtils.showError("Inserire un numero !!");
                continue;
            }
            switch (command) {
                case 1 -> generateReport();
                case 2 -> logout();
                case 3 -> System.exit(0);
            }
            if(command == 2){
                break;
            }
        }
    }
    private void logout(){
        try{
            ConnectionFactory.getInstance().changeRole(Role.LOGIN);
            TeacherCookie.getInstance().logout();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void generateReport(){
        try {
            List<Lesson> lessonList = new GenerateWeekReportProcedureDAO().execute(TeacherCookie.getInstance().getLoggedUser());
            List<ReportRowTeacherBean> reportRowTeacherBeanList = new ArrayList<>();
            String courseCode, courseLevel, lessonDay, startTime, endTime;
            for(Lesson lesson : lessonList){
                courseCode = String.valueOf(lesson.getCourse().getCode());
                courseLevel = lesson.getCourse().getLevel().getName();
                lessonDay = lesson.getDay();
                startTime = String.valueOf(lesson.getStartTime());
                endTime = String.valueOf(lesson.getEndTime());
                reportRowTeacherBeanList.add(new ReportRowTeacherBean(courseCode,courseLevel,lessonDay,startTime,endTime));
            }
            TeacherView.viewWeekReport(reportRowTeacherBeanList,TeacherCookie.getInstance().getLoggedUser());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
}
