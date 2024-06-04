package it.uniroma2.controller;

import it.uniroma2.beans.CourseBean;
import it.uniroma2.beans.LevelBean;
import it.uniroma2.database.CreateCourseProcedureDAO;
import it.uniroma2.database.CreateLevelProcedureDAO;
import it.uniroma2.database.LevelListProcedureDAO;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.exceptions.NullListException;
import it.uniroma2.models.Course;
import it.uniroma2.models.Level;
import it.uniroma2.models.Role;
import it.uniroma2.view.AdministrationView;
import it.uniroma2.view.utils.Color;
import it.uniroma2.view.utils.GraphicUtils;
import it.uniroma2.view.utils.TableCreator;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            }
            switch (command) {
                case 1 -> createLevel();
                case 2 -> createCourse();
                case 3 -> System.out.println("Bravo hai creato una lezione");
                case 4 -> System.out.println("Bravo hai aggiunto un insegnante");
                case 5 -> System.out.println("Bravo hai assegnato un insegnante ad un corso");
                case 6 -> System.out.println("Bravo hai generato un report");
                case 7 -> System.out.println("Bravo hai fatto logout");
                default -> throw new RuntimeException("Scelta non valida !!");
            }
        }
    }

    private void createLevel(){
        LevelBean levelBean;
        Level level;
        try{
            levelBean = AdministrationView.getLevelInfo();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            level = new CreateLevelProcedureDAO().execute(levelBean.getName(),levelBean.getBook(),levelBean.getExam().equals("y"));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createCourse(){
        CourseBean courseBean;
        List<LevelBean> levelBeanList = new ArrayList<>();
        List<Level> levelList;
        try{
            levelList = new LevelListProcedureDAO().execute();
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        for(Level level : levelList){
            levelBeanList.add(new LevelBean(level.getName(),level.getBook(),level.getExam()?("yes"):("no")));
        }
        try{
            courseBean = AdministrationView.getCourseInfo(levelBeanList);
        }catch(IOException e){
            throw new RuntimeException(e);
        }catch (NullListException e){
            GraphicUtils.showError("Non esistono ancora livelli quindi prima di creare un corso aggiungi almeno un livello!!");
            return;
        }
        String levelCourse = courseBean.getLevel();
        Date courseActivationDate = Date.valueOf(LocalDate.now());
        Course course;
        try{
            course = new CreateCourseProcedureDAO().execute(levelCourse,courseActivationDate);
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        courseBean.setCode(Integer.toString(course.getCode()));
        courseBean.setActivationDate(courseActivationDate.toString());
        courseBean.setStudentsNumber("0");
        List<CourseBean> courseBeanList = new ArrayList<>();
        courseBeanList.add(courseBean);
        System.out.println(Color.fromColor(Color.GREEN) +"Il seguente corso è stato aggiunto:"+"\033[0m");
        try {
            TableCreator.showTable(courseBeanList);
        } catch (NullListException e) {
            System.out.println("Errore nella creazione della tabella");
        }
    }
}
